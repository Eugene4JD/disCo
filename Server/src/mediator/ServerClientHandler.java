package mediator;

import com.google.gson.Gson;
import model.ServerModel;
import model.User;
import network.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static network.RequestType.LogToExistingDiscussion;
import static network.RequestType.RemoveDiscussion;

public class ServerClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private ServerModel model;
  private Gson gson;


  public ServerClientHandler(Socket socket, ServerModel model) throws
      IOException
  {
     this.socket = socket;
     in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     out = new PrintWriter(socket.getOutputStream(), true);
     gson = new Gson();
     this.running = true;
     this.model = model;
     model.addListener(this);
     model.addLog("User connected");
  }

  @Override public void run()
  {
     while (running)
     {
       try
       {
         String req = in.readLine();
         switch ((gson.fromJson(req, Request.class).getType()))
         {
           case Message:
             MessageRequest request = gson.fromJson(req,MessageRequest.class);
             model.addMessageToDiscussion(request.getDiscussionId(),request.getSender(),request.getMessage());
             break;


           case Log:
             LogRequest request1 = gson.fromJson(req,LogRequest.class);
             User user = model.getUserFromUserBaseByLogin(request1.getLogin());
             if (user != null)
             {
               if (user.getUserPassword().equals(request1.getPassword()))
               {
                 out.println(new BroadcastLoginStatusToUserRequest(true));
                 out.println(new BroadcastDiscussionsToUserRequest());
               }
               else
                 out.println(new BroadcastLoginStatusToUserRequest(false));
             }
             else
               out.println(new BroadcastLoginStatusToUserRequest(false));


           case LogToExistingDiscussion:
             LogToExistingDiscussionRequest request2 = gson.fromJson(req,LogToExistingDiscussionRequest.class);
             if (model.getDiscussionById(request2.getDiscussionId()).getUserBase().getUserByLogin(request2.getUserLogin()) == null)
             {
               model.getDiscussionById(request2.getDiscussionId()).addUser(model.getUserFromUserBaseByLogin(request2.getUserLogin()));
               out.println(gson.toJson(new BroadcastDiscussionToUserRequest(model.getDiscussionById(request2.getDiscussionId()))));
             }
             break;


           case CreateDiscussion:
             CreateDiscussionRequest request3 = gson.fromJson(req,CreateDiscussionRequest.class);
             if (model.getDiscussionById(request3.getDiscussionId()) == null)
             {
               model.createNewDiscussion(request3.getDiscussionId(),request3.getEditorLogin());
               out.println(gson.toJson(new BroadcastDiscussionToUserRequest(model.getDiscussionById(request3.getDiscussionId()))));
             }
             break;


           case RemoveDiscussion:
              RemoveDiscussionRequest request4 = gson.fromJson(req,RemoveDiscussionRequest.class);
              if (model.getDiscussionById(request4.getDiscussionId()) != null)
              {
                model.removeDiscussion(request4.getDiscussionId());
                out.println(gson.toJson(new BroadcastRemovingDiscussionToUserRequest(request4.getDiscussionId())));
              }
             break;
         }
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }

     }
  }
  public void stop()
  {
    this.running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (Exception e)
    {

    }
  }

  @Override public void propertyChange(PropertyChangeEvent event)
  {
    switch (event.getPropertyName())
    {
      case "BroadcastMessageToDiscussion":
        out.println(gson.toJson(event.getNewValue()));
    }
  }
}

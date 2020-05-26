package mediator;

import com.google.gson.Gson;
import model.Discussion;
import model.DiscussionList;
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
             model.addMessageToDiscussion(request.getDiscussionId(),request.getSenderID(),request.getMessage());
             break;
           case Log:
             LogRequest request1 = gson.fromJson(req,LogRequest.class);
             if (request1.isNewUser())
             {
               model.addNewUserToUserBase("RegisteredUser",request1.getLogin(),request1.getPassword());
             }
             else
             {
               User user = model.getUserFromUserBaseByLogin(request1.getLogin());
               if (user != null)
               {
                 if (user.getUserPassword().equals(request1.getPassword()))
                 {
                   out.println(gson.toJson(new BroadcastLoginStatusToUserRequest(true, user.getUserLogin(),user.getUserId())));
                   DiscussionList discussionList =model.getDiscussionWithUser(model.getUserFromUserBaseByLogin(request1.getLogin()).getUserId());
                   out.println(gson.toJson(new BroadcastDiscussionsToUserRequest(discussionList)));
                 }
                 else
                   out.println(gson.toJson(new BroadcastLoginStatusToUserRequest(false, "",0)));
               }
               else
                 out.println(gson.toJson(new BroadcastLoginStatusToUserRequest(false, "",0)));
             }
             break;


           case LogToExistingDiscussion:
             LogToExistingDiscussionRequest request2 = gson.fromJson(req,LogToExistingDiscussionRequest.class);
             if (!(model.getDiscussionById(request2.getDiscussionId()).getUserBase().isLogged(request2.getUserId())))
             {
             System.out.println(request2.getDiscussionId());
              model.addUserToDiscussion(request2.getDiscussionId(),
                   request2.getUserId());
               out.println(gson.toJson(new BroadcastDiscussionToUserRequest(model.getDiscussionById(request2.getDiscussionId()),request2.getUserId())));
             }
             break;


           case CreateDiscussion:
             CreateDiscussionRequest request3 = gson.fromJson(req,CreateDiscussionRequest.class);
             Discussion discussion = model.createNewDiscussion(request3.getDiscussionId(),model.getUserFromUserBaseById(request3.getUserID()).getUserLogin());
             out.println(gson.toJson(new BroadcastDiscussionToUserRequest(discussion,request3.getUserID())));
             break;


           case RemoveDiscussion:
              RemoveDiscussionRequest request4 = gson.fromJson(req,RemoveDiscussionRequest.class);
              if (model.getDiscussionById(request4.getDiscussionId()) != null)
              {
                model.removeDiscussion(request4.getDiscussionId(),request4.getUserId());
                out.println(gson.toJson(new BroadcastRemovingDiscussionToUserRequest(request4.getDiscussionId())));
              }
             break;

           case SearchDiscussionById:
             SearchDiscussionByIdRequest request5 = gson.fromJson(req,SearchDiscussionByIdRequest.class);
             if (model.getDiscussionById(request5.getId()) != null)
             {
               out.println(gson.toJson(new BroadcastSearchedDiscussionToUser(model.getDiscussionById(request5.getId()))));
             }
             break;
           case SearchDiscussionsByName:
             SearchDiscussionsByNameRequest request6 = gson.fromJson(req,SearchDiscussionsByNameRequest.class);
             if (model.getDiscussionsByName(request6.getName()).size() != 0)
             {
               out.println(gson.toJson(new BroadcastSearchedDiscussionsToUser(model.getDiscussionsByName(request6.getName()))));
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

package mediator;

import com.google.gson.Gson;
import network.*;
import utility.Log;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiver implements Runnable, UnnamedPropertyChangeSubject
{
  private Socket socket;
  private BufferedReader in ;
  private boolean running;
  private Gson gson;
  private PropertyChangeSupport property;
  private Log log;

  public ClientReceiver(Socket socket) throws IOException
  {
    this.socket= socket;
    this.running = true;
    log = Log.getInstance();
    gson = new Gson();
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    property = new PropertyChangeSupport(this);
  }
  @Override public void run()
  {
    log.addLog("clientStarted");
    while (running)
    {
       try
       {
         String req = in.readLine();
         log.addLog(req);
         switch (gson.fromJson(req, Request.class).getType())
         {
           case BroadcastMessage:
             BroadcastMessageToDiscussionRequest broadcastMessageToDiscussionRequest = gson.fromJson(req,BroadcastMessageToDiscussionRequest.class);
             property.firePropertyChange("broadcastMessageToDiscussion",null,broadcastMessageToDiscussionRequest);
             break;
           case BroadcastDiscussions:
             BroadcastDiscussionsToUserRequest broadcastDiscussionsToUserRequest = gson.fromJson(req,BroadcastDiscussionsToUserRequest.class);
             property.firePropertyChange("broadcastDiscussionsToUser",null,broadcastDiscussionsToUserRequest);
             break;
           case BroadcastDiscussion:
             BroadcastDiscussionToUserRequest broadcastDiscussionToUserRequest = gson.fromJson(req,
                 BroadcastDiscussionToUserRequest.class);
             property.firePropertyChange("broadcastDiscussionToUser",null,
                 broadcastDiscussionToUserRequest);
             break;
           case BroadcastRemovingDiscussion:
             BroadcastRemovingDiscussionToUserRequest broadcastRemovingDiscussionToUserRequest = gson.fromJson(req,
                 BroadcastRemovingDiscussionToUserRequest.class);
             property.firePropertyChange("broadcastRemovingDiscussionToUser",null,
                 broadcastRemovingDiscussionToUserRequest);
             break;
           case BroadcastLoginStatus:
             BroadcastLoginStatusToUserRequest broadcastLoginStatusToUserRequest = gson.fromJson(req,BroadcastLoginStatusToUserRequest.class);
             property.firePropertyChange("broadcastLoginStatusToUser",null,broadcastLoginStatusToUserRequest);
             break;
         }
       }
       catch (IOException e)
       {
         e.printStackTrace();
       }
    }
  }
  public void stop() throws IOException
  {
    this.running = false;
    in.close();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    this.property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    this.property.removePropertyChangeListener(listener);
  }
}

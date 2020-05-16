package mediator;

import com.google.gson.Gson;
import model.ServerModel;
import network.Request;

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
           case BroadcastDiscussions:
           case BroadcastMessage:
           case Log:
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

  @Override public void propertyChange(PropertyChangeEvent propertyChangeEvent)
  {

  }
}

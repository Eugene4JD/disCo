package mediator;

import com.google.gson.Gson;
import network.Request;
import utility.Log;
import utility.NamedPropertyChangeSubject;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver implements Runnable, UnnamedPropertyChangeSubject
{
  private Socket socket;
  private BufferedReader in ;
  private boolean running;
  private Gson gson;
  private PropertyChangeSupport property;
  private Log log;

  public ClientReceiver(Socket socket)
  {
    this.socket= socket;
    this.running = true;
    log = Log.getInstance();
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
           case Log:
           case Message:
           case BroadcastDiscussions:
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

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {

  }
}

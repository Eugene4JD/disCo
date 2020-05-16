package mediator;

import com.google.gson.Gson;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver implements Runnable
{
  private Socket socket;
  private BufferedReader in ;
  private boolean running;
  private Gson gson;
  private PropertyChangeSupport property;

  public ClientReceiver(Socket socket)
  {
    this.socket= socket;
    this.running = true;
  }
  @Override public void run()
  {
    while (running)
    {

    }
  }
  public void stop() throws IOException
  {
    this.running = false;
    in.close();
  }
}

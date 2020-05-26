package mediator;

import model.ServerModel;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable
{
  private boolean running;
  private ServerSocket welcomeSocket;
  private final int PORT = 6789;
  private ServerModel model;

  public ServerConnector(ServerModel model)
  {
    try
    {
      welcomeSocket = new ServerSocket(PORT);
      model.addLog("Server is running");
    }
    catch (Exception e)
    {

    }
    this.model = model;
    this.running = true;
  }

  @Override public void run()
  {
    while (running)
    {
      try
      {
        Socket socket = welcomeSocket.accept();
        ServerClientHandler serverClientHandler = new ServerClientHandler(
            socket, model);
        Thread thread = new Thread(serverClientHandler);
        thread.setDaemon(true);
        thread.start();
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
  }

}

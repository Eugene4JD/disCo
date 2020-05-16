package mediator;

import com.google.gson.Gson;
import model.ClientModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSender implements RemoteModel
{
  public static int PORT = 6789;
  public static String HOST = "localhost";
  private Socket socket;
  private String host;
  private int port;
  private Gson gson;
  private PrintWriter out;
  private ClientReceiver clientReceiver;
  private ClientModel model;


  public ClientSender(ClientModel model) throws IOException
  {
    this.gson = new Gson();
    this.model = model;
    this.port = PORT;
    this.host = HOST;
  }

  public ClientSender(ClientModel model, int port, String host) throws IOException
  {
    this.gson = new Gson();
    this.model = model;
    this.port = port;
    this.host = host;
  }
  @Override public void disconnect() throws IOException
  {
     out.close();
     clientReceiver.stop();
     socket.close();
  }

  @Override public void connect() throws IOException
  {
    socket = new Socket(host,port);
    out = new PrintWriter(socket.getOutputStream(),true);
    clientReceiver = new ClientReceiver(socket);
    Thread thread = new Thread(clientReceiver);
    thread.setDaemon(true);
    thread.start();

  }

  @Override public void createNewDiscussion()
  {

  }

  @Override public void log(String login, String password, boolean isNewUser)
  {

  }

  @Override public void logToExistingDiscussion(String discussionId)
  {

  }

  @Override public void removeDiscussion()
  {

  }

  @Override public void sendMessageInDiscussion()
  {

  }
}

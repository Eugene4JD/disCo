package mediator;

import com.google.gson.Gson;
import model.ClientModel;
import model.Discussion;
import network.*;

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
    clientReceiver.addListener(model);
    Thread thread = new Thread(clientReceiver);
    thread.setDaemon(true);
    thread.start();

  }

  @Override public void createNewDiscussion(String discussionID,String editorLogin)
  {
    out.println(gson.toJson(new CreateDiscussionRequest(discussionID,editorLogin)));
  }

  @Override public void log(String login, String password, boolean isNewUser)
  {
    out.println(gson.toJson(new LogRequest(login,password,isNewUser)));
  }

  @Override public void logToExistingDiscussion(String discussionId,String login)
  {
    out.println(gson.toJson(new LogToExistingDiscussionRequest(discussionId,login)));
  }

  @Override public void removeDiscussion(int discussionID, int userId)
  {
    System.out.println("Prep,send");
    out.println(gson.toJson(new RemoveDiscussionRequest(discussionID,userId)));
    System.out.println("Send");
  }

  @Override public void sendMessageInDiscussion(String discussionId, String sender, String message)
  {
    out.println(gson.toJson(new MessageRequest(discussionId,sender,message)));
  }
}

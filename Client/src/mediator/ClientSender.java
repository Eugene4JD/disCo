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

  public ClientSender(ClientModel model, int port, String host)
      throws IOException
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
    socket = new Socket(host, port);
    out = new PrintWriter(socket.getOutputStream(), true);
    clientReceiver = new ClientReceiver(socket);
    clientReceiver.addListener(model);
    Thread thread = new Thread(clientReceiver);
    thread.setDaemon(true);
    thread.start();

  }

  @Override public void createNewDiscussion(String discussionID, int userId)
  {
    out.println(gson.toJson(new CreateDiscussionRequest(discussionID, userId)));
  }

  @Override public void log(String login, String password, boolean isNewUser,boolean isGuest)
  {
    out.println(gson.toJson(new LogRequest(login, password, isNewUser,isGuest)));
  }

  @Override public void logToExistingDiscussion(int discussionId, int userId)
  {
    out.println(
        gson.toJson(new LogToExistingDiscussionRequest(discussionId, userId)));
  }

  @Override public void removeDiscussion(int discussionID, int userId)
  {
    out.println(gson.toJson(new RemoveDiscussionRequest(discussionID, userId)));
  }

  @Override public void sendMessageInDiscussion(int discussionId, int senderID,
      String message)
  {
    out.println(
        gson.toJson(new MessageRequest(discussionId, senderID, message)));
  }

  @Override public void searchDiscussionByID(int id)
  {
    out.println(gson.toJson(new SearchDiscussionByIdRequest(id)));
  }

  @Override public void searchDiscussionsByName(String name)
  {
    out.println(gson.toJson(new SearchDiscussionsByNameRequest(name)));
  }

  @Override public void changeLoginOfUser(int userId, String newLogin)
  {
    out.println(gson.toJson(new ChangeUserNameRequest(userId, newLogin)));
  }

  @Override public void changeNameOfDiscussion(int discussionId,
      String discussionName)
  {
    out.println(gson.toJson(
        new ChangeDiscussionNameRequest(discussionId, discussionName)));
  }

  @Override public void changePassword(int userId, String oldPassword,
      String newPassword)
  {
    out.println(gson.toJson(
        new ChangePasswordRequest(userId, oldPassword, newPassword)));
  }

  @Override public void removeUser(int userId)
  {
    out.println(gson.toJson(new RemoveUserRequest(userId)));
  }
}

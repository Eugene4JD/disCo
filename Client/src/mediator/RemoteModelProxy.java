package mediator;

import model.ClientModel;

import java.io.IOException;

public class RemoteModelProxy implements RemoteModel
{
  private ClientSender clientSender;

  public RemoteModelProxy(ClientModel model) throws IOException
  {
    clientSender = new ClientSender(model);
  }

  @Override public void log(String login, String password, boolean isNewUser)
  {
    clientSender.log(login,password,isNewUser);
  }

  @Override public void connect() throws IOException
  {
    clientSender.connect();
  }

  @Override public void disconnect() throws IOException
  {
    clientSender.disconnect();
  }

  @Override public void removeDiscussion(String discussionId)
  {
    clientSender.removeDiscussion(discussionId);
  }

  @Override public void createNewDiscussion(String discussionId)
  {
    clientSender.createNewDiscussion(discussionId);
  }

  @Override public void logToExistingDiscussion(String discussionId,
      String login)
  {
    clientSender.logToExistingDiscussion(discussionId,login);
  }

  @Override public void sendMessageInDiscussion(String discussionId,
      String sender, String message)
  {
    clientSender.sendMessageInDiscussion(discussionId,sender,message);
  }
}

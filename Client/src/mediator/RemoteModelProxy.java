package mediator;

import model.ClientModel;
import model.Discussion;

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
    clientSender.log(login, password, isNewUser);
  }

  @Override public void connect() throws IOException
  {
    clientSender.connect();
  }

  @Override public void disconnect() throws IOException
  {
    clientSender.disconnect();
  }

  @Override public void removeDiscussion(int discussionId, int userId)
  {
    clientSender.removeDiscussion(discussionId, userId);
  }

  @Override public void createNewDiscussion(String discussionId, int UserId)
  {
    clientSender.createNewDiscussion(discussionId, UserId);
  }

  @Override public void logToExistingDiscussion(int discussionId, int userId)
  {
    clientSender.logToExistingDiscussion(discussionId, userId);
  }

  @Override public void sendMessageInDiscussion(int discussionId, int senderID,
      String message)
  {
    clientSender.sendMessageInDiscussion(discussionId, senderID, message);
  }

  @Override public void searchDiscussionByID(int id)
  {
    clientSender.searchDiscussionByID(id);
  }

  @Override public void searchDiscussionsByName(String name)
  {
    clientSender.searchDiscussionsByName(name);
  }

  @Override public void removeUser(int userId)
  {
    clientSender.removeUser(userId);
  }

  @Override public void changePassword(int userId, String oldPassword,
      String newPassword)
  {
    clientSender.changePassword(userId, oldPassword, newPassword);
  }

  @Override public void changeNameOfDiscussion(int discussionId,
      String discussionName)
  {
    clientSender.changeNameOfDiscussion(discussionId, discussionName);
  }

  @Override public void changeLoginOfUser(int userId, String newLogin)
  {
    clientSender.changeLoginOfUser(userId, newLogin);
  }
}

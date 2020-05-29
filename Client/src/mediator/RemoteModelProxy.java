package mediator;

import model.ClientModel;
import model.Discussion;

import java.io.IOException;

public class RemoteModelProxy implements RemoteModel
{
  private ClientSender clientSender;
  private ClientModel model;

  public RemoteModelProxy(ClientModel model) throws IOException
  {
    clientSender = new ClientSender(model);
    this.model = model;
  }

  @Override public void log(String login, String password, boolean isNewUser,boolean isGuest)
  {
    clientSender.log(login, password, isNewUser,isGuest);
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
    if (model.getDiscussionListBuffer().getDiscussionById(discussionId).getLoginOfEditorOfDiscussion().equals(model.getLogin()) || model.getUserType().equals("Admin"))
    {
      clientSender.removeDiscussion(discussionId, userId);
    }
    else
    {
      throw new IllegalStateException("Restrict access");
    }
  }

  @Override public void createNewDiscussion(String discussionId, int UserId)
  {
    if (!model.getUserType().equals("Guest"))
    {
      clientSender.createNewDiscussion(discussionId, UserId);
    }
    else
    {
      throw new IllegalStateException("! You are guest, only registered users are allowed to do that");
    }
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
    if (!model.getUserType().equals("Guest"))
    {
      clientSender.changePassword(userId, oldPassword, newPassword);
    }
    else
    {
      throw new IllegalStateException("! You are guest, only registered users are allowed to do that");
    }
  }

  @Override public void changeNameOfDiscussion(int discussionId,
      String discussionName)
  {
    if (model.getDiscussionListBuffer().getDiscussionById(discussionId).getLoginOfEditorOfDiscussion().equals(model.getLogin()) || model.getUserType().equals("Admin"))
    {
      clientSender.changeNameOfDiscussion(discussionId, discussionName);
    }
    else
    {
      throw new IllegalStateException("Restrict access");
    }
  }

  @Override public void changeLoginOfUser(int userId, String newLogin)
  {
    if (!model.getUserType().equals("Guest"))
    {
      clientSender.changeLoginOfUser(userId, newLogin);
    }
    else
    {
      throw new IllegalStateException("! You are guest, only registered users are allowed to do that");
    }
  }
}

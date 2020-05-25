package model;

import mediator.RemoteModel;
import mediator.RemoteModelProxy;
import network.*;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ClientModelManager implements ClientModel
{
  private DiscussionList discussionListBuffer;
  private UserBase userBaseBuffer;
  private RemoteModel remoteModel;
  private String login;
  private int id;
  private PropertyChangeSupport property;
  private Discussion tmpDiscussion;

  public ClientModelManager()
  {
    this.discussionListBuffer = new DiscussionList();
    this.userBaseBuffer = new UserBase();
    this.property = new PropertyChangeSupport(this);
    try
    {
      this.remoteModel = new RemoteModelProxy(this);
      this.remoteModel.connect();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    this.login = "";
  }

  @Override public void connect()
  {
    try
    {
      remoteModel.connect();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void disconnect()
  {
    try
    {
      remoteModel.disconnect();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void removeDiscussion(int discussionId, int userId)
  {
    this.remoteModel.removeDiscussion(discussionId, userId);
  }

  @Override public void addDiscussion(Discussion discussion)
  {
    this.discussionListBuffer.addDiscussion(discussion);
  }

  @Override public void addDiscussions(DiscussionList discussionList)
  {
    for (int i = 0; i < discussionList.size(); i++)
    {
      this.discussionListBuffer.addDiscussion(discussionList.getDiscussion(i));
    }
  }

  @Override public void addUser(User user)
  {
    userBaseBuffer.addUser(user);
  }

  @Override public void addUsers(UserBase userBase)
  {
    for (int i = 0; i < userBase.size(); i++)
    {
      this.userBaseBuffer.addUser(userBase.getUser(i));
    }
  }

  @Override public void createDiscussion(String discussionId)
  {
    remoteModel.createNewDiscussion(discussionId, login);
  }

  @Override public void log(String login, String password, boolean isNewUser)
  {
    remoteModel.log(login, password, isNewUser);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "broadcastMessageToDiscussion":
        BroadcastMessageToDiscussionRequest request = (BroadcastMessageToDiscussionRequest) evt
            .getNewValue();
        Discussion discussion = this.discussionListBuffer
            .getDiscussionById(request.getDiscussionID());
        if (discussion != null)
          discussion.getMessageList().addMessage(
              this.userBaseBuffer.getUserById(request.getSenderID())
                  .getUserLogin(), request.getMessage());
        break;
      case "broadcastDiscussionsToUser":
        BroadcastDiscussionsToUserRequest request1 = (BroadcastDiscussionsToUserRequest) evt
            .getNewValue();
        for (int i = 0; i < request1.getDiscussions().size(); i++)
        {
          discussionListBuffer
              .addDiscussion(request1.getDiscussions().getDiscussion(i));
        }
        property.firePropertyChange("AddList", null, discussionListBuffer);
        break;
      case "broadcastDiscussionToUser":
        BroadcastDiscussionToUserRequest request2 = (BroadcastDiscussionToUserRequest) evt
            .getNewValue();
        discussionListBuffer.addDiscussion(request2.getDiscussion());
        property.firePropertyChange("Add", null, request2.getDiscussion());
        break;
      case "broadcastRemovingDiscussionToUser":
        BroadcastRemovingDiscussionToUserRequest request3 = (BroadcastRemovingDiscussionToUserRequest) evt
            .getNewValue();
        discussionListBuffer.removeDiscussionById(request3.getDiscussionId());
        break;
      case "broadcastLoginStatusToUser":
        BroadcastLoginStatusToUserRequest request4 = (BroadcastLoginStatusToUserRequest) evt
            .getNewValue();
        if (request4.isLogSuccessful())
        {
          this.login = request4.getLogin();
          this.id = request4.getId();
          property.firePropertyChange("LogStatus", null, true);
        }
        else
        {
          property.firePropertyChange("LogStatus", null, false);
        }
        break;
      case "broadcastSearchedDiscussion":
        BroadcastSearchedDiscussionToUser request5 = (BroadcastSearchedDiscussionToUser) evt
            .getNewValue();
        property
            .firePropertyChange("searchByID", null, request5.getDiscussion());
        break;
      case "broadcastSearchedDiscussions":
        BroadcastSearchedDiscussionsToUser request6 = (BroadcastSearchedDiscussionsToUser) evt
            .getNewValue();
        property.firePropertyChange("searchByName", null,
            request6.getDiscussionList());
        break;
    }
  }

  public int getDiscussionIdFromBuffer(String discussionName)
  {
    int id = this.discussionListBuffer.getDiscussionByName(discussionName)
        .getDiscussionId();
    return id;
  }

  @Override public int getId()
  {
    return id;
  }

  public String getLogin()
  {
    return login;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    this.property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    this.property.removePropertyChangeListener(listener);
  }

  @Override public void sendMessageToDiscussion(int discussionID, int userId,
      String message)
  {
    remoteModel.sendMessageInDiscussion(discussionID, userId, message);
  }

  public DiscussionList getDiscussionListBuffer()
  {
    return this.discussionListBuffer;
  }

  @Override public void logToExistingDiscussion(int discussionId)
  {
    remoteModel.logToExistingDiscussion(discussionId, login);
  }

  @Override public void searchDiscussionsByName(String name)
  {
    remoteModel.searchDiscussionsByName(name);
  }

  @Override public void setTmpDiscussion(Discussion discussion)
  {
    tmpDiscussion = discussion;
  }

  @Override public Discussion getTmpDiscussion()
  {
    return tmpDiscussion;
  }

  @Override public void searchDiscussionById(int discussionId)
  {
    remoteModel.searchDiscussionByID(discussionId);
  }
}

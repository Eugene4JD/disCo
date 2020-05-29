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
  private int selectedDiscussionId;
  private DiscussionList lastSearched;
  private String userType;

  public ClientModelManager()
  {
    this.discussionListBuffer = new DiscussionList();
    this.lastSearched = new DiscussionList();
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
    this.userType = "";
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

  @Override public void removeDiscussion(int discussionId)
  {
    this.remoteModel.removeDiscussion(discussionId,id);
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
    remoteModel.createNewDiscussion(discussionId, id);
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
        {
          discussion.addMessage(request.getMessage().getText(),
              request.getDiscussionID());
          property.firePropertyChange("NewMessageToChat", null, discussion);

        }
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
        if (request2.getUserId() == this.id)
        {
          discussionListBuffer.addDiscussion(request2.getDiscussion());
          if (request2.getDiscussion().getLoginOfEditorOfDiscussion()
              .equals(this.login))
            property.firePropertyChange("Add", null, request2.getDiscussion());
          else
          {
            property.firePropertyChange("LoggedToExistingDiscussion", null,
                request2.getDiscussion().getDiscussionId());
          }
        }
        break;
      case "broadcastRemovingDiscussionToUser":
        BroadcastRemovingDiscussionToUserRequest request3 = (BroadcastRemovingDiscussionToUserRequest) evt
            .getNewValue();
        if (this.discussionListBuffer.getDiscussionById(request3.getDiscussionId()) != null)
        {
          discussionListBuffer.removeDiscussionById(request3.getDiscussionId());
          property.firePropertyChange("DiscussionRemoved",null,request3.getDiscussionId());
        }
        break;
      case "broadcastLoginStatusToUser":
        BroadcastLoginStatusToUserRequest request4 = (BroadcastLoginStatusToUserRequest) evt
            .getNewValue();
        if (request4.isLogSuccessful())
        {
          this.login = request4.getLogin();
          this.id = request4.getId();
          this.userType = request4.getTypeOfUser();
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
        this.lastSearched.clear();
        this.lastSearched.addDiscussion(request5.getDiscussion());
        property
            .firePropertyChange("searchByID", null, request5.getDiscussion());
        break;
      case "broadcastSearchedDiscussions":
        BroadcastSearchedDiscussionsToUser request6 = (BroadcastSearchedDiscussionsToUser) evt
            .getNewValue();
        property.firePropertyChange("searchByName", null,
            request6.getDiscussionList());
        this.lastSearched = request6.getDiscussionList();
        break;
      case "broadcastChangeDiscussionName":
        BroadcastChangedDiscussionName request7 = (BroadcastChangedDiscussionName) evt
            .getNewValue();
        if (!(this.discussionListBuffer
            .getDiscussionById(request7.getDiscussionId()) == null))
        {
          this.discussionListBuffer
              .getDiscussionById(request7.getDiscussionId())
              .setDiscussionName(request7.getDiscussionName());
          property.firePropertyChange("ChangedDiscussionName", null,
              request7.getDiscussionName());
        }
        break;
      case "broadcastChangePasswordToUser":
        BroadcastChangedPasswordToUser request8 = (BroadcastChangedPasswordToUser) evt
            .getNewValue();
        if (request8.isSuccessOfChangingPassword())
          property.firePropertyChange("ChangedPassword", null,
              "password successfully changed");
        else
          property.firePropertyChange("ChangedPassword", null,
              "wrong old password");
        break;
      case "broadcastChangeUserNameToUser":
        BroadcastChangedUserNameToUser request9 = (BroadcastChangedUserNameToUser) evt
            .getNewValue();
        this.login = request9.getNewUserName();
        property.firePropertyChange("ChangedUserName", null, login);
        break;
      case "broadcastDeletedAccountRequest":
        BroadcastDeletedAccountRequest request10 = (BroadcastDeletedAccountRequest) evt
            .getNewValue();
        property.firePropertyChange("RemoveUser", null, request10);
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
    remoteModel.sendMessageInDiscussion(discussionID, id, message);
  }

  public DiscussionList getDiscussionListBuffer()
  {
    return this.discussionListBuffer;
  }

  @Override public void logToExistingDiscussion(int discussionId)
  {
    remoteModel.logToExistingDiscussion(discussionId, id);
  }

  @Override public void searchDiscussionsByName(String name)
  {
    remoteModel.searchDiscussionsByName(name);
  }

  @Override public void searchDiscussionById(int discussionId)
  {
    remoteModel.searchDiscussionByID(discussionId);
  }

  @Override public int getSelectedDiscussion()
  {
    return this.selectedDiscussionId;
  }

  @Override public void selectDiscussion(int discussionId)
  {
    this.selectedDiscussionId = discussionId;
  }

  @Override public int searchDiscussionIdByLabel(String label)
  {
    for (int i = 0; i < discussionListBuffer.size(); i++)
    {
      if ((discussionListBuffer.getDiscussion(i).getDiscussionId() + "      "
          + discussionListBuffer.getDiscussion(i).getDiscussionName())
          .equals(label))
      {
        return discussionListBuffer.getDiscussion(i).getDiscussionId();
      }
    }
    return -1;
  }

  @Override public DiscussionList getLastSearchedDiscussions()
  {
    return this.lastSearched;
  }

  @Override public void changeNameOfDiscussion(int discussionId, String name)
  {
    this.remoteModel.changeNameOfDiscussion(discussionId, name);
  }

  @Override public void changePassword(String oldPassword, String newPassword)
  {
    this.remoteModel.changePassword(id, oldPassword, newPassword);
  }

  @Override public void changeLogin(String login)
  {
    this.remoteModel.changeLoginOfUser(id, login);
  }

  @Override public void removeItself()
  {
    this.remoteModel.removeUser(id);
  }

  @Override public String getUserType()
  {
    return userType;
  }
}

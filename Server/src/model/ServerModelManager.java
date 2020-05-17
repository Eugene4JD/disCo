package model;

import network.BroadcastMessageToDiscussionRequest;
import utility.Log;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ServerModelManager implements ServerModel
{
  private DiscussionList discussionList;
  private PropertyChangeSupport property;
  private UserBase userBase;
  private Log log;
  public ServerModelManager()
  {
    this.discussionList = new DiscussionList();
    this.userBase = new UserBase();
    this.log = Log.getInstance();
    property = new PropertyChangeSupport(this);
  }

  @Override public void addNewUserToUserBase(String userType, String login,
      String password)
  {
   userBase.addUser(userType, login, password);
  }

  @Override public void createNewDiscussion(String discussionId,String editorOfDiscussionLogin)
  {
   discussionList.createNewDiscussion(discussionId,userBase.getUserByLogin(editorOfDiscussionLogin));
  }

  @Override public Discussion getDiscussionById(String discussionId)
  {
   return discussionList.getDiscussionById(discussionId);
  }

  @Override public User getUserFromUserBaseByLogin(String login)
  {
   return userBase.getUserByLogin(login);
  }

  @Override public void removeDiscussion(String discussionId)
  {
    discussionList.removeDiscussionById(discussionId);
  }

  @Override public void removeUserFromUserBase(String userLogin)
  {
    userBase.removeUserById(userBase.getUserByLogin(userLogin).getUserId());
  }

  @Override public void addLog(String log)
  {
    this.log.addLog(log);
  }

  @Override public void addMessageToDiscussion(String discussionId,
      String sender, String message)
  {
    property.firePropertyChange("BroadcastMessageToDiscussion",null,new BroadcastMessageToDiscussionRequest(discussionId,sender,message));
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
}

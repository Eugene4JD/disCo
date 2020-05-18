package model;

import network.BroadcastMessageToDiscussionRequest;
import utility.Log;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class ServerModelManager implements ServerModel
{
  private DiscussionList discussionList;
  private DiscoPersistence discoPersistence;
  private PropertyChangeSupport property;
  private UserBase userBase;
  private Log log;
  public ServerModelManager()
  {
    this.discussionList = new DiscussionList();
    this.userBase = new UserBase();
    this.log = Log.getInstance();
    property = new PropertyChangeSupport(this);
    try
    {
      this.discoPersistence = new DiscoDatabase();
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    try
    {
      this.discussionList = discoPersistence.loadDiscussions();
      this.userBase = discoPersistence.loadUsers();
      discoPersistence.linkTheConnectionsBetween(discussionList,userBase);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void addNewUserToUserBase(String userType, String login,
      String password)
  {
    try
    {
      userBase.addUser(discoPersistence.saveUser(userType,login,password));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createNewDiscussion(String discussionName,String editorOfDiscussionLogin)
  {
    try
    {
      this.discussionList.addDiscussion(discoPersistence.saveDiscussion(discussionName,editorOfDiscussionLogin));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

  }
  @Override public Discussion getDiscussionById(int discussionId)
  {
   return discussionList.getDiscussionById(discussionId);
  }

  @Override public User getUserFromUserBaseByLogin(String login)
  {
   return userBase.getUserByLogin(login);
  }

  @Override public void removeDiscussion( String discussionName, String loginOfEditor)
  {
    discussionList.removeDiscussionByName(discussionName);
    try
    {
      discoPersistence.removeDiscussion(discussionName,loginOfEditor);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void removeUserFromUserBase(String userLogin)
  {
    userBase.removeUserById(userBase.getUserByLogin(userLogin).getUserId());
    try
    {
      discoPersistence.removeUser(userLogin,userBase.getUserByLogin(userLogin).getUserPassword());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addUserToDiscussion(int discussionID, int loginID)
  {
    try
    {
      discoPersistence.saveUserDiscussionConnection(discussionID,loginID);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
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

  @Override public Discussion getDiscussionByName(String name)
  {
    return discussionList.getDiscussionByName(name);
  }

  @Override public void removeDiscussionByName(String name)
  {
    discussionList.removeDiscussionByName(name);
  }
}

package model;

import utility.Log;

public class ServerModelManager implements ServerModel
{
  private DiscussionList discussionList;
  private UserBase userBase;
  private Log log;
  public ServerModelManager()
  {
    this.discussionList = new DiscussionList();
    this.userBase = new UserBase();
    this.log = Log.getInstance();
  }

  @Override public void addNewUserToUserBase(String userType, String login,
      String password)
  {
   userBase.addUser(userType, login, password);
  }

  @Override public void createNewDiscussion(String discussionId)
  {
   discussionList.createNewDiscussion(discussionId);
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
}

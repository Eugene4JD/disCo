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

  @Override public void addNewUserToUserBase(String userType, String Login,
      String Password)
  {

  }

  @Override public void createNewDiscussion(String DiscussionId)
  {

  }

  @Override public void getDiscussionById(String DiscussionId)
  {

  }

  @Override public void getUserFromUserBaseByLogin(String login)
  {

  }

  @Override public void removeDiscussion(String DiscussionId)
  {

  }

  @Override public void removeUserFromUserBase(String userLogin)
  {

  }

  @Override public void addLog(String log)
  {
    this.log.addLog(log);
  }
}

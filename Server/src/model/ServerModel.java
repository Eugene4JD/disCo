package model;

public interface ServerModel
{
  void createNewDiscussion(String DiscussionId);
  void removeDiscussion(String DiscussionId);
  void getDiscussionById(String DiscussionId);
  void addNewUserToUserBase(String userType, String Login, String Password);
  void removeUserFromUserBase(String userLogin);
  void getUserFromUserBaseByLogin(String login);
  void addLog(String log);

}

package model;

public interface ServerModel
{
  void createNewDiscussion(String discussionId);
  void removeDiscussion(String discussionId);
  Discussion getDiscussionById(String discussionId);
  void addNewUserToUserBase(String userType, String login, String password);
  void removeUserFromUserBase(String userLogin);
  User getUserFromUserBaseByLogin(String login);
  void addLog(String log);

}

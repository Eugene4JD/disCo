package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface ServerModel extends UnnamedPropertyChangeSubject
{
  Discussion createNewDiscussion(String discussionName,String editorOfDiscussion);
  void removeDiscussion(int discussionId, int userId);
  Discussion getDiscussionById(int discussionId);
  void addNewUserToUserBase(String userType, String login, String password);
  void removeUserFromUserBase(String userLogin);
  User getUserFromUserBaseByLogin(String login);
  void addMessageToDiscussion(int discussionId, int senderId, String message);
  void addLog(String log);
  void addUserToDiscussion(int discussionID, int loginID);
  DiscussionList getDiscussionsByName(String name);
  DiscussionList getDiscussionWithUser(int userID);
  void removeDiscussionByName(String name);
  Discussion getDiscussionWithCertainId(int id);


}

package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface ServerModel extends UnnamedPropertyChangeSubject
{
  void createNewDiscussion(String discussionName,String editorOfDiscussion);
  void removeDiscussion(String discussionName, String editorOfDiscussion);
  Discussion getDiscussionById(int discussionId);
  void addNewUserToUserBase(String userType, String login, String password);
  void removeUserFromUserBase(String userLogin);
  User getUserFromUserBaseByLogin(String login);
  void addMessageToDiscussion(String discussionId, String sender, String message);
  void addLog(String log);
  void addUserToDiscussion(int discussionID, int loginID);
  Discussion getDiscussionByName(String name);
  void removeDiscussionByName(String name);

}

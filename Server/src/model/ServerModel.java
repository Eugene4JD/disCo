package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface ServerModel extends UnnamedPropertyChangeSubject
{
  void createNewDiscussion(String discussionId,String editorOfDiscussionId);
  void removeDiscussion(String discussionId);
  Discussion getDiscussionById(String discussionId);
  void addNewUserToUserBase(String userType, String login, String password);
  void removeUserFromUserBase(String userLogin);
  User getUserFromUserBaseByLogin(String login);
  void addMessageToDiscussion(String discussionId, String sender, String message);
  void addLog(String log);

}

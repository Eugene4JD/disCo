package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface ServerModel extends UnnamedPropertyChangeSubject
{
  void createNewDiscussion(String discussionId,String editorOfDiscussionId);
  void removeDiscussion(int discussionId);
  Discussion getDiscussionById(int discussionId);
  void addNewUserToUserBase(int id ,String userType, String login, String password);
  void removeUserFromUserBase(String userLogin);
  User getUserFromUserBaseByLogin(String login);
  void addMessageToDiscussion(String discussionId, String sender, String message);
  void addLog(String log);
  Discussion getDiscussionByName(String name);
  void removeDiscussionByName(String name);

}

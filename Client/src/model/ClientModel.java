package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface ClientModel extends UnnamedPropertyChangeSubject
{
  void addDiscussions(DiscussionList discussionList);
  void addUsers(UserBase userBase);
  void addDiscussion(Discussion discussion);
  void addUser(User user);
  void createDiscussion(String discussionId);
  void removeDiscussion(String discussionId);
  void connect();
  void disconnect();
  void log(String login, String password, boolean isNewUser);
}

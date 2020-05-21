package model;

import utility.UnnamedPropertyChangeSubject;
import utility.ultimateUnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface ClientModel extends ultimateUnnamedPropertyChangeSubject
{
  void addDiscussions(DiscussionList discussionList);
  void addUsers(UserBase userBase);
  void addDiscussion(Discussion discussion);
  void addUser(User user);
  void createDiscussion(String discussionId);
  void removeDiscussion(int discussionId, int userId);
  void connect();
  void disconnect();
  void log(String login, String password, boolean isNewUser);
  int getDiscussionIdFromBuffer(String name);
  String getLogin();
  int getId();
}

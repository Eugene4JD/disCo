package model;

import java.beans.PropertyChangeListener;

public interface ClientModel extends PropertyChangeListener
{
  void addDiscussions(DiscussionList discussionList);
  void addUsers(UserBase userBase);
  void addDiscussion(Discussion discussion);
  void addUser(User user);
  void createDiscussion(String discussionId);
  void removeDiscussion(String discussionId);
  void connect();
  void disconnect();
}

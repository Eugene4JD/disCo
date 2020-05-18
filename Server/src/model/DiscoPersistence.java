package model;

public interface DiscoPersistence
{
  UserBase loadUsers();
  void saveUsers(UserBase userBase);
  void saveUser (User user);
  void removeUser(User user);
  void clearUsers();
  void saveDiscussions(DiscussionList discussionList);
  void saveDiscussion(Discussion discussion);
  void removeDiscussion(Discussion discussion);
  void clearDiscussions();
  void lingTheConnectionsBetween(DiscussionList discussionList, UserBase userBase);

}

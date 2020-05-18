package model;

import java.sql.SQLException;

public interface DiscoPersistence
{
  UserBase loadUsers() throws SQLException;
  DiscussionList loadDiscussions() throws SQLException;
  void saveUsers(UserBase userBase) throws SQLException;
  void saveUser (User user) throws SQLException;
  void removeUser(User user) throws SQLException;
  void clearUsers() throws  SQLException;
  void saveDiscussions(DiscussionList discussionList) throws SQLException;
  void saveDiscussion(Discussion discussion) throws SQLException;
  void removeDiscussion(Discussion discussion) throws SQLException;
  void clearDiscussions() throws SQLException;
  void linkTheConnectionsBetween(DiscussionList discussionList, UserBase userBase) throws SQLException;

}

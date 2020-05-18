package model;

import java.sql.SQLException;

public interface DiscoPersistence
{
  UserBase loadUsers() throws SQLException;
  DiscussionList loadDiscussions() throws SQLException;
  User saveUser (String userType,String login, String password) throws SQLException;
  void removeUser(User user) throws SQLException;
  void clearUsers() throws  SQLException;
  Discussion saveDiscussion(String discussionName, String loginOfEditor) throws SQLException;
  void removeDiscussion(Discussion discussion) throws SQLException;
  void clearDiscussions() throws SQLException;
  void linkTheConnectionsBetween(DiscussionList discussionList, UserBase userBase) throws SQLException;

}

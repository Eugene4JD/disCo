package model;

import java.sql.SQLException;

public interface DiscoPersistence
{
  UserBase loadUsers() throws SQLException;
  DiscussionList loadDiscussions() throws SQLException;
  User saveUser(String userType, String login, String password)
      throws SQLException;
  void removeUser(int userId) throws SQLException;
  void clearUsers() throws SQLException;
  Discussion saveDiscussion(String discussionName, String loginOfEditor)
      throws SQLException;
  void removeDiscussion(int discussionID, int editorId) throws SQLException;
  void clearDiscussions() throws SQLException;
  void linkTheConnectionsBetween(DiscussionList discussionList,
      UserBase userBase) throws SQLException;
  void saveUserDiscussionConnection(int discussionID, int userID)
      throws SQLException;
  void editUserLogin(int userId, String newLogin) throws SQLException;
  void editUserPassword(int userId, String newPassword) throws SQLException;
  void editNameOfDiscussion(int discussionId, String discussionName)
      throws SQLException;
  void changeEditorLoginInEveryDiscussion(String oldEditorLogin,
      String newEditorLogin) throws SQLException;

}

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
  void removeDiscussion(int discussionId);
  void sendMessageToDiscussion(int discussionID, int userId, String message);
  void connect();
  void disconnect();
  void log(String login, String password, boolean isNewUser);
  int getDiscussionIdFromBuffer(String name);
  String getLogin();
  int getId();
  String getUserType();
  DiscussionList getDiscussionListBuffer();
  void logToExistingDiscussion(int discussionId);
  void searchDiscussionById(int discussionId);
  void searchDiscussionsByName(String name);
  void selectDiscussion(int discussionId);
  int getSelectedDiscussion();
  int searchDiscussionIdByLabel(String label);
  DiscussionList getLastSearchedDiscussions();
  void changeLogin(String login);
  void changePassword(String oldPassword, String newPassword);
  void changeNameOfDiscussion(int discussionId, String name);
  void removeItself();

}

package mediator;

import model.Discussion;

import java.io.IOException;

public interface RemoteModel
{
  void connect() throws IOException;
  void disconnect() throws IOException;
  void log(String login, String password, boolean isNewUser,boolean isGuest);
  void logToExistingDiscussion(int discussionId, int userId);
  void createNewDiscussion(String discussionId, int userId);
  void sendMessageInDiscussion(int discussionId, int sender, String message);
  void removeDiscussion(int discussionId, int userId);
  void searchDiscussionByID(int id);
  void searchDiscussionsByName(String name);
  void removeUser(int userId);
  void changeLoginOfUser(int userId, String newLogin);
  void changeNameOfDiscussion(int discussionId, String discussionName);
  void changePassword(int userId, String oldPassword, String newPassword);

}

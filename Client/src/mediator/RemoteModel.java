package mediator;


import java.io.IOException;

public interface RemoteModel
{
  void connect() throws IOException;
  void disconnect() throws IOException;
  void log(String login, String password, boolean isNewUser);
  void logToExistingDiscussion(String discussionId,String login);
  void createNewDiscussion(String discussionId,String loginOfEditor);
  void sendMessageInDiscussion(String discussionId, String sender, String message);
  void removeDiscussion(int discussionId, int userId);

}

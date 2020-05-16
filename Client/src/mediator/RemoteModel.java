package mediator;


import java.io.IOException;

public interface RemoteModel
{
  void connect() throws IOException;
  void disconnect() throws IOException;
  void log(String login, String password, boolean isNewUser);
  void logToExistingDiscussion(String discussionId);
  void createNewDiscussion(String discussionId);
  void sendMessageInDiscussion();
  void removeDiscussion();

}

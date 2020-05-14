package model;

public class Discussion
{
  private MessageList messageList;
  private UserBase userBase;

  public Discussion()
  {
    messageList = new MessageList();
    userBase = new UserBase();
  }

  private void addUser(User user)
  {
    userBase.addUser(user);
  }
  private void addUser(String type, String login, String password)
  {
    userBase.addUser(type,login,password);
  }
  private void removeUserById(String userId)
  {
    userBase.removeUserById(userId);
  }
  private User getUserIdByLogin(String userId)
  {
    return userBase.getUserById(userId);
  }

  private void addMessage(String message, String login)
  {
    messageList.addMessage(message,login);
  }
}

package model;

public class Discussion
{
  private MessageList messageList;
  private UserBase userBase;
  private String discussionId;
  private User editorOfDiscussion;

  public Discussion(String discussionId,User editorOfDiscussion)
  {
    messageList = new MessageList();
    userBase = new UserBase();
    this.editorOfDiscussion = editorOfDiscussion;
  }

  public void addUser(User user)
  {
    userBase.addUser(user);
  }
  public  void addUser(String type, String login, String password)
  {
    userBase.addUser(type,login,password);
  }
  public void removeUserById(String userId)
  {
    userBase.removeUserById(userId);
  }
  public User getUserIdByLogin(String userId)
  {
    return userBase.getUserById(userId);
  }

  public void addMessage(String message, String login)
  {
    messageList.addMessage(message,login);
  }
  public  MessageList getMessageList()
  {
    return messageList;
  }
  public UserBase getUserBase()
  {
    return userBase;
  }
  public String getDiscussionId()
  {
    return discussionId;
  }

  public User getEditorOfDiscussion()
  {
    return editorOfDiscussion;
  }
}

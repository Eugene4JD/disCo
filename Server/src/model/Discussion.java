package model;

public class Discussion
{
  private MessageList messageList;
  private UserBase userBase;
  private int discussionId;
  private String discussionName;
  private String LoginOfEditorOfDiscussion;

  public Discussion(int discussionId, String discussionName,
      String editorOfDiscussion)
  {
    messageList = new MessageList();
    userBase = new UserBase();
    this.LoginOfEditorOfDiscussion = editorOfDiscussion;
    this.discussionId = discussionId;
    this.discussionName = discussionName;
  }

  public void addUser(User user)
  {
    userBase.addUser(user);
  }

  public void addUser(int id, String type, String login, String password)
  {
    userBase.addUser(id, type, login, password);
  }

  public void removeUserById(int userId)
  {
    userBase.removeUserById(userId);
  }

  public User getUserIdByLogin(int userId)
  {
    return userBase.getUserById(userId);
  }

  public void addMessage(String message, String login)
  {
    messageList.addMessage(message, login);
  }

  public MessageList getMessageList()
  {
    return messageList;
  }

  public UserBase getUserBase()
  {
    return userBase;
  }

  public String getDiscussionName()
  {
    return discussionName;
  }

  public int getDiscussionId()
  {
    return discussionId;
  }

  public String getLoginOfEditorOfDiscussion()
  {
    return this.LoginOfEditorOfDiscussion;
  }

}

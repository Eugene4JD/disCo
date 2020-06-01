package model;

/**
 * A class representing discussion
 *
 * @author Group2
 * @version 1.1
 */
public class Discussion
{
  private MessageList messageList;
  private UserBase userBase;
  private int discussionId;
  private String discussionName;
  private String LoginOfEditorOfDiscussion;

  /**
   * Three arguments constructor setting the discussion id, discussion name and editor of discussion
   *
   * @param discussionId       the discussion id
   * @param discussionName     the discussion name
   * @param editorOfDiscussion the editor of discussion
   */
  public Discussion(int discussionId, String discussionName,
      String editorOfDiscussion)
  {
    messageList = new MessageList();
    userBase = new UserBase();
    this.LoginOfEditorOfDiscussion = editorOfDiscussion;
    this.discussionId = discussionId;
    this.discussionName = discussionName;
  }

  /**
   * Adding user to this discussion userBase
   *
   * @param user the user
   */
  public void addUser(User user)
  {
    userBase.addUser(user);
  }

  /**
   * Adding user with attributes which will be used when creating this new user in userBase
   *
   * @param id       the id of user
   * @param type     the type of user
   * @param login    the login of user
   * @param password the password of user
   */
  public void addUser(int id, String type, String login, String password)
  {
    userBase.addUser(id, type, login, password);
  }

  /**
   * removing user from the discussion userBase by userID
   *
   * @param userId the user Id
   */
  public void removeUserById(int userId)
  {
    userBase.removeUserById(userId);
  }

  /**
   * the user by given userId from this discussion UserBase
   *
   * @param userId user Id
   * @return the user by given userId from this discussion UserBase
   */
  public User getUserIdByLogin(int userId)
  {
    return userBase.getUserById(userId);
  }

  /**
   * adding the message to the Message list of this discussion
   *
   * @param message   string containing the message itself
   * @param messageId the message id
   */
  public void addMessage(String message, int messageId)
  {
    messageList.addMessage(message, messageId);
  }

  /**
   * returns message list of this discussion
   *
   * @return message list of this discussion
   */
  public MessageList getMessageList()
  {
    return messageList;
  }

  /**
   * returns user Base of this discussion
   *
   * @return user Base of this discussion
   */
  public UserBase getUserBase()
  {
    return userBase;
  }

  /**
   * returns Discussion name
   *
   * @return Discussion name
   */
  public String getDiscussionName()
  {
    return discussionName;
  }

  /**
   * returns discussion Id
   *
   * @return discussion id
   */
  public int getDiscussionId()
  {
    return discussionId;
  }

  /**
   * sets the name of this discussion to given
   *
   * @param discussionName discussion
   */
  public void setDiscussionName(String discussionName)
  {
    this.discussionName = discussionName;
  }

  /**
   * returns login of editor of this discussion
   *
   * @return
   */
  public String getLoginOfEditorOfDiscussion()
  {
    return this.LoginOfEditorOfDiscussion;
  }

}

package network;

public class LogToExistingDiscussionRequest extends Request
{
  private int discussionId;
  private String userLogin;

  public LogToExistingDiscussionRequest(int discussionId, String userLogin)
  {
    super(RequestType.LogToExistingDiscussion);
    this.discussionId = discussionId;
    this.userLogin = userLogin;
  }

  public int getDiscussionId()
  {
    return discussionId;
  }

  public String getUserLogin()
  {
    return userLogin;
  }
}

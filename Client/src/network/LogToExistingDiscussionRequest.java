package network;

public class LogToExistingDiscussionRequest extends Request
{
  private String discussionId;
  private String userLogin;

  public LogToExistingDiscussionRequest(String discussionId, String userLogin)
  {
    super(RequestType.LogToExistingDiscussion);
    this.discussionId = discussionId;
    this.userLogin = userLogin;
  }

  public String getDiscussionId()
  {
    return discussionId;
  }

  public String getUserLogin()
  {
    return userLogin;
  }
}
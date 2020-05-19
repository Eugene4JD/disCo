package network;

public class LogToExistingDiscussionRequest extends Request
{
  private String discussionName;
  private String userLogin;

  public LogToExistingDiscussionRequest(String discussionName, String userLogin)
  {
    super(RequestType.LogToExistingDiscussion);
    this.discussionName = discussionName;
    this.userLogin = userLogin;
  }

  public String getDiscussionName()
  {
    return discussionName;
  }

  public String getUserLogin()
  {
    return userLogin;
  }
}

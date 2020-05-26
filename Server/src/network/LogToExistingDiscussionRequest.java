package network;

public class LogToExistingDiscussionRequest extends Request
{
  private int discussionId;
  private int userId;

  public LogToExistingDiscussionRequest(int discussionId, int userId)
  {
    super(RequestType.LogToExistingDiscussion);
    this.discussionId = discussionId;
    this.userId = userId;
  }

  public int getDiscussionId()
  {
    return discussionId;
  }

  public int getUserId()
  {
    return userId;
  }
}

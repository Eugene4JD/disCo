package network;

public class RemoveDiscussionRequest extends Request
{

  private int discussionId;
  private int userId;

  public RemoveDiscussionRequest(int discussionId, int userId)
  {
    super(RequestType.RemoveDiscussion);
    this.userId = userId;
    this.discussionId = discussionId;
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

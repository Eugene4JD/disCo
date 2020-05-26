package network;

public class CreateDiscussionRequest extends Request
{
  private String discussionId;
  private int userId;

  public CreateDiscussionRequest(String discussionId, int userId)
  {
    super(RequestType.CreateDiscussion);
    this.discussionId = discussionId;
    this.userId = userId;
  }

  public String getDiscussionId()
  {
    return discussionId;
  }

  public int getUserID()
  {
    return userId;
  }
}

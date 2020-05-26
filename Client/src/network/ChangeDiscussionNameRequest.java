package network;

public class ChangeDiscussionNameRequest extends Request
{
  private int discussionId;
  private String discussionName;

  public ChangeDiscussionNameRequest(int discussionId, String discussionName)
  {
    super(RequestType.ChangeDiscussionName);
    this.discussionId = discussionId;
    this.discussionName = discussionName;
  }

  public int getDiscussionId()
  {
    return discussionId;
  }

  public String getDiscussionName()
  {
    return discussionName;
  }
}

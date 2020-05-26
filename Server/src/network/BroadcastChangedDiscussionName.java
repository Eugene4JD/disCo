package network;

public class BroadcastChangedDiscussionName extends Request
{

  private int discussionId;
  private String discussionName;

  public BroadcastChangedDiscussionName(int discussionId, String discussionName)
  {
    super(RequestType.BroadcastChangeDiscussionName);
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

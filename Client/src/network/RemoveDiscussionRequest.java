package network;

public class RemoveDiscussionRequest extends Request
{
  private String discussionId;
  public RemoveDiscussionRequest(String discussionId)
  {
   super(RequestType.RemoveDiscussion);
   this.discussionId = discussionId;
  }

  public String getDiscussionId()
  {
    return discussionId;
  }
}

package network;

public class BroadcastRemovingDiscussionToUserRequest extends Request
{
  String discussionId;
  public BroadcastRemovingDiscussionToUserRequest(String discussionId)
  {
    super(RequestType.BroadcastRemovingDiscussion);
    this.discussionId =discussionId;
  }

  public String getDiscussionId()
  {
    return discussionId;
  }
}

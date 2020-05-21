package network;

public class BroadcastRemovingDiscussionToUserRequest extends Request
{
  int discussionId;
  public BroadcastRemovingDiscussionToUserRequest(int discussionId)
  {
    super(RequestType.BroadcastRemovingDiscussion);
    this.discussionId =discussionId;
  }

  public int getDiscussionId()
  {
    return discussionId;
  }
}

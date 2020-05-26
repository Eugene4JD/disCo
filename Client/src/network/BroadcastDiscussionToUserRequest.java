package network;

import model.Discussion;

public class BroadcastDiscussionToUserRequest extends Request
{
  private Discussion discussion;
  private int userId;

  public BroadcastDiscussionToUserRequest(Discussion discussion, int id)
  {
    super(RequestType.BroadcastDiscussion);
    this.discussion = discussion;
    this.userId = id;
  }

  public Discussion getDiscussion()
  {
    return discussion;
  }

  public int getUserId()
  {
    return userId;
  }
}

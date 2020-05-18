package network;

import model.Discussion;

public class BroadcastDiscussionToUserRequest extends Request
{
  private Discussion discussion;
  public BroadcastDiscussionToUserRequest(Discussion discussion)
  {
    super(RequestType.BroadcastDiscussion);
    this.discussion = discussion;
  }

  public Discussion getDiscussion()
  {
    return discussion;
  }
}

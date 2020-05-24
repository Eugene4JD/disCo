package network;

import model.Discussion;

public class BroadcastSearchedDiscussionToUser extends Request
{
  private Discussion discussion;
  public BroadcastSearchedDiscussionToUser(Discussion discussion)
  {
    super(RequestType.BroadcastSearchedDiscussion);
    this.discussion =discussion;
  }
  public Discussion getDiscussion()
  {
    return this.discussion;
  }
}

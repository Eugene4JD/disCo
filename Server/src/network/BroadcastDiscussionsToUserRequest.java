package network;

import model.DiscussionList;

public class BroadcastDiscussionsToUserRequest extends Request
{
  private DiscussionList discussions;
  public BroadcastDiscussionsToUserRequest()
  {
    super(RequestType.BroadcastDiscussions);
    this.discussions = new DiscussionList();
  }
  public DiscussionList getDiscussions()
  {
    return this.discussions;
  }
}

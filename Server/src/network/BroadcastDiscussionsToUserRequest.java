package network;

import model.DiscussionList;

public class BroadcastDiscussionsToUserRequest extends Request
{
  private DiscussionList discussions;

  public BroadcastDiscussionsToUserRequest(DiscussionList discussionList)
  {
    super(RequestType.BroadcastDiscussions);
    this.discussions = discussionList;
  }

  public DiscussionList getDiscussions()
  {
    return this.discussions;
  }
}

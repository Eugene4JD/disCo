package network;

import model.DiscussionList;

public class BroadcastSearchedDiscussionsToUser extends Request
{
  private DiscussionList discussionList;
 public BroadcastSearchedDiscussionsToUser(DiscussionList discussionList)
 {
   super(RequestType.BroadcastSearchedDiscussions);
   this.discussionList = discussionList;
 }

 public DiscussionList getDiscussionList()
 {
   return this.discussionList;
 }
}

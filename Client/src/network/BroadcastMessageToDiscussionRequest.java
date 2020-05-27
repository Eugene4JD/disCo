package network;

import model.Message;

public class BroadcastMessageToDiscussionRequest extends Request
{
  private int discussionID;
  private Message message;

  public BroadcastMessageToDiscussionRequest(int discussionID, Message message)
  {
    super(RequestType.BroadcastMessage);
    this.discussionID = discussionID;
    this.message = message;
  }

  public Message getMessage()
  {
    return message;
  }

  public int getDiscussionID()
  {
    return discussionID;
  }
}

package network;

public class BroadcastMessageToDiscussionRequest extends Request
{
  private int discussionID;
  private String message;
  private int senderID;

  public BroadcastMessageToDiscussionRequest(int discussionID, int senderID,
      String message)
  {
    super(RequestType.BroadcastMessage);
    this.discussionID = discussionID;
    this.senderID = senderID;
    this.message = message;
  }

  public int getDiscussionID()
  {
    return discussionID;
  }

  public String getMessage()
  {
    return message;
  }

  public int getSenderID()
  {
    return senderID;
  }
}

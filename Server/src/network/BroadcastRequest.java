package network;

public class BroadcastRequest extends Request
{
  private String discussionID;
  private String message;
  private String sender;

  public BroadcastRequest(String discussionID, String sender, String message)
  {
    super(RequestType.Broadcast);
    this.discussionID = discussionID;
    this.sender = sender;
    this.message = message;
  }

  public String getDiscussionID()
  {
    return discussionID;
  }

  public String getMessage()
  {
    return message;
  }

  public String getSender()
  {
    return sender;
  }
}

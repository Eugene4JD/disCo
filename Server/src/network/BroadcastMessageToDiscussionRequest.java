package network;

public class BroadcastMessageToDiscussionRequest extends Request
{
  private String discussionID;
  private String message;
  private String sender;

  public BroadcastMessageToDiscussionRequest(String discussionID, String sender, String message)
  {
    super(RequestType.BroadcastMessage);
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

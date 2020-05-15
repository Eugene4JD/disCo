package network;

public class MessageRequest extends Request
{
  private String sender;
  private String message;
  private String discussionId;
  public MessageRequest(String discussionId, String sender, String message)
  {
    super(RequestType.Message);
    this.discussionId = discussionId;
    this.message = message;
    this.sender = sender;
  }

  public String getSender()
  {
    return sender;
  }

  public String getMessage()
  {
    return message;
  }

  public String getDiscussionId()
  {
    return discussionId;
  }
}

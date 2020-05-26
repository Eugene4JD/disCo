package network;

public class MessageRequest extends Request
{
  private int senderID;
  private String message;
  private int discussionId;

  public MessageRequest(int discussionId, int userId, String message)
  {
    super(RequestType.Message);
    this.discussionId = discussionId;
    this.message = message;
    this.senderID = userId;
  }

  public int getSenderID()
  {
    return senderID;
  }

  public String getMessage()
  {
    return message;
  }

  public int getDiscussionId()
  {
    return discussionId;
  }
}

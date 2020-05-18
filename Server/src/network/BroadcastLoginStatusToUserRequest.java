package network;

public class BroadcastLoginStatusToUserRequest extends Request
{
  private boolean isLogSuccessful;

  public BroadcastLoginStatusToUserRequest(Boolean isLogSuccessful)
  {
    super(RequestType.BroadcastLoginStatus);
    this.isLogSuccessful = isLogSuccessful;
  }

  public boolean isLogSuccessful()
  {
    return this.isLogSuccessful;
  }
}

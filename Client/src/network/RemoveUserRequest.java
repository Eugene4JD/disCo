package network;

public class RemoveUserRequest extends Request
{
  private int userId;
  public RemoveUserRequest(int userId)
  {
    super(RequestType.RemoveUserRequest);
    this.userId = userId;
  }

  public int getUserId()
  {
    return userId;
  }
}

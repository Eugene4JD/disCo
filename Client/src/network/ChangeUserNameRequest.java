package network;

public class ChangeUserNameRequest extends Request
{
  private String newUserName;
  private int UserId;

  public ChangeUserNameRequest(int UserId, String newUserName)
  {
    super(RequestType.ChangeUserNameRequest);
    this.UserId = UserId;
    this.newUserName =newUserName;
  }

  public String getNewUserName()
  {
    return newUserName;
  }

  public int getUserId()
  {
    return UserId;
  }
}

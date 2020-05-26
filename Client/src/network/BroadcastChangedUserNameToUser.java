package network;

public class BroadcastChangedUserNameToUser extends Request
{
  private String newUserName;

  public BroadcastChangedUserNameToUser(String newUserName)
  {
    super(RequestType.BroadcastChangeUserNameToUser);
    this.newUserName = newUserName;
  }

  public String getNewUserName()
  {
    return newUserName;
  }
}

package network;

public class BroadcastLoginStatusToUserRequest extends Request
{
  private boolean isLogSuccessful;
  private String login;

  public BroadcastLoginStatusToUserRequest(Boolean isLogSuccessful,String login)
  {
    super(RequestType.BroadcastLoginStatus);
    this.isLogSuccessful = isLogSuccessful;
    this.login = login;
  }

  public boolean isLogSuccessful()
  {
    return this.isLogSuccessful;
  }

  public String getLogin()
  {
    return login;
  }
}

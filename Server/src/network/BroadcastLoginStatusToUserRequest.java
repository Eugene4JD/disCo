package network;

public class BroadcastLoginStatusToUserRequest extends Request
{
  private boolean isLogSuccessful;
  private String login;
  private int id;

  public BroadcastLoginStatusToUserRequest(Boolean isLogSuccessful,
      String login, int id)
  {
    super(RequestType.BroadcastLoginStatus);
    this.isLogSuccessful = isLogSuccessful;
    this.login = login;
    this.id = id;
  }

  public boolean isLogSuccessful()
  {
    return this.isLogSuccessful;
  }

  public String getLogin()
  {
    return login;
  }

  public int getId()
  {
    return id;
  }
}

package network;

public class BroadcastLoginStatusToUserRequest extends Request
{
  private boolean isLogSuccessful;
  private String login;
  private int id;
  private String typeOfUser;

  public BroadcastLoginStatusToUserRequest(Boolean isLogSuccessful,
      String login, int id, String typeOfUser)
  {
    super(RequestType.BroadcastLoginStatus);
    this.isLogSuccessful = isLogSuccessful;
    this.login = login;
    this.id = id;
    this.typeOfUser = typeOfUser;
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

  public String getTypeOfUser()
  {
    return this.typeOfUser;
  }
}

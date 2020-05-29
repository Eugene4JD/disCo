package network;

public class BroadcastLoginStatusToUserRequest extends Request
{
  private boolean isLogSuccessful;
  private String login;
  private int id;
  private String typeOfUser;
  private boolean isGuest;

  public BroadcastLoginStatusToUserRequest(Boolean isLogSuccessful,
      String login, int id, String typeOfUser, boolean isGuest)
  {
    super(RequestType.BroadcastLoginStatus);
    this.isLogSuccessful = isLogSuccessful;
    this.login = login;
    this.id = id;
    this.typeOfUser = typeOfUser;
    this.isGuest = isGuest;
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

  public boolean isGuest()
  {
    return isGuest;
  }
}

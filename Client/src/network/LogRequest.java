package network;

public class LogRequest extends Request
{
  private String login;
  private String password;
  private boolean isNewUser;
  private boolean isGuest;

  public LogRequest(String login, String password, boolean isNewUser, boolean isGuest)
  {
    super(RequestType.Log);
    this.login = login;
    this.password = password;
    this.isNewUser = isNewUser;
    this.isGuest = isGuest;
  }

  public String getLogin()
  {
    return login;
  }

  public String getPassword()
  {
    return password;
  }

  public boolean isNewUser()
  {
    return this.isNewUser;
  }

  public boolean isGuest()
  {
    return isGuest;
  }
}

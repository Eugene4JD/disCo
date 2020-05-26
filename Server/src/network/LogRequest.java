package network;

public class LogRequest extends Request
{
  private String login;
  private String password;
  private boolean isNewUser;

  public LogRequest(String login, String password, boolean isNewUser)
  {
    super(RequestType.Log);
    this.login = login;
    this.password = password;
    this.isNewUser = isNewUser;
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
}

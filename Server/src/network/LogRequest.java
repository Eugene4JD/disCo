package network;

public class LogRequest extends Request
{
  private String login;
  private String password;

  public LogRequest(String login, String password)
  {
    super(RequestType.Log);
  }

  public String getLogin()
  {
    return login;
  }

  public String getPassword()
  {
    return password;
  }
}

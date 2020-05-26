package network;

public class BroadcastChangedPasswordToUser extends Request
{

  private boolean successOfChangingPassword;

  public BroadcastChangedPasswordToUser(boolean successOfChangingPassword)
  {
    super(RequestType.BroadcastChangePasswordToUser);
    this.successOfChangingPassword = successOfChangingPassword;
  }

  public boolean isSuccessOfChangingPassword()
  {
    return this.successOfChangingPassword;
  }
}

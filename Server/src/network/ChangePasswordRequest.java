package network;

public class ChangePasswordRequest extends Request
{
  private String oldPassword;
  private String newPassword;
  private int userId;

  public ChangePasswordRequest(int userID, String oldPassword,
      String newPassword)
  {
    super(RequestType.BroadcastChangePasswordToUser);
    this.newPassword = newPassword;
    this.oldPassword = oldPassword;
    this.userId = userID;
  }

  public int getUserId()
  {
    return userId;
  }

  public String getNewPassword()
  {
    return newPassword;
  }

  public String getOldPassword()
  {
    return oldPassword;
  }
}


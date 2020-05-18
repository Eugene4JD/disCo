package model;

import java.io.Serializable;

public  class User implements Serializable
{
  private int userId;
  private String userType;
  private String userLogin;
  private String userPassword;

  public User(int userId, String userType, String userLogin, String userPassword)
  {
    this.userType = userType;
    this.userId = userId;
    this.userLogin = userLogin;
    this.userPassword = userPassword;
  }

  public int getUserId()
  {
    return userId;
  }
  public String getUserType()
  {
    return userType;
  }
  public String getUserLogin()
  {
    return userLogin;
  }
  public String getUserPassword()
  {
    return userPassword;
  }

  public void setUserLogin(String userLogin)
  {
    this.userLogin = userLogin;
  }

  public void setUserPassword(String userPassword)
  {
    this.userPassword = userPassword;
  }

  @Override public String toString()
  {
    return userId + " " + userType;
  }
}

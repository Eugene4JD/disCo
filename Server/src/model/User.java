package model;

import java.io.Serializable;

/**
 * Class representing user
 *
 * @author Group 2
 * @version 1.1
 */
public class User implements Serializable
{
  private int userId;
  private String userType;
  private String userLogin;
  private String userPassword;

  /**
   * 4 arguments constructor
   *
   * @param userId       id of new user
   * @param userType     user Type
   * @param userLogin    user Login
   * @param userPassword user password
   */
  public User(int userId, String userType, String userLogin,
      String userPassword)
  {
    this.userType = userType;
    this.userId = userId;
    this.userLogin = userLogin;
    this.userPassword = userPassword;
  }

  /**
   * returns user id
   *
   * @return id of the user
   */
  public int getUserId()
  {
    return userId;
  }

  /**
   * returns type of the user
   *
   * @return type of the user
   */
  public String getUserType()
  {
    return userType;
  }

  /**
   * returns the login of the user
   *
   * @return login of the user
   */
  public String getUserLogin()
  {
    return userLogin;
  }

  /**
   * returns the password of the user
   *
   * @return the user password
   */
  public String getUserPassword()
  {
    return userPassword;
  }

  /**
   * set User login  to the given login
   *
   * @param userLogin user login
   */
  public void setUserLogin(String userLogin)
  {
    this.userLogin = userLogin;
  }

  /**
   * set User password to the password given
   *
   * @param userPassword
   */
  public void setUserPassword(String userPassword)
  {
    this.userPassword = userPassword;
  }

  /**
   * returns String interpretation of the user
   *
   * @return String interpretation of the user
   */
  @Override public String toString()
  {
    return userId + " " + userType;
  }
}

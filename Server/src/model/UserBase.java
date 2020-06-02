package model;

import java.util.ArrayList;

/**
 * Class representing user base
 *
 * @author Group 2
 * @version 1.1
 */
public class UserBase
{

  private ArrayList<User> users;

  /**
   * Zero arguments constructor
   */
  public UserBase()
  {
    this.users = new ArrayList<>();
  }

  /**
   * Adding user to userBase ,if the login has been already been
   *
   * @param user user
   */
  public void addUser(User user)
  {
    if (isLoginInBase(user.getUserLogin()))
    {
      throw new IllegalArgumentException(
          "Login is in the system... Check again");
    }
    this.users.add(user);
  }

  /**
   * adding new user to the user base by using given values to create it
   *
   * @param userId   user id
   * @param userType user type
   * @param login    user login
   * @param password user password
   */
  public void addUser(int userId, String userType, String login,
      String password)
  {
    this.users.add(new User(userId, userType, login, password));
  }

  /**
   * removing the user from user base by given id
   *
   * @param id given user id
   */
  public void removeUserById(int id)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUserId() == id)
      {
        users.remove(i);
        break;
      }
    }
  }

  /**
   * getting the user by given id from userBase
   *
   * @param userId given user id
   * @return User
   */
  public User getUserById(int userId)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUserId() == userId)
        return users.get(i);
    }
    return null;
  }

  /**
   * get User from userBase by a given login
   *
   * @param login given user login
   * @return User
   */

  public User getUserByLogin(String login)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUserLogin().equals(login))
        return users.get(i);
    }
    return null;
  }

  /**
   * removing all users from userBase
   */
  public void removeAllUserFromChat()
  {
    int s = users.size();
    for (int i = 0; i < s; i++)
    {
      users.remove(0);
    }
  }

  /**
   * get user by its index in an array list
   *
   * @param i given index
   * @return user
   */
  public User getUser(int i)
  {
    return users.get(i);
  }

  /**
   * return the size of the user list
   *
   * @return the user list size
   */
  public int size()
  {
    return this.users.size();
  }

  /**
   * Checks if user with given login is in base
   *
   * @param login given login
   * @return true if user with this login in base and false if he is not
   */
  public boolean isLoginInBase(String login)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUserLogin().equals(login))
        return true;
    }
    return false;
  }

  /**
   * Checks if user is logged in the userBase with given id
   *
   * @param id User id
   * @return true if user is logged and false if he is not
   */
  public boolean isLogged(int id)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUserId() == id)
      {
        return true;
      }
    }
    return false;
  }
}

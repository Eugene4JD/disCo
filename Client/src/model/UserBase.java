package model;

import java.util.ArrayList;

public class UserBase
{

  private ArrayList<User> users;
  private int index;

  public UserBase()
  {
    this.users = new ArrayList<>();
    this.index = 0;
  }

  public void addUser(User user)
  {
    if (isLoginInBase(user.getUserLogin()))
    {
      throw  new IllegalArgumentException("Login is in the system... Check again");
    }
    this.users.add(user);
  }

  public void addUser(int userId, String userType,String login, String password)
  {
    this.users.add(new User(userId,userType,login,password));
    index ++;
  }

  public void removeUserById(int id)
  {
    for (int i = 0; i<users.size(); i++)
    {
      if (users.get(i).getUserId() == id)
      {
        users.remove(i);
        break;
      }
    }
  }

  public User getUserById(int userId)
  {
    for (int i = 0; i<users.size(); i++)
    {
      if (users.get(i).getUserId() == userId)
        return users.get(i);
    }
    return null;
  }

  public User getUserByLogin(String login)
 {
   for (int i = 0; i< users.size(); i++)
   {
     if (users.get(i).getUserLogin().equals(login))
       return users.get(i);
   }
   return null;
 }
  public void removeAllUserFromChat()
  {
    int s = users.size();
    for (int i =0; i<s; i++)
    {
      users.remove(0);
    }
   index = 0;
  }


  public String getUserId()
  {
    return Integer.toString(index);
  }

  public User getUser(int i)
  {
    return users.get(i);
  }

  public int size()
  {
    return this.users.size();
  }


  public boolean isLoginInBase(String login)
  {
    for (int i =0; i< users.size(); i++)
    {
      if (users.get(i).getUserLogin().equals(login))
         return true;
    }
    return false;
  }
}

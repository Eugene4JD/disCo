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

  public void addUser(String userType,String login, String password)
  {
    this.users.add(new User(getUserId(),userType,login,password));
    index ++;
  }

  public void removeUserById(String id)
  {
    for (int i = 0; i<users.size(); i++)
    {
      if (users.get(i).getUserId().equals(id))
      {
        users.remove(i);
        break;
      }
    }
  }

  public User getUserById(String userId)
  {
    for (int i = 0; i<users.size(); i++)
    {
      if (users.get(i).getUserId().equals(userId))
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


  private String getUserId()
  {
    return Integer.toString(index);
  }


  private boolean isLoginInBase(String login)
  {
    for (int i =0; i< users.size(); i++)
    {
      if (users.get(i).getUserLogin().equals(login))
         return true;
    }
    return false;
  }
}

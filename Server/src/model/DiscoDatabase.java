package model;

import utility.persistence.MyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class DiscoDatabase implements DiscoPersistence
{

  private MyDatabase db;

  public DiscoDatabase() throws ClassNotFoundException
  {
      this.db = new MyDatabase("org.postgresql.Driver" ,"jdbc:postgresql://localhost:5432/postgres", "postgres", "3228");
  }
  @Override public UserBase loadUsers() throws SQLException
  {
    String sql = "Select * From DisCoDB.userBase";
    ArrayList<Object[]> result = db.query(sql);
    UserBase userBase = new UserBase();
    for (int i =0; i < result.size(); i++)
    {
      Object[] row = result.get(i);
      userBase.addUser((int)row[0],(String)row[3],(String)row[1],(String)row[2]);
    }
    return userBase;
  }

  @Override public DiscussionList loadDiscussions() throws SQLException
  {
    String sql ="Select * From DisCoDB.DiscussionList";
    ArrayList<Object[]> result = db.query(sql);
    DiscussionList discussions = new DiscussionList();
    for (int i=0;i < result.size();i++)
    {
      Object[] row = result.get(i);
      discussions.addDiscussion(new Discussion((int)row[0],(String)row[1],(String)row[2]));
    }
    return discussions;
  }

  @Override public void clearDiscussions()
  {

  }

  @Override public void clearUsers()
  {

  }

  @Override public void linkTheConnectionsBetween(DiscussionList discussionList,
      UserBase userBase) throws SQLException
  {
    String sql = "Select * from DisCoDB.DiscussionUserList";
    ArrayList<Object[]> result = db.query(sql);
    for (int i = 0; i<result.size(); i++)
    {
      Object[] row = result.get(i);
      discussionList.getDiscussionById((int)row[0]).addUser(userBase.getUserById((int)row[1]));
    }

  }

  @Override public void removeDiscussion(Discussion discussion)
  {

  }

  @Override public void removeUser(User user)
  {

  }

  @Override public void saveDiscussion(Discussion discussion)
  {

  }

  @Override public void saveDiscussions(DiscussionList discussionList)
  {

  }

  @Override public void saveUser(User user)
  {

  }

  @Override public void saveUsers(UserBase userBase)
  {

  }
}

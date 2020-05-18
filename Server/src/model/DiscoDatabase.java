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

  @Override public Discussion saveDiscussion(String discussionName, String loginOfEditor) throws SQLException
  {
    String sql = "INSERT INTO DisCoDB.DiscussionList(DiscussionName,LoginOfEditor) " + " VALUES(?,?);";
    db.update(sql,discussionName,loginOfEditor);

    sql = "SELECT ID From DisCoDB.DiscussionList WHERE DiscussionName = ? AND LoginOfEditor = ?;";
    ArrayList<Object[]> idRes = db.query(sql,discussionName,loginOfEditor);
    int id = Integer.parseInt(idRes.get(0)[0].toString());

    return new Discussion(id,discussionName,loginOfEditor);
  }

  @Override public User saveUser(String userType ,String login, String password) throws SQLException
  {
    String sql = "INSERT INTO DisCoDB.userBase(Login,Password,UserType) " + "VALUES(?,?,?);";
    db.update(sql,login,password,userType);

    sql = "SELECT ID from DisCoDB.userBase WHERE Login = ? AND Password = ? AND UserType = ?;";
    ArrayList<Object[]> idRes = db.query(sql,login,password,userType);
    int id = Integer.parseInt(idRes.get(0)[0].toString());

    return new User(id,userType,login,password);
  }

}

package model;

import org.postgresql.core.SqlCommand;
import utility.persistence.MyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class DiscoDatabase implements DiscoPersistence
{

  private MyDatabase db;

  public DiscoDatabase() throws ClassNotFoundException
  {
    this.db = new MyDatabase("org.postgresql.Driver",
        "jdbc:postgresql://discodb.csnebnxmdf9e.us-east-2.rds.amazonaws.com:5432/postgres",
        "postgres", "DisCoDB20");
  }

  @Override public UserBase loadUsers() throws SQLException
  {
    String sql = "Select * From DisCoDB.userBase";
    ArrayList<Object[]> result = db.query(sql);
    UserBase userBase = new UserBase();
    for (int i = 0; i < result.size(); i++)
    {
      Object[] row = result.get(i);
      userBase.addUser((int) row[0], (String) row[3], (String) row[1],
          (String) row[2]);
    }
    return userBase;
  }

  @Override public DiscussionList loadDiscussions() throws SQLException
  {
    String sql = "Select * From DisCoDB.DiscussionList";
    ArrayList<Object[]> result = db.query(sql);
    DiscussionList discussions = new DiscussionList();
    for (int i = 0; i < result.size(); i++)
    {
      Object[] row = result.get(i);
      discussions.addDiscussion(
          new Discussion((int) row[0], (String) row[1], (String) row[2]));
    }
    return discussions;
  }

  @Override public void clearDiscussions() throws SQLException
  {
    try
    {
      String sql = "TRUNCATE TABLE DisCoDB.DiscussionList CASCADE;";
      db.update(sql);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void clearUsers() throws SQLException
  {
    try
    {
      String sql = "TRUNCATE TABLE DisCoDB.userBase CASCADE;";
      db.update(sql);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void linkTheConnectionsBetween(DiscussionList discussionList,
      UserBase userBase) throws SQLException
  {
    String sql = "Select * from DisCoDB.DiscussionUserList";
    ArrayList<Object[]> result = db.query(sql);
    for (int i = 0; i < result.size(); i++)
    {
      Object[] row = result.get(i);
      discussionList.getDiscussionById((int) row[0])
          .addUser(userBase.getUserById((int) row[1]));
    }
  }

  @Override public void removeDiscussion(int discussionId, int userId)
      throws SQLException
  {
    String sql = "Delete from DisCoDB.DiscussionList where ID = ?;";
    db.update(sql, discussionId);
    sql = "Delete from DisCoDB.DiscussionUserList where DiscussionID = ?;";
    db.update(sql, discussionId);
    sql ="Delete from DisCoDB.DiscussionMessageList where DiscussionID = ?;";
    db.update(sql,discussionId);
  }

  @Override public void removeUser(int userID) throws SQLException
  {
    String sql = "Delete from DisCoDB.userBase where ID = ?;";
    db.update(sql, userID);
    sql = "Delete from DisCoDB.DiscussionUserList where UserId = ?;";
    db.update(sql, userID);
  }

  @Override public Discussion saveDiscussion(String discussionName,
      String loginOfEditor) throws SQLException
  {
    String sql =
        "INSERT INTO DisCoDB.DiscussionList(DiscussionName,LoginOfEditor) "
            + " VALUES(?,?);";
    db.update(sql, discussionName, loginOfEditor);

    sql = "SELECT ID From DisCoDB.DiscussionList WHERE DiscussionName = ? AND LoginOfEditor = ?;";
    ArrayList<Object[]> idRes = db.query(sql, discussionName, loginOfEditor);
    int id = Integer.parseInt(idRes.get(0)[0].toString());

    return new Discussion(id, discussionName, loginOfEditor);
  }

  @Override public User saveUser(String userType, String login, String password)
      throws SQLException
  {
    String sql = "INSERT INTO DisCoDB.userBase(Login,Password,UserType) "
        + "VALUES(?,?,?);";
    db.update(sql, login, password, userType);

    sql = "SELECT ID from DisCoDB.userBase WHERE Login = ? AND Password = ? AND UserType = ?;";
    ArrayList<Object[]> idRes = db.query(sql, login, password, userType);
    int id = Integer.parseInt(idRes.get(0)[0].toString());

    return new User(id, userType, login, password);
  }

  @Override public void saveUserDiscussionConnection(int discussionId,
      int userId) throws SQLException
  {
    String sql = "INSERT INTO DisCoDB.DiscussionUserList(DiscussionId,UserID) "
        + "VALUES(?,?);";
    db.update(sql, discussionId, userId);
  }

  @Override public void editNameOfDiscussion(int discussionId,
      String discussionName) throws SQLException
  {
    String sql = "UPDATE DisCoDB.DiscussionList SET DiscussionName = ? WHERE ID = ? ;";
    db.update(sql, discussionName, discussionId);
  }

  @Override public void editUserLogin(int userId, String newLogin)
      throws SQLException
  {
    String sql = "UPDATE DisCoDB.userBase SET Login = ? WHERE ID = ?;";
    db.update(sql, newLogin, userId);
  }

  @Override public void editUserPassword(int userId, String newPassword)
      throws SQLException
  {
    String sql = "UPDATE DisCoDB.userBase SET Password = ? WHERE ID = ?;";
    db.update(sql, newPassword, userId);
  }

  @Override public void changeEditorLoginInEveryDiscussion(
      String oldEditorLogin, String newEditorLogin) throws SQLException
  {
    String sql = "UPDATE DisCoDB.DiscussionList SET LoginOfEditor = ? WHERE LoginOfEditor = ?";
    db.update(sql, newEditorLogin, oldEditorLogin);
  }

  @Override public Message saveDiscussionMessageConnection(String text,
      int discussionId) throws SQLException
  {
    DateTime dateTime  = new DateTime();
    String messageText = dateTime.getTimestamp() + " " + text;
    String sql = "INSERT INTO DisCoDB.DiscussionMessageList(DiscussionId,MessageText) " + " VALUES(?,?);";
    db.update(sql,discussionId,messageText);

    sql = "SELECT MessageID from DisCoDB.DiscussionMessageList WHERE DiscussionID = ? AND MessageText = ?;";
    ArrayList<Object[]> idRes = db.query(sql, discussionId, messageText);
    System.out.println(idRes.size());
    int id = Integer.parseInt(idRes.get(0)[0].toString());
    return new Message(messageText,id);
  }

  @Override public void linkDiscussionMessage(DiscussionList discussionList) throws SQLException
  {
    String sql = "Select * from DisCoDB.DiscussionMessageList";
    ArrayList<Object[]> result = db.query(sql);
    for (int i = 0; i < result.size(); i++)
    {
      Object[] row = result.get(i);
      discussionList.getDiscussionById((int) row[0])
          .addMessage((String)row[2],((int)row[1]));
    }
  }
}

import model.ServerModel;
import model.ServerModelManager;
import model.UserBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import static org.junit.jupiter.api.Assertions.*;

class ServerModelManagerTest
{
  private ServerModel model;
  @BeforeEach void setUp()
  {
    this.model = new ServerModelManager();
  }

  @AfterEach void tearDown()
  {
  }

  @Test void addZeroNewUserToUserBase()
  {
    assertThrows(IllegalArgumentException.class, ()->{
      this.model.addNewUserToUserBase(null,null,null);
    });
  }

  @Test void addOneNewUserToUserBase()
  {
    model.addNewUserToUserBase("RegisteredUser","Tim","123");
    assertEquals("Tim",model.getUserFromUserBaseByLogin("Tim").getUserLogin());
  }

  @Test void addManyUserToUserBase()
  {
    model.addNewUserToUserBase("RegisteredUser","Greg","123");
    model.addNewUserToUserBase("RegisteredUser","Neo","123");
    assertEquals("Greg Neo",model.getUserFromUserBaseByLogin("Greg").getUserLogin() + " " + model.getUserFromUserBaseByLogin("Neo").getUserLogin());
  }

  @Test void addBoundariesUserToUserBase()
  {
    // no need
  }

  @Test void addExceptionUserToUserBase()
  {
    assertThrows(IllegalArgumentException.class,()->{
      this.model.addNewUserToUserBase(null,"Tim","123");
    });
  }
}
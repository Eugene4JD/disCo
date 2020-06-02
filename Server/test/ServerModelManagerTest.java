import model.*;
import network.CreateDiscussionRequest;
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
    assertThrows(IllegalArgumentException.class, () -> {
      this.model.addNewUserToUserBase(null, null, null);
    });
  }

  @Test void addOneNewUserToUserBase()
  {
    model.addNewUserToUserBase("RegisteredUser", "JOK", "123");
    assertEquals("JOK", model.getUserFromUserBaseByLogin("JOK").getUserLogin());
  }

  @Test void addManyUserToUserBase()
  {
    model.addNewUserToUserBase("RegisteredUser", "Greg1232", "123");
    model.addNewUserToUserBase("RegisteredUser", "Neo12332", "123");
    assertEquals("Greg1232 Neo12332",
        model.getUserFromUserBaseByLogin("Greg1232").getUserLogin() + " "
            + model.getUserFromUserBaseByLogin("Neo12332").getUserLogin());
  }

  @Test void addBoundariesUserToUserBase()
  {
    // no need
  }

  @Test void addExceptionUserToUserBase()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      this.model.addNewUserToUserBase(null, "Tim", "123");
    });
  }

  @Test void createZeroNewDiscussion()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      this.model.createNewDiscussion(null, null);
    });
  }

  @Test void createOneNewDiscussion()
  {
    Discussion discussion = this.model.createNewDiscussion("GGBET", "Tim");
    assertEquals("GGBET", model.getDiscussionById(discussion.getDiscussionId())
        .getDiscussionName());
  }

  @Test void createManyNewDiscussion()
  {
    Discussion discussion1 = this.model.createNewDiscussion("OM", "Tim");
    Discussion discussion2 = this.model.createNewDiscussion("MO", "Tim");
    assertEquals("OM MO", model.getDiscussionById(discussion1.getDiscussionId())
        .getDiscussionName() + " " + model
        .getDiscussionById(discussion2.getDiscussionId()).getDiscussionName());
  }

  @Test void createBoundariesNewDiscussion()
  {
    // no need
  }

  @Test void createExceptionNewDiscussion()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.createNewDiscussion(null, "Tim");
    });
  }

  @Test void getZeroDiscussionById()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.getDiscussionById(0);
    });
  }

  @Test void getOneDiscussionById()
  {
    Discussion discussion = model.createNewDiscussion("Gl", "Tim");
    assertEquals("Gl", model.getDiscussionById(discussion.getDiscussionId())
        .getDiscussionName());
  }

  @Test void getBoundariesDiscussionById()
  {
    assertNull(model.getDiscussionById(1));
  }

  @Test void getManyDiscussionById()
  {
    //tested in create many discussions
  }

  @Test void getExceptionDiscussionById()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.getDiscussionById(-1);
    });
  }

  @Test void getZeroUserFromUserBaseById()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.getUserFromUserBaseById(0);
    });
  }

  @Test void getOneUserFromUserBaseById()
  {
    User Bastion = model
        .addNewUserToUserBase("RegisteredUser", "BigBro", "123");
    assertEquals(Bastion.getUserId(),
        model.getUserFromUserBaseById(Bastion.getUserId()).getUserId());
  }

  @Test void getManyUsersFromUserBaseById()
  {
    User user1 = model.getUserFromUserBaseByLogin("Tim");
    User user2 = model.getUserFromUserBaseByLogin("Bastion");
    assertEquals("TimBastion",
        model.getUserFromUserBaseById(user1.getUserId()).getUserLogin() + model
            .getUserFromUserBaseById(user2.getUserId()).getUserLogin());
  }

  @Test void getBoundariesUserFromUserBaseById()
  {
    assertNull(model.getUserFromUserBaseById(1));
  }

  @Test void getExceptionUserFromUserBaseById()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.getUserFromUserBaseById(-1);
    });
  }

  @Test void getZeroUserFromUserBaseByLogin()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.getUserFromUserBaseByLogin(null);
    });
  }

  @Test void getOneUserFromUserBaseByLogin()
  {
    assertEquals("Tim", model.getUserFromUserBaseByLogin("Tim").getUserLogin());
  }

  @Test void getManyFromUserBaseByLogin()
  {
    assertEquals("Tim Bastion",
        model.getUserFromUserBaseByLogin("Tim").getUserLogin() + " " + model
            .getUserFromUserBaseByLogin("Bastion").getUserLogin());
  }

  @Test void getBoundariesFromUserBaseByLogin()
  {
    // no need
  }

  @Test void getExceptionFromUserBaseByLogin()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.getUserFromUserBaseByLogin(null);
    });
  }

  @Test void removeZeroDiscussion()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.removeDiscussion(0, 0);
    });
  }

  @Test void removeOneDiscussion()
  {
    Discussion discussion = model.createNewDiscussion("CCCC", "Tim");
    model.removeDiscussion(discussion.getDiscussionId(),
        model.getUserFromUserBaseByLogin("Tim").getUserId());
    assertNull(model.getDiscussionById(discussion.getDiscussionId()));
  }

  @Test void removeManyDiscussions()
  {
    // no need
  }

  @Test void removeBoundariesDiscussion()
  {
    // no need
  }

  @Test void removeExceptionDiscussion()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.removeDiscussion(-1, -1);
    });
  }

  @Test void removeZeroUserFromUserBase()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.removeUserFromUserBase(0);
    });
  }

  @Test void removeOneUserFromUserBase()
  {
    User user = model.getUserFromUserBaseByLogin("Bastion");
    model.removeUserFromUserBase(user.getUserId());
    assertNull(model.getUserFromUserBaseByLogin("Bastion"));
  }

  @Test void removeManyUsersFromUserBase()
  {
    // no need
  }

  @Test void removeBoundariesUserFromUserBase()
  {
    // no need
  }

  @Test void removeExceptionUserFromUserBase()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.removeUserFromUserBase(-1);
    });
  }

  @Test void addZeroUserToDiscussion()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.addUserToDiscussion(0, 0);
    });
  }

  @Test void addOneUserToDiscussion()
  {
    Discussion discussion = model.createNewDiscussion("DF", "Tim");
    model.addUserToDiscussion(discussion.getDiscussionId(),
        model.getUserFromUserBaseByLogin("Tim").getUserId());
    assertEquals("Tim",
        model.getDiscussionById(discussion.getDiscussionId()).getUserBase()
            .getUserByLogin("Tim").getUserLogin());
  }

  @Test void addManyUserToDiscussion()
  {
    //no need
  }

  @Test void addBoundaryUserToDiscussion()
  {
    // no need
  }

  @Test void addExceptionsUserToDiscussion()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.addUserToDiscussion(-1, -10);
    });
  }

  @Test void addZeroMessageToDiscussion()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.addMessageToDiscussion(0, 0, null);
    });
  }

  @Test void addOneMessageToDiscussion()
  {
    // no need
  }

  @Test void addManyMessagesToDiscussion()
  {
    // no need
  }

  @Test void addBoundariesToDiscussion()
  {
    // no need
  }

  @Test void addExceptionMessageToDiscussion()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      model.addMessageToDiscussion(-1, -100, "ds");
    });
  }

}
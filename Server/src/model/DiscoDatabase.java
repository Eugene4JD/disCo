package model;

import utility.persistence.MyDatabase;

public class DiscoDatabase implements DiscoPersistence
{

  private MyDatabase db; 
  @Override public UserBase loadUsers()
  {
    return null;
  }

  @Override public void clearDiscussions()
  {

  }

  @Override public void clearUsers()
  {

  }

  @Override public void lingTheConnectionsBetween(DiscussionList discussionList,
      UserBase userBase)
  {

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

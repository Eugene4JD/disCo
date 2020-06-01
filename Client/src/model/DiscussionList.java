package model;

import java.util.ArrayList;

/**
 * Class representing list of Discussions
 *
 * @author Group 2
 * @version 1.1
 */

public class DiscussionList
{
  private ArrayList<Discussion> discussions;

  /**
   * Zero argument constructor initializing the array list of discussions
   */
  public DiscussionList()
  {
    this.discussions = new ArrayList<>();
  }

  /**
   * Creating new discussion and adding it to instance of discussion  array list
   *
   * @param discussionId   discussion id
   * @param discussionName discussion name
   * @param loginOfEditor  login of editor of discussion
   */
  public void createNewDiscussion(int discussionId, String discussionName,
      String loginOfEditor)
  {
    discussions
        .add(new Discussion(discussionId, discussionName, loginOfEditor));
  }

  /**
   * Adding discussion to instance of discussion  array list
   *
   * @param discussion discussion
   */
  public void addDiscussion(Discussion discussion)
  {
    this.discussions.add(discussion);
  }

  /**
   * getting discussion from the instance of discussion array list  by discussion id
   *
   * @param discussionId discussion id
   * @return discussion
   */
  public Discussion getDiscussionById(int discussionId)
  {
    for (int i = 0; i < discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionId() == discussionId)
        return discussions.get(i);
    }
    return null;
  }

  /**
   * getting discussion from the instance of discussion array list by given name of this discussion
   *
   * @param name discussion name
   * @return discussion
   */
  public Discussion getDiscussionByName(String name)
  {
    for (int i = 0; i < discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionName().equals(name))
        return discussions.get(i);
    }
    return null;
  }

  /**
   * getting discussion by index from the instance of discussion array list(used when you need to go through the list of discussion and for example check )
   *
   * @param i discussion index
   * @return discussion
   */
  public Discussion getDiscussion(int i)
  {
    return discussions.get(i);
  }

  /**
   * removing discussion from the instance of discussion array list by discussion id
   *
   * @param discussionId
   */
  public void removeDiscussionById(int discussionId)
  {
    for (int i = 0; i < discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionId() == discussionId)
      {
        discussions.remove(i);
        break;
      }
    }
  }

  /**
   * removing discussion from the instance of discussion array list by discussion Name(the first that will be in line )
   *
   * @param discussionName
   */
  public void removeDiscussionByName(String discussionName)
  {
    for (int i = 0; i < discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionName().equals(discussionName))
      {
        discussions.remove(i);
        break;
      }
    }
  }

  /**
   * removing every discussion from instance of discussion array list
   */
  public void clear()
  {
    int s = size();
    for (int i = 0; i < s; i++)
    {
      discussions.remove(0);
    }
  }

  /**
   * returning the size of discussion list
   *
   * @return discussion list size
   */
  public int size()
  {
    return discussions.size();
  }

  /**
   * return the String interpretation of this discussion list
   *
   * @return String interpretation of this discussion list
   */
  public String toString()
  {
    String str = " ";
    for (int i = 0; i < this.size(); i++)
    {
      str += discussions.get(0).getDiscussionName() + " " + discussions.get(0)
          .getDiscussionId() + " " + discussions.get(0)
          .getLoginOfEditorOfDiscussion();
      str += "\n";
    }
    return str;

  }
}

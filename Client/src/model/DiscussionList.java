package model;

import java.util.ArrayList;

public class DiscussionList
{
  private ArrayList<Discussion> discussions;

  public DiscussionList()
  {
    this.discussions = new ArrayList<>();
  }

  public void createNewDiscussion(String discussionId)
  {
    discussions.add(new Discussion(discussionId));
  }
  public void addDiscussion(Discussion discussion)
  {
    this.discussions.add(discussion);
  }

  public Discussion getDiscussionById(String discussionId)
  {
    for (int i =0; i<discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionId().equals(discussionId))
        return discussions.get(i);
    }
    return null;
  }

  public Discussion getDiscussion(int i)
  {
    return discussions.get(i);
  }

  public void removeDiscussionById(String discussionId)
  {
    for (int i =0; i<discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionId().equals(discussionId))
      {
        discussions.remove(i);
        break;
      }
    }
  }

  public int size()
  {
    return discussions.size();
  }
}

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

  public Discussion getDiscussionById(String discussionId)
  {
    for (int i =0; i<discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionId().equals(discussionId))
        return discussions.get(i);
    }
    return null;
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
}

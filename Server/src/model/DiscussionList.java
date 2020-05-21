package model;

import java.util.ArrayList;

public class DiscussionList
{
  private ArrayList<Discussion> discussions;

  public DiscussionList()
  {
    this.discussions = new ArrayList<>();
  }

  public void createNewDiscussion(int discussionId,String discussionName,String loginOfEditor)
  {
    discussions.add(new Discussion(discussionId,discussionName,loginOfEditor));
  }
  public void addDiscussion(Discussion discussion)
  {
    this.discussions.add(discussion);
  }

  public Discussion getDiscussionById(int  discussionId)
  {
    for (int i =0; i<discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionId() == discussionId)
        return discussions.get(i);
    }
    throw new IllegalArgumentException(" e");
  }

  public Discussion getDiscussionByName(String name)
  {
    for (int i = 0; i<discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionName().equals(name))
        return discussions.get(i);
    }
    return null;
  }

  public Discussion getDiscussion(int i)
  {
    return discussions.get(i);
  }

  public void removeDiscussionById(int discussionId)
  {
    for (int i =0; i<discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionId() == discussionId)
      {
        discussions.remove(i);
        break;
      }
    }
  }

  public void removeDiscussionByName(String discussionName)
  {
    for (int i =0; i<discussions.size(); i++)
    {
      if (discussions.get(i).getDiscussionName().equals(discussionName))
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

  public String toString()
  {
    String str = " ";
    for (int i=0 ; i<this.size(); i++)
    {
     str += discussions.get(0).getDiscussionName() + " " + discussions.get(0).getDiscussionId() + " " + discussions.get(0).getLoginOfEditorOfDiscussion();
     str += "\n";
    }
    return str;

  }
}

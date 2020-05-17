package model;

import mediator.RemoteModel;
import mediator.RemoteModelProxy;
import network.BroadcastMessageToDiscussionRequest;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class ClientModelManager implements ClientModel
{
  private DiscussionList discussionListBuffer;
  private UserBase userBaseBuffer;
  private RemoteModel remoteModel;

  public ClientModelManager()
  {
    this.discussionListBuffer = new DiscussionList();
    this.userBaseBuffer = new UserBase();
    try
    {
      this.remoteModel = new RemoteModelProxy(this);
      this.remoteModel.connect();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void connect()
  {
    try
    {
      remoteModel.connect();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void disconnect()
  {
    try
    {
      remoteModel.disconnect();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  @Override public void removeDiscussion(String discussionId)
  {
    this.remoteModel.removeDiscussion(discussionId);
  }

  @Override public void addDiscussion(Discussion discussion)
  {
    this.discussionListBuffer.addDiscussion(discussion);
  }

  @Override public void addDiscussions(DiscussionList discussionList)
  {
    for (int i = 0; i<discussionList.size(); i++)
    {
      this.discussionListBuffer.addDiscussion(discussionList.getDiscussion(i));
    }
  }

  @Override public void addUser(User user)
  {
     userBaseBuffer.addUser(user);
  }

  @Override public void addUsers(UserBase userBase)
  {
    for (int i = 0; i<userBase.size(); i++)
    {
      this.userBaseBuffer.addUser(userBase.getUser(i));
    }
  }

  @Override public void createDiscussion(String discussionId)
  {
      remoteModel.createNewDiscussion(discussionId);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "broadcastMessageToDiscussion":
        BroadcastMessageToDiscussionRequest request = (BroadcastMessageToDiscussionRequest)evt.getNewValue();
        //this.discussionListBuffer.getDiscussionById(request.getDiscussionID())
      case "broadcastDiscussionsToUser":
      case "broadcastDiscussionToUser":
      case "broadcastRemovingDiscussionToUser":
      case "broadcastLoginStatusToUser":

    }
  }
}

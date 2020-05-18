package model;

import mediator.RemoteModel;
import mediator.RemoteModelProxy;
import network.*;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class ClientModelManager implements ClientModel
{
  private DiscussionList discussionListBuffer;
  private UserBase userBaseBuffer;
  private RemoteModel remoteModel;
  private String login;

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
    this.login = "";

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
      remoteModel.createNewDiscussion(discussionId,login);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "broadcastMessageToDiscussion":
        BroadcastMessageToDiscussionRequest request = (BroadcastMessageToDiscussionRequest)evt.getNewValue();
        Discussion discussion = this.discussionListBuffer.getDiscussionByName(request.getDiscussionID());
        if (discussion != null)
        discussion.getMessageList().addMessage(request.getSender(),request.getMessage());
        break;
      case "broadcastDiscussionsToUser":
        BroadcastDiscussionsToUserRequest request1 = (BroadcastDiscussionsToUserRequest)evt.getNewValue();
        for (int i =0; i<request1.getDiscussions().size();i++)
        {
          discussionListBuffer.addDiscussion(discussionListBuffer.getDiscussion(i));
        }
        break;
      case "broadcastDiscussionToUser":
        BroadcastDiscussionToUserRequest request2 = (BroadcastDiscussionToUserRequest)evt.getNewValue();
        discussionListBuffer.addDiscussion(request2.getDiscussion());
        break;
      case "broadcastRemovingDiscussionToUser":
        BroadcastRemovingDiscussionToUserRequest request3 =(BroadcastRemovingDiscussionToUserRequest)evt.getNewValue();
        discussionListBuffer.removeDiscussionByName(request3.getDiscussionId());
        break;
      case "broadcastLoginStatusToUser":
        BroadcastLoginStatusToUserRequest request4 = (BroadcastLoginStatusToUserRequest)evt.getNewValue();
        if (request4.isLogSuccessful())
          this.login = request4.getLogin();
    }
  }
}

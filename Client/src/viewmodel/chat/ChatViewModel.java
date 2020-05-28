package viewmodel.chat;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.ClientModel;
import model.Discussion;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener
{
  private ClientModel model;
  private ObservableList<Label> chatList;
  private StringProperty enterField;
  private StringProperty threadName;

  public ChatViewModel(ClientModel model)
  {
    this.model = model;
    model.addListener(this);
    updateChatList();
    this.enterField = new SimpleStringProperty();
    this.threadName = new SimpleStringProperty();
  }

  private void setThreadName()
  {
    threadName.set(model.getDiscussionListBuffer()
        .getDiscussionById(model.getSelectedDiscussion()).getDiscussionName());
  }

  public void clear()
  {
    chatList.clear();
  }

  public ObservableList<Label> getChatList()
  {
    return chatList;
  }

  public void updateChatList()
  {
    chatList = FXCollections.observableArrayList();
    chatList.clear();
  }

  public void load()
  {
    Discussion discussion = model.getDiscussionListBuffer()
        .getDiscussionById(model.getSelectedDiscussion());
    if (discussion != null)
    {
      for (int i = 0; i < discussion.getMessageList().size(); i++)
      {
        chatList.add(
            new Label(discussion.getMessageList().getMessage(i).toString()));
      }
      setThreadName();
    }
  }

  public void sendMessage()
  {
    model.sendMessageToDiscussion(model.getSelectedDiscussion(), model.getId(),
        enterField.get());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "NewMessageToChat":
          Discussion discussion = (Discussion) evt.getNewValue();
          if (discussion.getDiscussionId() == model.getSelectedDiscussion())
          {
            chatList.add(new Label(discussion.getMessageList()
                .getMessage(discussion.getMessageList().size() - 1)
                .toString()));
            enterField.set("");
          }
          break;
        case "Add":
          load();
        case "AddList":
          load();

      }
    });
  }

  public StringProperty getEnterField()
  {
    return this.enterField;
  }

  public StringProperty getThreadName()
  {
    return threadName;
  }

  public void remove()
  {
    //model.removeDiscussion();
  }
}

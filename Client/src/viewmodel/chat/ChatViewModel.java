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
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private ClientModel model;
  private ObservableList<Label> chatList;
  private StringProperty enterField;
  private StringProperty threadName;
  private PropertyChangeSupport property;

  public ChatViewModel(ClientModel model)
  {
    this.model = model;
    model.addListener(this);
    property = new PropertyChangeSupport(this);
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
    chatList.clear();
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
      property.firePropertyChange("toBottom", null,
          discussion.getMessageList().size() - 1);
    }
    enterField.set("");
  }

  public void sendMessage()
  {
    if (!enterField.get().equals(""))
    {
      model
          .sendMessageToDiscussion(model.getSelectedDiscussion(), model.getId(),
              enterField.get());
      property.firePropertyChange("MessageLoading", null, true);
      enterField.set("");
    }
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
            property.firePropertyChange("MessageLoading", null, false);
            property.firePropertyChange("toBottom", null,
                discussion.getMessageList().size() - 1);
          }
          break;
        case "ChangedDiscussionName":
          load();
          break;
        case "DiscussionRemoved":
          if (model.getSelectedDiscussion() == (int) evt.getNewValue())
          {
            property.firePropertyChange("RemoveThread", null, "");
          }
          break;
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
    try
    {
      model.removeDiscussion(model.getSelectedDiscussion());
      property.firePropertyChange("Loading", null, "");
    }
    catch (Exception e)
    {
      property.firePropertyChange("AccessDenied", null, "");
    }
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}

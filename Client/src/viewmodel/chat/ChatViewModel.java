package viewmodel.chat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.ClientModel;

public class ChatViewModel
{
  private ClientModel model;
  private ObservableList<Label> chatList;

  public ChatViewModel(ClientModel model)
  {
    this.model = model;
    updateChatList();
  }

  public void clear()
  {

  }

  public ObservableList<Label> getChatList()
  {
    return chatList;
  }

  public void updateChatList()
  {
    chatList = FXCollections.observableArrayList();
    for (int i = 0; i < 25; i++)
    {
      Label label = new Label("Item: " + i);
      chatList.add(label);
    }
  }

}

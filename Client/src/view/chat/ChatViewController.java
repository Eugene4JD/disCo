package view.chat;

import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewmodel.chat.ChatViewModel;

public class ChatViewController
{
  @FXML private JFXListView<Label> chatList;
  @FXML private TextField enterField;

  private ViewHandler viewHandler;
  private ChatViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ChatViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    chatList.setItems(viewModel.getChatList());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  public void backButtonPressed(ActionEvent actionEvent)
  {
    viewHandler.openView("main");
  }

  public void sendButtonPressed(ActionEvent actionEvent)
  {
  }
}

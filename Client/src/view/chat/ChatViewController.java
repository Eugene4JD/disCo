package view.chat;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.ViewHandler;
import viewmodel.chat.ChatViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class ChatViewController implements PropertyChangeListener
{
  @FXML private JFXListView<Label> chatList;
  @FXML private TextField enterField;
  @FXML private Label threadName;
  @FXML private VBox vBox;
  @FXML private JFXSpinner spinner;
  @FXML private ImageView logo;
  @FXML private ImageView renameIcon;
  @FXML private ImageView removeIcon;
  @FXML private JFXButton sendButton;
  @FXML private JFXButton back;
  private boolean disableSend = false;

  private ViewHandler viewHandler;
  private ChatViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ChatViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    viewModel.addListener(this);

    //chatList.setExpanded(true);
    chatList.setItems(viewModel.getChatList());
    enterField.textProperty().bindBidirectional(viewModel.getEnterField());
    threadName.textProperty().bindBidirectional(viewModel.getThreadName());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public void load()
  {
    viewModel.load();
    enterField.requestFocus();
  }

  public Region getRoot()
  {
    return root;
  }

  public void backButtonPressed(ActionEvent actionEvent)
  {
    viewHandler.openView("main");
  }

  public void sendButtonPressed()
  {
    viewModel.sendMessage();
  }

  public void editPressed(MouseEvent mouseEvent)
  {
    viewHandler.openView("rename");
  }

  public void removePressed(MouseEvent mouseEvent)
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Are you sure that you want to remove this thread? This action is irreversible");
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
      viewModel.remove();
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "AccessDenied":
          accessDeniedAlert();
          break;
        case "RemoveThread":
          removeLoading();
          viewHandler.openView("main");
          break;
        case "Loading":
          setLoading();
          break;
        case "MessageLoading":
          if (evt.getNewValue().equals(true))
          {
            setMessageLoading();
            break;
          }
          else if (evt.getNewValue().equals(false))
          {
            removeMessageLoading();
            break;
          }
      }
    });
  }

  private void setLoading()
  {
    logo.setOpacity(0.5);
    renameIcon.setOpacity(0.5);
    removeIcon.setOpacity(0.5);
    spinner.visibleProperty().setValue(true);
    vBox.disableProperty().setValue(true);
  }

  private void removeLoading()
  {
    logo.setOpacity(1);
    renameIcon.setOpacity(1);
    removeIcon.setOpacity(1);
    spinner.visibleProperty().setValue(false);
    vBox.disableProperty().setValue(false);
  }

  private void setMessageLoading()
  {
    renameIcon.setOpacity(0.5);
    renameIcon.disableProperty().setValue(true);
    removeIcon.setOpacity(0.5);
    removeIcon.disableProperty().setValue(true);
    back.disableProperty().setValue(true);
    spinner.visibleProperty().setValue(true);
    spinner.setOpacity(0.5);
    sendButton.disableProperty().setValue(true);
    disableSend = true;
  }

  private void removeMessageLoading()
  {
    renameIcon.setOpacity(1);
    renameIcon.disableProperty().setValue(false);
    removeIcon.setOpacity(1);
    removeIcon.disableProperty().setValue(false);
    back.disableProperty().setValue(false);
    spinner.visibleProperty().setValue(false);
    sendButton.disableProperty().setValue(false);
    disableSend = false;
  }

  private void accessDeniedAlert()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("You are not the editor of this thread");
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    alert.showAndWait();
  }

  public void onEnter(KeyEvent keyEvent)
  {
    if (keyEvent.getCode() == KeyCode.ENTER)
    {
      if (!disableSend)
        sendButtonPressed();
    }
  }
}

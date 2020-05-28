package view.chat;

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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.ViewHandler;
import viewmodel.chat.RenameViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class RenameViewController implements PropertyChangeListener
{
  @FXML private TextField enterField;
  @FXML private Label oldTitleLabel;
  @FXML private Text oldTitleText;
  @FXML private Text newTitleText;
  @FXML private VBox vBox;
  @FXML private JFXSpinner spinner;
  @FXML private ImageView logo;

  private ViewHandler viewHandler;
  private RenameViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, RenameViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    viewModel.addListener(this);
    this.root = root;

    enterField.textProperty().bindBidirectional(viewModel.getEnterProperty());
    oldTitleLabel.textProperty().bindBidirectional(viewModel.getOldProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public void saveButtonPressed()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure that you want to rename the thread?");
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
      viewModel.rename();
    }
  }

  public void cancelButtonPressed(ActionEvent actionEvent)
  {
    viewHandler.openView("chat");
  }

  public Region getRoot()
  {
    return root;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "AnswerReceived":
          removeLoading();
          viewHandler.openView("chat");
          break;
        case "Loading":
          setLoading();
          break;
        case "AlertEmpty":
          alertEmpty();
          break;
      }
    });
  }

  private void alertEmpty()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Untitled threads are not allowed");
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    alert.showAndWait();
  }

  public void setLoading()
  {
    logo.setOpacity(0.5);
    oldTitleText.setOpacity(0.5);
    newTitleText.setOpacity(0.5);
    spinner.visibleProperty().setValue(true);
    vBox.disableProperty().setValue(true);
  }

  public void removeLoading()
  {
    logo.setOpacity(1);
    oldTitleText.setOpacity(0.95);
    newTitleText.setOpacity(0.95);
    spinner.visibleProperty().setValue(false);
    vBox.disableProperty().setValue(false);
  }

  public void onEnter(KeyEvent keyEvent)
  {
    if (keyEvent.getCode() == KeyCode.ENTER)
    {
      saveButtonPressed();
    }
  }
}

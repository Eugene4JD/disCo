package view.discussionList;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.ViewHandler;
import viewmodel.discussionList.SettingsViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class SettingsViewController implements PropertyChangeListener
{
  @FXML private Label usernameText;
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField oldPasswordField;
  @FXML private JFXPasswordField newPasswordField1;
  @FXML private JFXPasswordField newPasswordField2;
  @FXML private Label errorLabel;
  @FXML private VBox vBox;
  @FXML private JFXSpinner spinner;
  @FXML private ImageView logo;

  private ViewHandler viewHandler;
  private SettingsViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, SettingsViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    viewModel.addListener(this);
    this.root = root;
    this.usernameText.textProperty().bind(viewModel.getUsernameProperty());
    this.usernameField.textProperty()
        .bindBidirectional(viewModel.getNewUsernameProperty());
    this.errorLabel.textProperty().bind(viewModel.getError());
    this.oldPasswordField.textProperty()
        .bindBidirectional(viewModel.getOldPassword());
    this.newPasswordField1.textProperty()
        .bindBidirectional(viewModel.getNewPassword1());
    this.newPasswordField2.textProperty()
        .bindBidirectional(viewModel.getNewPassword2());
  }

  public void backButtonPressed(ActionEvent actionEvent)
  {
    viewHandler.openView("main");
  }

  public void removeProfileButtonPressed(ActionEvent actionEvent)
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Are you sure that you want to remove your profile? This action is irreversible");
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
      viewModel.removeUserButton();
      setLoading();
    }
  }

  public void applyButtonPressed(ActionEvent actionEvent)
  {
    viewModel.applyButton();
  }

  public void reset()
  {
    viewModel.clear();
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
        case "RemoveUser":
          removeLoading();
          viewHandler.openView("login");
          break;
        case "Loading":
          if (evt.getNewValue().equals(true))
          {
            setLoading();
            break;
          }
          else if (evt.getNewValue().equals("changedUsername"))
          {
            removeLoading();
            break;
          }
          else if (evt.getNewValue().equals("changedPassword"))
          {
            removeLoading();
            break;
          }
          else if (evt.getNewValue().equals("changedBoth"))
          {
            removeLoading();
            break;
          }
      }
    });

  }

  public void setLoading()
  {
    logo.setOpacity(0.5);
    usernameField.setOpacity(0.5);
    oldPasswordField.setOpacity(0.5);
    newPasswordField1.setOpacity(0.5);
    newPasswordField2.setOpacity(0.5);
    spinner.visibleProperty().setValue(true);
    vBox.disableProperty().setValue(true);
  }

  public void removeLoading()
  {
    logo.setOpacity(1);
    usernameField.setOpacity(0.95);
    oldPasswordField.setOpacity(0.95);
    newPasswordField1.setOpacity(0.95);
    newPasswordField2.setOpacity(0.95);
    spinner.visibleProperty().setValue(false);
    vBox.disableProperty().setValue(false);
  }
}
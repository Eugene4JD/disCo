package view.discussionList;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import view.ViewHandler;
import viewmodel.discussionList.SettingsViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SettingsViewController implements PropertyChangeListener
{
  @FXML private Text usernameText;
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField oldPasswordField;
  @FXML private JFXPasswordField newPasswordField1;
  @FXML private JFXPasswordField newPasswordField2;
  @FXML private Text errorLabel;

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
    this.usernameText.textProperty()
        .bindBidirectional(viewModel.getUsernameProperty());
    this.usernameField.textProperty()
        .bindBidirectional(viewModel.getNewUsernameProperty());
    this.errorLabel.textProperty().bindBidirectional(viewModel.getError());
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
          viewHandler.openView("login");
      }
    });

  }
}
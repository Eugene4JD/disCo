package view.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.ViewHandler;
import viewmodel.login.RegistrationViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegistrationViewController implements PropertyChangeListener
{
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField passwordField;
  @FXML private JFXPasswordField repeatPasswordField;
  @FXML private Label errorLabel;
  @FXML private VBox vBox;
  @FXML private JFXSpinner spinner;
  @FXML private ImageView logo;

  private ViewHandler viewHandler;
  private RegistrationViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, RegistrationViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewModel.addListener(this);

    usernameField.textProperty()
        .bindBidirectional(viewModel.getUsernameProperty());
    passwordField.textProperty()
        .bindBidirectional(viewModel.getPasswordProperty());
    repeatPasswordField.textProperty()
        .bindBidirectional(viewModel.getRepeatPasswordProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public void backButtonPressed(ActionEvent event)
  {
    viewHandler.openView("login");
  }

  public void signUpButtonPressed()
  {
    viewModel.signUp();
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
        case "Loading":
          if (evt.getNewValue().equals(true))
          {
            logo.setOpacity(0.5);
            usernameField.setOpacity(0.5);
            passwordField.setOpacity(0.5);
            repeatPasswordField.setOpacity(0.5);
            spinner.visibleProperty().setValue(true);
            vBox.disableProperty().setValue(true);
            break;
          }
          else if (evt.getNewValue().equals(false))
          {
            logo.setOpacity(1);
            usernameField.setOpacity(0.95);
            passwordField.setOpacity(0.95);
            repeatPasswordField.setOpacity(0.95);
            spinner.visibleProperty().setValue(false);
            vBox.disableProperty().setValue(false);
            break;
          }
        case "LogStatus":
          viewHandler.openView("main");
          break;
      }
    });
  }

  public void onEnter(KeyEvent keyEvent)
  {
    if (keyEvent.getCode() == KeyCode.ENTER)
    {
      signUpButtonPressed();
    }
  }
}

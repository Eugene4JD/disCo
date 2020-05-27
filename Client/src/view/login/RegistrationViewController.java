package view.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
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

  public void signUpButtonPressed(ActionEvent event)
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
        case "LogStatus":
          if (evt.getNewValue().equals(true))
          {
            viewHandler.openView("login");
          }
      }
    });
  }
}

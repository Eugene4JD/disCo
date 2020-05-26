package view.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import view.ViewHandler;
import viewmodel.login.RegistrationViewModel;

public class RegistrationViewController
{
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField passwordField;
  @FXML private JFXPasswordField repeatPasswordField;
  @FXML private Text errorLabel;

  private ViewHandler viewHandler;
  private RegistrationViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, RegistrationViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    usernameField.textProperty()
        .bindBidirectional(viewModel.getUsernameProperty());
    passwordField.textProperty()
        .bindBidirectional(viewModel.getPasswordProperty());
    repeatPasswordField.textProperty()
        .bindBidirectional(viewModel.getRepeatPasswordProperty());
    errorLabel.textProperty().bindBidirectional(viewModel.getErrorProperty());
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
    viewHandler.openView("login");

  }

  public Region getRoot()
  {
    return root;
  }
}

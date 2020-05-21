package view.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewmodel.login.RegistrationViewModel;

public class RegistrationViewController
{
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField passwordField;
  @FXML private JFXPasswordField repeatPasswordField;

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
}

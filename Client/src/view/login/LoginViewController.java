package view.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewmodel.login.LoginViewModel;

public class LoginViewController
{
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField passwordField;

  private ViewHandler viewHandler;
  private LoginViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, LoginViewModel loginViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = loginViewModel;
    this.root = root;

    usernameField.textProperty()
        .bindBidirectional(viewModel.getUsernameProperty());
    passwordField.textProperty()
        .bindBidirectional(viewModel.getPasswordProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  @FXML private void cancelButtonPressed()
  {
    viewHandler.closeView();
  }

  @FXML private void signInButtonPressed()
  {
    viewModel.logIn();
    try
    {
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {

    }
    viewHandler.openView("main");
  }

  public Region getRoot()
  {
    return root;
  }

  public void createOnePressed(MouseEvent mouseEvent)
  {
    viewHandler.openView("registration");
  }

  public void enterAsGuestClicked(MouseEvent mouseEvent)
  {
    viewHandler.openView("main");
  }

}

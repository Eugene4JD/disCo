package view.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import view.ViewHandler;
import viewmodel.login.LoginViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewController implements PropertyChangeListener
{
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField passwordField;
  @FXML private Text errorLabel;

  private ViewHandler viewHandler;
  private LoginViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, LoginViewModel loginViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = loginViewModel;
    this.root = root;
    loginViewModel.addListener(this);

    usernameField.textProperty()
        .bindBidirectional(viewModel.getUsernameProperty());
    passwordField.textProperty()
        .bindBidirectional(viewModel.getPasswordProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
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

  public void onEnter(KeyEvent keyEvent)
  {
    if (keyEvent.getCode() == KeyCode.ENTER)
    {
      signInButtonPressed();
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "LogStatus":
        if (evt.getNewValue().equals(true))
        {
          // viewHandler.openView("main");
        }
    }
  }
}

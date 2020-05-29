package view.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.ViewHandler;
import viewmodel.login.LoginViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewController implements PropertyChangeListener
{
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField passwordField;
  @FXML private Label errorLabel;
  @FXML private VBox vBox;
  @FXML private JFXSpinner spinner;
  @FXML private ImageView logo;

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
    if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
    {
      viewModel.enterAsAGuest();
    }
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
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "LogStatus":
          removeLoading();
          viewHandler.openView("main");
          break;
        case "Loading":
        {
          setLoading();
          break;
        }
      }
    });
  }

  private void setLoading()
  {
    logo.setOpacity(0.5);
    usernameField.setOpacity(0.5);
    passwordField.setOpacity(0.5);
    spinner.visibleProperty().setValue(true);
    vBox.disableProperty().setValue(true);
  }

  private void removeLoading()
  {
    logo.setOpacity(1);
    usernameField.setOpacity(0.95);
    passwordField.setOpacity(0.95);
    spinner.visibleProperty().setValue(false);
    vBox.disableProperty().setValue(false);
  }
}

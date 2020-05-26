package view.discussionList;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import view.ViewHandler;
import viewmodel.discussionList.SettingsViewModel;

public class SettingsViewController
{
  @FXML private Text usernameText;
  @FXML private JFXTextField usernameField;
  @FXML private JFXPasswordField oldPasswordField;
  @FXML private JFXPasswordField newPasswordField1;
  @FXML private JFXPasswordField newPasswordField2;

  private ViewHandler viewHandler;
  private SettingsViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, SettingsViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.usernameText.textProperty()
        .bindBidirectional(viewModel.getUsernameProperty());
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

  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }
}

package view;

import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.MainViewModel;

public class MainViewController
{
  @FXML private JFXListView<Label> logList;
  @FXML private TextField username;
  @FXML private TextField password;

  private ViewHandler viewHandler;
  private MainViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, MainViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    logList.setItems(viewModel.getLogList());
    username.textProperty().bindBidirectional(viewModel.getUsernameProperty());
    password.textProperty().bindBidirectional(viewModel.getPasswordProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  public void createButtonPressed(ActionEvent actionEvent)
  {
    viewModel.createAdmin();
  }
}

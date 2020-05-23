package view.discussionList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewmodel.discussionList.AddViewModel;

public class AddViewController
{
  @FXML private TextField enterField;

  private ViewHandler viewHandler;
  private AddViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, AddViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    enterField.textProperty().bindBidirectional(viewModel.getEnterProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public void createThreadButtonPressed(ActionEvent actionEvent)
  {
    viewModel.createThread();
    viewHandler.openView("main");
  }

  public void cancelButtonPressed(ActionEvent actionEvent)
  {
    viewHandler.openView("main");
  }

  public Region getRoot()
  {
    return root;
  }
}

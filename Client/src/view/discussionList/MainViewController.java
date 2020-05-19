package view.discussionList;

import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewmodel.discussionList.MainViewModel;

public class MainViewController
{
  private ViewHandler viewHandler;
  private MainViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, MainViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
  }

  public void reset()
  {
    viewModel.clear();
  }

  public void backButtonPressed(ActionEvent event)
  {
    viewHandler.openView("login");
  }

  public Region getRoot()
  {
    return root;
  }
}

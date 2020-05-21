package view.discussionList;

import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewmodel.discussionList.MainViewModel;

import java.io.FileInputStream;

public class MainViewController
{
  @FXML private JFXListView<Label> listView;
  @FXML private TextField searchField;

  private ViewHandler viewHandler;
  private MainViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, MainViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    for (int i = 0; i < 25; i++)
    {
      Label label = new Label("Item: " + i);
      listView.getItems().add(label);
    }
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  public void settingsButtonPressed(ActionEvent actionEvent)
  {
  }

  public void addButtonPressed(ActionEvent actionEvent)
  {
  }
}

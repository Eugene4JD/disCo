package view.discussionList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.ClientModel;
import view.ViewHandler;
import viewmodel.discussionList.MainViewModel;

import java.io.FileInputStream;

public class MainViewController
{
  @FXML private JFXListView<Label> listView;
  @FXML private TextField searchField;
  @FXML private JFXPopup popup;
  @FXML private ComboBox<String> searchSelector;

  private ViewHandler viewHandler;
  private MainViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, MainViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    listView.setItems(viewModel.getMessages());
    searchSelector.setItems(viewModel.getSearchSelector());
    searchField.textProperty().bindBidirectional(viewModel.getSearch());
    // initPopup();
  }

  private void initPopup()
  {
    JFXButton b0 = new JFXButton("Enter");
    JFXButton b1 = new JFXButton("Edit");
    JFXButton b2 = new JFXButton("Remove");
    VBox vBox = new VBox(b0, b1, b2);
    popup.setPopupContent(vBox);
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
    viewHandler.openView("chat");
  }

  public void addButtonPressed(ActionEvent actionEvent)
  {
    viewHandler.openView("add");
  }

  public void doubleClickOnThread(MouseEvent mouseEvent)
  {
    if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
    {
      if (mouseEvent.getClickCount() == 2)
      {
        Label selectedItem = listView.getSelectionModel().getSelectedItem();
        System.out.printf(selectedItem.getText());
      }
    }
  }

  public ClientModel getModel()
  {
    return viewModel.getModel();
  }

  public void searchRefresh(KeyEvent keyEvent)
  {
    if (searchSelector.getValue().equals("by TITLE"))
    {
      viewModel.searchByName();
    }
    if (searchSelector.getValue().equals("by ID"))
    {
      viewModel.searchById();
    }
  }
}

package view.discussionList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.application.Platform;
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
import javafx.scene.text.Text;
import model.ClientModel;
import view.ViewHandler;
import viewmodel.discussionList.MainViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;

public class MainViewController implements PropertyChangeListener
{
  @FXML private JFXListView<Label> listView;
  @FXML private TextField searchField;
  @FXML private JFXPopup popup;
  @FXML private ComboBox<String> searchSelector;
  @FXML private Text usernamesThreads;

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
    usernamesThreads.textProperty()
        .bindBidirectional(viewModel.getUserNamesThreads());
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
    viewHandler.openView("settings");
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
        if ((!viewModel.checkSearch(selectedItem.textProperty().get())))
        {
          viewModel.logToDiscussion(selectedItem.textProperty().get());
        }
        else
        {
          viewModel.selectDiscussion(selectedItem.textProperty().get());
          viewHandler.openView("chat");
        }
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

      if (searchField.getText().equals(""))
        viewModel.load();
      else
        viewModel.searchByName();
    }
    if (searchSelector.getValue().equals("by ID"))
    {
      if (searchField.getText().equals(""))
        viewModel.load();
      else
        viewModel.searchById();
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->{
      switch (evt.getPropertyName())
      {
        case "LogToExistingDiscussion":
          viewHandler.openView("chat");
      }
    });
  }
}

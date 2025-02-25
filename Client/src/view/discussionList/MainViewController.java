package view.discussionList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
import javafx.stage.Stage;
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
  @FXML private ComboBox<String> searchSelector;
  @FXML private Text usernamesThreads;
  @FXML private ImageView logo;
  @FXML private VBox vBox;
  @FXML private JFXSpinner spinner;

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
    viewModel.addListener(this);
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
    viewModel.settings();
  }

  public void addButtonPressed(ActionEvent actionEvent)
  {
    viewModel.add();
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
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "LogToExistingDiscussion":
          removeLoading();
          viewHandler.openView("chat");
          break;
        case "OpenSettings":
        {
          viewHandler.openView("settings");
          break;
        }
        case "OpenAdd":
        {
          viewHandler.openView("add");
          break;
        }
        case "AlertGuest":
        {
          accessDenied();
          break;
        }
        case "AlertLetters":
        {
          noLettersAllowed();
          break;
        }
        case "Loading":
        {
          setLoading();
          break;
        }
      }
    });
  }

  private void accessDenied()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("This feature is only available for registered users!");
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    alert.showAndWait();
  }

  private void noLettersAllowed()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Letter are not allowed for ID search!");
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    alert.showAndWait();
  }

  public void comboHidden(Event event)
  {
    viewModel.load();
  }

  private void setLoading()
  {
    logo.setOpacity(0.5);
    usernamesThreads.setOpacity(0.5);
    spinner.visibleProperty().setValue(true);
    vBox.disableProperty().setValue(true);
  }

  private void removeLoading()
  {
    logo.setOpacity(1);
    usernamesThreads.setOpacity(1);
    spinner.visibleProperty().setValue(false);
    vBox.disableProperty().setValue(false);
  }
}

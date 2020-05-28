package view.discussionList;

import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.ViewHandler;
import viewmodel.discussionList.AddViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddViewController implements PropertyChangeListener
{
  @FXML private ImageView logo;
  @FXML private VBox vBox;
  @FXML private TextField enterField;
  @FXML private JFXSpinner spinner;

  private ViewHandler viewHandler;
  private AddViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, AddViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    viewModel.addListener(this);
    this.root = root;
    enterField.textProperty().bindBidirectional(viewModel.getEnterProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public void createThreadButtonPressed()
  {
    viewModel.createThread();
  }

  public void cancelButtonPressed(ActionEvent actionEvent)
  {
    viewHandler.openView("main");
  }

  public Region getRoot()
  {
    return root;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "AnswerReceived":
          logo.setOpacity(1);
          spinner.visibleProperty().setValue(false);
          vBox.disableProperty().setValue(false);
          viewHandler.openView("main");
          break;
        case "Alert":
          alertWindow();
          break;
        case "Loading":
          logo.setOpacity(0.5);
          spinner.visibleProperty().setValue(true);
          vBox.disableProperty().setValue(true);
          break;
      }
    });
  }

  public void alertWindow()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Untitled threads are not allowed");
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    alert.showAndWait();
  }

  public void onEnter(KeyEvent keyEvent)
  {
    if (keyEvent.getCode() == KeyCode.ENTER)
    {
      createThreadButtonPressed();
    }
  }
}

package view.chat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewmodel.chat.RenameViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RenameViewController implements PropertyChangeListener
{
  @FXML private TextField enterField;
  @FXML private Label oldTitleLabel;

  private ViewHandler viewHandler;
  private RenameViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, RenameViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    viewModel.addListener(this);
    this.root = root;

    enterField.textProperty().bindBidirectional(viewModel.getEnterProperty());
    oldTitleLabel.textProperty().bindBidirectional(viewModel.getOldProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public void saveButtonPressed(ActionEvent actionEvent)
  {
    viewModel.rename();
  }

  public void cancelButtonPressed(ActionEvent actionEvent)
  {
    viewHandler.openView("chat");
  }

  public Region getRoot()
  {
    return root;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->{
      switch (evt.getPropertyName())
      {
        case "AnswerReceived":
          viewHandler.openView("chat");
      }
    });
  }
}

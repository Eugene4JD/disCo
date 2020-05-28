package view.discussionList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewHandler;
import viewmodel.discussionList.AddViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddViewController implements PropertyChangeListener
{
  @FXML private TextField enterField;

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

  public void createThreadButtonPressed(ActionEvent actionEvent)
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
    Platform.runLater(()->{
      switch (evt.getPropertyName())
      {
        case "AnswerReceived":
          viewHandler.openView("main");
          break;
      }
    });
  }
}

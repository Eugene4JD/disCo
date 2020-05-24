package viewmodel.discussionList;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.ClientModel;
import model.Discussion;
import model.DiscussionList;
import model.Message;
import utility.observer.listener.LocalListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewModel implements PropertyChangeListener
{
  private ClientModel model;
  private StringProperty search;
  private ObservableList<Label> listView;
  private ObservableList<String> searchSelector;

  public MainViewModel(ClientModel model)
  {
    search = new SimpleStringProperty();
    listView = FXCollections.observableArrayList();
    searchSelector = FXCollections.observableArrayList();
    searchSelector.addAll("by TITLE", "by ID");
    model.addListener(this);
    this.model = model;
    // updateListView();
  }

  public void clear()
  {

  }

  public ObservableList<Label> getMessages()
  {
    return listView;
  }

  public ObservableList<String> getSearchSelector()
  {
    return searchSelector;
  }

  public void updateListView()
  {
    DiscussionList list = model.getDiscussionListBuffer();
    System.out.println(list.toString());
    for (int i = 0; i < list.size(); i++)
    {
      Label label = new Label(list.getDiscussion(i).getDiscussionName());
      listView.add(label);
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "Add":
          Discussion discussion = (Discussion) evt.getNewValue();
          listView.add(new Label(
              discussion.getDiscussionId() + "      " + discussion
                  .getDiscussionName()));
          break;
        case "AddList":
          DiscussionList list = (DiscussionList) evt.getNewValue();
          for (int i = 0; i < list.size(); i++)
          {
            listView.add(new Label(
                list.getDiscussion(i).getDiscussionId() + "      " + list
                    .getDiscussion(i).getDiscussionName()));
          }
          break;
      }
    });
  }

  public ClientModel getModel()
  {
    return model;
  }

  public void searchByName()
  {
    DiscussionList list;
  }

  public void searchById()
  {

  }

  public StringProperty getSearch()
  {
    return search;
  }
}

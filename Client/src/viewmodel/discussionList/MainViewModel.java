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
import utility.UnnamedPropertyChangeSubject;
import utility.observer.listener.LocalListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private ClientModel model;
  private StringProperty search;
  private ObservableList<Label> listView;
  private ObservableList<String> searchSelector;
  private StringProperty usernamesThreads;
  private PropertyChangeSupport property;

  public MainViewModel(ClientModel model)
  {
    search = new SimpleStringProperty();
    listView = FXCollections.observableArrayList();
    searchSelector = FXCollections.observableArrayList();
    searchSelector.addAll("by TITLE", "by ID");
    model.addListener(this);
    this.model = model;
    usernamesThreads = new SimpleStringProperty();
    property = new PropertyChangeSupport(this);
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

  public void load()
  {
    this.usernamesThreads.set(model.getLogin() + "'s threads ▼");
    this.search.set("");
    listView.clear();
    for (int i = 0; i < model.getDiscussionListBuffer().size(); i++)
    {
      listView.add(new Label(
          model.getDiscussionListBuffer().getDiscussion(i).getDiscussionId()
              + "      " + model.getDiscussionListBuffer().getDiscussion(i)
              .getDiscussionName()));
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
        case "LoggedToExistingDiscussion":
          System.out.println("it was here");
          model.selectDiscussion((int) evt.getNewValue());
          property.firePropertyChange("LogToExistingDiscussion", null,
              evt.getNewValue());
          break;
        case "searchByName":
          listView.clear();
          DiscussionList titleList = (DiscussionList) evt.getNewValue();
          for (int i = 0; i < titleList.size(); i++)
          {
            listView.add(new Label(
                titleList.getDiscussion(i).getDiscussionId() + "      "
                    + titleList.getDiscussion(i).getDiscussionName()));
          }
          break;
        case "searchByID":
          listView.clear();
          Discussion iD = (Discussion) evt.getNewValue();
          for (int i = 0; i < 1; i++)
          {
            listView.add(new Label(
                iD.getDiscussionId() + "      " + iD.getDiscussionName()));
          }
          break;
        case "ChangedDiscussionName":
        case "DiscussionRemoved":
          load();
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
    model.searchDiscussionsByName(search.get());
  }

  public void searchById()
  {
    if (containsLetters())
    {
    property.firePropertyChange("AlertLetters", null, "");
    load();
    }
    else
      model.searchDiscussionById(Integer.parseInt(search.get()));
  }

  public void logToDiscussion(String selectedLabel)
  {
    if (model.searchDiscussionIdByLabel(selectedLabel) == -1)
    {
      for (int i = 0; i < model.getLastSearchedDiscussions().size(); i++)
      {
        if (selectedLabel.equals(
            model.getLastSearchedDiscussions().getDiscussion(i)
                .getDiscussionId() + "      " + model
                .getLastSearchedDiscussions().getDiscussion(i)
                .getDiscussionName()))
        {
          model.logToExistingDiscussion(
              model.getLastSearchedDiscussions().getDiscussion(i)
                  .getDiscussionId());
        }
      }
    }
  }

  public boolean checkSearch(String selectedLabel)
  {
    if (model.searchDiscussionIdByLabel(selectedLabel) == -1)
    {
      return false;
    }
    else
      return true;
  }

  public void selectDiscussion(String selectedLabel)
  {
    //System.out.println();
    //model.logToExistingDiscussion(model.searchDiscussionIdByLabel(selectedLabel));
    model.selectDiscussion(model.searchDiscussionIdByLabel(selectedLabel));
  }

  public StringProperty getSearch()
  {
    return search;
  }

  public StringProperty getUserNamesThreads()
  {
    return usernamesThreads;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    this.property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    this.property.removePropertyChangeListener(listener);
  }

  public void settings()
  {
    if (model.getUserType().equals("Guest"))
    {
      property.firePropertyChange("AlertGuest", null, "");
    }
    else
      property.firePropertyChange("OpenSettings", null, "");
  }

  public void add()
  {
    if (model.getUserType().equals("Guest"))
    {
      property.firePropertyChange("AlertGuest", null, "");
    }
    else
      property.firePropertyChange("OpenAdd", null, "");
  }

  private boolean containsLetters()
  {
    String s = search.get();
    int len = s.length();
    for (int i = 0; i < len; i++)
    {
      if (Character.isLetter(s.charAt(i)) == true)
      {
        return true;
      }
    }
    return false;
  }
}

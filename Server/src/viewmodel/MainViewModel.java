package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.ServerModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewModel implements PropertyChangeListener
{
  private ServerModel model;
  private ObservableList<Label> logList;
  private StringProperty username;
  private StringProperty password;

  public MainViewModel(ServerModel model)
  {
    this.model = model;
    model.addListener(this);
    logList = FXCollections.observableArrayList();
    this.username = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
    this.username.set("");
    this.password.set("");
  }

  public ObservableList<Label> getLogList()
  {
    return logList;
  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public StringProperty getPasswordProperty()
  {
    return password;
  }
  

  public void createAdmin()
  {
    if ((!(username.get().equals(""))) && (!(password.get().equals(""))))
    {
      model.addNewUserToUserBase("Admin", username.get(), password.get());
      clear();
    }
  }
  public void clear()
  {
    username.set("");
    password.set("");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()->{
      switch (evt.getPropertyName())
      {
        case "NewLog":
          logList.add(new Label((String)evt.getNewValue()));
      }
    });
  }
}

package viewmodel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.ServerModel;

public class MainViewModel
{
  private ServerModel model;
  private ObservableList<Label> logList;
  private StringProperty username;
  private StringProperty password;

  public MainViewModel(ServerModel model)
  {
    this.model = model;
    this.username = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
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
    //
  }

  public void clear()
  {
    username.set("");
    password.set("");
  }
}

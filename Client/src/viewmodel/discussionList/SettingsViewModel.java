package viewmodel.discussionList;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;

public class SettingsViewModel
{
  private ClientModel model;
  private StringProperty username;
  private StringProperty newUsername;
  private StringProperty error;

  public SettingsViewModel(ClientModel model)
  {
    this.model = model;
    username = new SimpleStringProperty();
    newUsername = new SimpleStringProperty();
    error = new SimpleStringProperty();
  }

  public void clear()
  {

  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public StringProperty getNewUsernameProperty()
  {
    return newUsername;
  }

  public void load()
  {
    username.set(model.getLogin());
  }

  public StringProperty getError()
  {
    return error;
  }
}

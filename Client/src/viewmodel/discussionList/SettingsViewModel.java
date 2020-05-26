package viewmodel.discussionList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;

public class SettingsViewModel
{
  private ClientModel model;
  private StringProperty username;

  public SettingsViewModel(ClientModel model)
  {
    this.model = model;
    username = new SimpleStringProperty();
  }

  public void clear()
  {

  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }


}

package viewmodel.chat;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;

public class RenameViewModel
{
  private ClientModel model;
  private StringProperty enter;
  private StringProperty old;

  public RenameViewModel(ClientModel model)
  {
    this.model = model;
    enter = new SimpleStringProperty();
    old = new SimpleStringProperty();
  }

  public StringProperty getEnterProperty()
  {
    return enter;
  }

  public void clear()
  {
    enter.set("");
  }

  public void rename()
  {
    //
  }

  public StringProperty getOldProperty()
  {
    return old;
  }
}

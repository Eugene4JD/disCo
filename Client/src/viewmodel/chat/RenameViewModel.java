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

  public void load()
  {
    setOldName();
  }

  private void setOldName()
  {
    System.out.println(model.getDiscussionListBuffer()
        .getDiscussionById(model.getSelectedDiscussion()).getDiscussionName());
    old.set(model.getDiscussionListBuffer()
        .getDiscussionById(model.getSelectedDiscussion()).getDiscussionName());
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

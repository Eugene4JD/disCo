package viewmodel.discussionList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;
import model.Discussion;

import java.util.concurrent.ThreadLocalRandom;

public class AddViewModel
{
  private ClientModel model;
  private StringProperty enter;

  public AddViewModel(ClientModel model)
  {
    this.model = model;
    enter = new SimpleStringProperty();
  }

  public void clear()
  {

  }

  public StringProperty getEnterProperty()
  {
    return enter;
  }

  public void createThread()
  {
    int random = ThreadLocalRandom.current().nextInt(5, 1000);
    model.createDiscussion(enter.get());
  }

}

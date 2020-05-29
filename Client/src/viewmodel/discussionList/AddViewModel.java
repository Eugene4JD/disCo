package viewmodel.discussionList;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;
import model.Discussion;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.ThreadLocalRandom;

public class AddViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private ClientModel model;
  private StringProperty enter;
  private PropertyChangeSupport property;

  public AddViewModel(ClientModel model)
  {
    this.model = model;
    model.addListener(this);
    enter = new SimpleStringProperty();
    this.property = new PropertyChangeSupport(this);
  }

  public void clear()
  {

  }

  public StringProperty getEnterProperty()
  {
    return enter;
  }

  public void load()
  {
    enter.set("");
  }

  public void createThread()
  {
    if (enter.get().equals(""))
    {
      property.firePropertyChange("Alert", null, null);
    }
    else
    {
      try
      {
        model.createDiscussion(enter.get());
        property.firePropertyChange("Loading", null, null);
      }
      catch (Exception e)
      {
        // to do
      }

    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "Add":
          property
              .firePropertyChange("AnswerReceived", null, evt.getNewValue());
      }
    });
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    this.property.removePropertyChangeListener(listener);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    this.property.addPropertyChangeListener(listener);
  }
}

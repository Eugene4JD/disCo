package viewmodel.chat;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RenameViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private ClientModel model;
  private StringProperty enter;
  private StringProperty old;
  private PropertyChangeSupport property;

  public RenameViewModel(ClientModel model)
  {
    this.model = model;
    model.addListener(this);
    this.property = new PropertyChangeSupport(this);
    enter = new SimpleStringProperty();
    old = new SimpleStringProperty();
  }

  public void load()
  {
    setOldName();
    enter.set("");
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
    if (!enter.get().equals(""))
    {
      try
      {
        model.changeNameOfDiscussion(model.getSelectedDiscussion(), enter.get());
        property.firePropertyChange("Loading", null, "");
      }
      catch (Exception e)
      {
        property.firePropertyChange("IllegalAccess",null,"");
      }
    }
    else
      property.firePropertyChange("AlertEmpty", null, "");
  }

  public StringProperty getOldProperty()
  {
    return old;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "ChangedDiscussionName":
          property
              .firePropertyChange("AnswerReceived", null, evt.getNewValue());
          break;
      }
    });
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}

package viewmodel.login;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private ClientModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty error;
  private PropertyChangeSupport property;

  public LoginViewModel(ClientModel model)
  {
    this.model = model;
    model.addListener(this);
    this.property = new PropertyChangeSupport(this);
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    error = new SimpleStringProperty();
  }

  public void clear()
  {
    username.set("");
    password.set("");
    error.set("");
  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public StringProperty getPasswordProperty()
  {
    return password;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public void logIn()
  {
    String username = this.username.get();
    String password = this.password.get();
    if (username.equals(""))
    {
      error.set("empty username");

    }
    else if (password.equals(""))
    {
      error.set("empty password");
    }
    else
      model.log(username, password, false);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "LogStatus":
          if (evt.getNewValue().equals(false))
          {
            error.set("wrong username/password");
          }
          else
            property.firePropertyChange("LogStatus", null, evt.getNewValue());
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

package viewmodel.login;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RegistrationViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private ClientModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty repeatPassword;
  private StringProperty error;
  private PropertyChangeSupport property;

  public RegistrationViewModel(ClientModel model)
  {
    this.model = model;
    model.addListener(this);
    this.property = new PropertyChangeSupport(this);
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    repeatPassword = new SimpleStringProperty();
    error = new SimpleStringProperty();
  }

  public void clear()
  {
    username.set("");
    password.set("");
    repeatPassword.set("");
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

  public StringProperty getRepeatPasswordProperty()
  {
    return repeatPassword;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public void signUp()
  {
    String username = this.username.get();
    String password0 = this.password.get();
    String password1 = this.repeatPassword.get();
    if (username.equals(""))
    {
      error.set("empty username");
    }
    else if (username.contains(" "))
    {
      error.set("invalid username");
    }
    else if (password0.equals(""))
    {
      error.set("empty password");
    }
    else if (password0.contains(" "))
    {
      error.set("invalid password");
    }
    else if (!password1.equals(password0))
    {
      error.set("passwords do not match");
    }
    else
    {
      model.log(username, password0, true);
      property.firePropertyChange("Loading", null, true);
    }

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "LogStatus":
          if (evt.getNewValue().equals(false))
          {
            property.firePropertyChange("Loading", null, false);
            error.set("try another username");
          }
          else
          {
            property.firePropertyChange("Loading", null, false);
            property.firePropertyChange("LogStatus", null, null);
          }

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

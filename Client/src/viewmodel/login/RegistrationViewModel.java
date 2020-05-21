package viewmodel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegistrationViewModel implements PropertyChangeListener
{
  private ClientModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty repeatPassword;
  private boolean isLogInSuccess;

  public RegistrationViewModel(ClientModel model)
  {
    this.model = model;
    model.addListener(this);
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    repeatPassword = new SimpleStringProperty();
  }

  public void clear()
  {
    username.set(null);
    password.set(null);
    repeatPassword.set(null);
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

  public void signUp()
  {
    String username = this.username.get();
    String password = this.password.get();
    model.log(username, password, true);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "LogStatus":
        if (evt.getNewValue().equals(true))
        {
          isLogInSuccess = true;
        }
        else
          isLogInSuccess = false;
    }
  }
}

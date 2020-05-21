package viewmodel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener
{
  private ClientModel model;
  private StringProperty username;
  private StringProperty password;

  public LoginViewModel(ClientModel model)
  {
    this.model = model;
    model.addListener(this);
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
  }

  public void clear()
  {
    username.set(null);
    password.set(null);
  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public StringProperty getPasswordProperty()
  {
    return password;
  }

  public void logIn()
  {
    //String username = this.username.get();
    //String password = this.password.get();
    //model.log(username, password, false);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "LogStatus":
        
    }
  }
}

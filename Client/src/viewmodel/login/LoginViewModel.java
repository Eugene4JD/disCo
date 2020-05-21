package viewmodel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;

public class LoginViewModel
{
  private ClientModel model;
  private StringProperty username;
  private StringProperty password;

  public LoginViewModel(ClientModel model)
  {
    this.model = model;
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
    String username = this.username.get();
    String password = this.password.get();
    System.out.printf(password);
  }
}

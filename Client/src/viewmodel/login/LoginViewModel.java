package viewmodel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel
{
  private Model model;
  private StringProperty username;
  private StringProperty password;

  public LoginViewModel(Model model)
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
}

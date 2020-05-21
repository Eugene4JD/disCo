package viewmodel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;

public class RegistrationViewModel
{
  private ClientModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty repeatPassword;

  public RegistrationViewModel(ClientModel model)
  {
    this.model = model;
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
}

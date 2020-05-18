package viewmodel;

import model.ClientModel;
import viewmodel.login.LoginViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginViewModel;

  public ViewModelFactory(ClientModel model)
  {
    loginViewModel = new LoginViewModel(model);
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }
}

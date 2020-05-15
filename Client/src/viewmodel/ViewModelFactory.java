package viewmodel;

import model.Model;
import viewmodel.login.LoginViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginViewModel;

  public ViewModelFactory(Model model)
  {
    loginViewModel = new LoginViewModel(model);
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }
}

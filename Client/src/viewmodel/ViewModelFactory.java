package viewmodel;

import model.ClientModel;
import viewmodel.discussionList.MainViewModel;
import viewmodel.login.LoginViewModel;
import viewmodel.login.RegistrationViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginViewModel;
  private RegistrationViewModel registrationViewModel;
  private MainViewModel mainViewModel;

  public ViewModelFactory(ClientModel model)
  {
    loginViewModel = new LoginViewModel(model);
    registrationViewModel = new RegistrationViewModel(model);
    mainViewModel = new MainViewModel(model);
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

  public RegistrationViewModel getRegistrationViewModel()
  {
    return registrationViewModel;
  }

  public MainViewModel getMainViewModel()
  {
    return mainViewModel;
  }
}

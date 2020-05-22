package viewmodel;

import model.ClientModel;
import viewmodel.chat.ChatViewModel;
import viewmodel.discussionList.MainViewModel;
import viewmodel.login.LoginViewModel;
import viewmodel.login.RegistrationViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginViewModel;
  private RegistrationViewModel registrationViewModel;
  private MainViewModel mainViewModel;
  private ChatViewModel chatViewModel;

  public ViewModelFactory(ClientModel model)
  {
    loginViewModel = new LoginViewModel(model);
    registrationViewModel = new RegistrationViewModel(model);
    mainViewModel = new MainViewModel(model);
    chatViewModel = new ChatViewModel(model);
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

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }
}

package viewmodel;

import model.ClientModel;
import viewmodel.chat.ChatViewModel;
import viewmodel.discussionList.AddViewModel;
import viewmodel.discussionList.MainViewModel;
import viewmodel.discussionList.SettingsViewModel;
import viewmodel.login.LoginViewModel;
import viewmodel.login.RegistrationViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginViewModel;
  private RegistrationViewModel registrationViewModel;
  private MainViewModel mainViewModel;
  private ChatViewModel chatViewModel;
  private AddViewModel addViewModel;
  private SettingsViewModel settingsViewModel;

  public ViewModelFactory(ClientModel model)
  {
    loginViewModel = new LoginViewModel(model);
    registrationViewModel = new RegistrationViewModel(model);
    mainViewModel = new MainViewModel(model);
    chatViewModel = new ChatViewModel(model);
    addViewModel = new AddViewModel(model);
    settingsViewModel = new SettingsViewModel(model);
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

  public AddViewModel getAddViewModel()
  {
    return addViewModel;
  }

  public SettingsViewModel getSettingsViewModel()
  {
    return settingsViewModel;
  }
}

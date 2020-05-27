package viewmodel;

import model.ServerModel;

public class ViewModelFactory
{
  private MainViewModel mainViewModel;

  public ViewModelFactory(ServerModel model)
  {
    mainViewModel = new MainViewModel(model);
  }

  public MainViewModel getMainViewModel()
  {
    return mainViewModel;
  }

}

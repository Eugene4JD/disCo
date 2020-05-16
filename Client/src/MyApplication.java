import javafx.application.Application;
import javafx.stage.Stage;
import model.ClientModel;
import model.ClientModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private ClientModel model;

  @Override public void start(Stage primaryStage)
  {
    model = new ClientModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(primaryStage);
  }
}

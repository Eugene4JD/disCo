import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ServerConnector;
import model.ServerModel;
import model.ServerModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private ServerModel model;

  @Override public void start(Stage primaryStage) throws Exception
  {
    model = new ServerModelManager();
    ServerConnector connector = new ServerConnector(model);
    Thread thread1 = new Thread(connector);
    thread1.start();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);

    viewHandler.start(primaryStage);
  }
}

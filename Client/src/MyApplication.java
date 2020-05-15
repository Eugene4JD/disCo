import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  private Model model;

  @Override public void start(Stage primaryStage)
  {
    model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(primaryStage);
  }
}

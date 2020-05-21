import mediator.ServerClientHandler;
import mediator.ServerConnector;
import model.ServerModel;
import model.ServerModelManager;

public class SERVER
{
  public static void main(String[] args)
  {
    ServerModel model = new ServerModelManager();
    ServerConnector connector = new ServerConnector(model);
    Thread thread1 = new Thread(connector);
    thread1.start();

  }
}

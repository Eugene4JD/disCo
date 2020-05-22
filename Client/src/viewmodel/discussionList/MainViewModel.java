package viewmodel.discussionList;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.ClientModel;
import model.DiscussionList;

public class MainViewModel
{
  private ClientModel model;
  private ObservableList<Label> listView;

  public MainViewModel(ClientModel model)
  {
    this.model = model;
    updateListView();
  }

  public void clear()
  {

  }

  public ObservableList<Label> getMessages()
  {
    return listView;
  }

  public void updateListView()
  {
    listView = FXCollections.observableArrayList();
    /*
    for (int i = 0; i < 25; i++)
    {
      Label label = new Label("Item: " + i);
      listView.add(label);
    }
     */
    DiscussionList list = model.getDiscussionListBuffer();
    for (int i = 0; i < list.size(); i++)
    {
      Label label = new Label(list.getDiscussion(i).getDiscussionName());
      listView.add(label);
    }
  }

}

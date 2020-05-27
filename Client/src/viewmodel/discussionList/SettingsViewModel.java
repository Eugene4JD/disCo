package viewmodel.discussionList;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ClientModel;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsViewModel
    implements PropertyChangeListener, UnnamedPropertyChangeSubject
{
  private ClientModel model;
  private StringProperty username;
  private StringProperty newUsername;
  private StringProperty newPassword1;
  private StringProperty newPassword2;
  private StringProperty oldPassword;
  private StringProperty error;
  private PropertyChangeSupport property;

  public SettingsViewModel(ClientModel model)
  {
    this.model = model;
    property = new PropertyChangeSupport(this);
    model.addListener(this);
    username = new SimpleStringProperty();
    newUsername = new SimpleStringProperty();
    error = new SimpleStringProperty();
    newPassword1 = new SimpleStringProperty();
    newPassword2 = new SimpleStringProperty();
    oldPassword = new SimpleStringProperty();
  }

  public void clear()
  {
    this.load();
  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public StringProperty getNewUsernameProperty()
  {
    return newUsername;
  }

  public StringProperty getNewPassword1()
  {
    return newPassword1;
  }

  public void applyButton()
  {
    if (this.newUsername.get().equals("") && (!(this.oldPassword.get().equals("")) && (!(this.newPassword1.get().equals(""))) && (!this.newPassword2.get().equals(""))))
    {
      System.out.println("this");
      if (this.newPassword1.get().equals(this.newPassword2.get()))
      {
        System.out.println("And this this");
        model.changePassword(oldPassword.get(),newPassword1.get());
      }
      else
      {
        this.error.set("! New Password is not the same in both fields...");
      }
    }
    else if (!(this.newUsername.get().equals("")) && this.oldPassword.get().equals("") && this.newPassword1.get().equals("") && this.newPassword2.get().equals(""))
    {
      model.changeLogin(newUsername.get());
    }
    else  if ((!(this.newUsername.get().equals(""))) && (!(this.oldPassword.get().equals("")) && (!(this.newPassword1.get().equals(""))) && (!this.newPassword2.get().equals(""))))
    {
      if (this.newPassword1.get().equals(this.newPassword2.get()))
      {
        model.changePassword(oldPassword.get(),newPassword1.get());
        model.changeLogin(newUsername.get());
      }
      else
      {
        this.error.set("! New Password is not the same in both fields...");
      }
    }
    else
    {
      error.set("! Fields are not used correctly...");
    }

  }

  public StringProperty getNewPassword2()
  {
    return newPassword2;
  }

  public StringProperty getOldPassword()
  {
    return oldPassword;
  }

  public void load()
  {
    username.set(model.getLogin());
    newUsername.set("");
    oldPassword.set("");
    newPassword1.set("");
    newPassword2.set("");
    error.set("");
  }

  public StringProperty getError()
  {
    return error;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "ChangedUserName":
          load();
          break;
        case "ChangedPassword":
          load();
          this.error.set(evt.getNewValue().toString());
          break;
        case "RemoveUser":
          property.firePropertyChange("RemovedUser", null, evt.getNewValue());
          break;
      }
    });
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
}

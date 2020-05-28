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
  boolean trigger = false;

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
    String user = this.newUsername.get();
    String oldPass = this.oldPassword.get();
    String newPass1 = this.newPassword1.get();
    String newPass2 = this.newPassword2.get();

    // 1 Password change
    if (user.equals("") && (!(oldPass.equals("")) && (!(newPass1.equals("")))
        && (!(newPass2.equals("")))))
    {
      if (newPass1.equals(newPass2))
      {
        if (newPass1.contains(" "))
        {
          error.set("no spaces for passwords");
        }
        else
        {
          model.changePassword(oldPass, newPass1);
          property.firePropertyChange("Loading", null, true);
        }
      }
      else
      {
        this.error.set("new passwords do not match");
      }
    }
    // 2 Username change
    else if (!(user.equals("")) && oldPass.equals("") && newPass1.equals("")
        && newPass2.equals(""))
    {
      if (user.contains(" "))
      {
        error.set("no spaces in usernames");
      }
      else
      {
        model.changeLogin(user);
        property.firePropertyChange("Loading", null, true);
      }

    }
    else if ((!(user.equals(""))) && (!(oldPass.equals("")) && (!(newPass1
        .equals(""))) && (!newPass2.equals(""))))
    {
      if (user.contains(" "))
      {
        this.error.set("no spaces for usernames");
      }
      else if (newPass1.contains(" "))
      {
        this.error.set("no spaces for passwords");
      }
      else if (newPass1.equals(newPass2))
      {
        model.changePassword(oldPass, newPass1);
        model.changeLogin(user);
        trigger = true;
        property.firePropertyChange("Loading", null, true);
      }
      else
      {
        this.error.set("new passwords do not match");
      }
    }
    else
    {
      error.set("something went wrong");
    }
  }

  public void removeUserButton()
  {
    this.model.removeItself();
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
          this.error.set("username changed");
          property.firePropertyChange("Loading", null, "changedUsername");
          trigger = false;
          break;
        case "ChangedPassword":
          load();
          this.error.set(evt.getNewValue().toString());
          if (!trigger)
          {
            property.firePropertyChange("Loading", null, "changedPassword");
          }
          break;
        case "RemoveUser":
          property.firePropertyChange("RemovedUser", null,evt.getNewValue());
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

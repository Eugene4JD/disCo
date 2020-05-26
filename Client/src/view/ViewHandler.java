package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.chat.ChatViewController;
import view.discussionList.AddViewController;
import view.discussionList.MainViewController;
import view.discussionList.SettingsViewController;
import view.login.LoginViewController;
import view.login.RegistrationViewController;
import viewmodel.ViewModelFactory;

import javax.sound.sampled.*;
import java.io.File;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private LoginViewController loginViewController;
  private RegistrationViewController registrationViewController;
  private MainViewController mainViewController;
  private ChatViewController chatViewController;
  private AddViewController addViewController;
  private SettingsViewController settingsViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("login");
    try
    {
      AudioInputStream audioInputStream = AudioSystem
          .getAudioInputStream(new File("Client/src/resources/intro.wav"));
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    }
    catch (Exception e)
    {
    }
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "login":
        root = loadLoginView("login/LoginView.fxml");
        break;
      case "registration":
        root = loadRegistrationView("login/RegistrationView.fxml");
        break;
      case "main":
        root = loadMainView("discussionList/MainView.fxml");
        break;
      case "chat":
        root = loadChatView("chat/ChatView.fxml");
        break;
      case "add":
        root = loadAddView("discussionList/AddView.fxml");
        break;
      case "settings":
        root = loadSettingsView("discussionList/SettingsView.fxml");
        break;
    }
    currentScene.setRoot(root);

    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.getIcons()
        .add(new Image(getClass().getResourceAsStream("/resources/exp.png")));
    primaryStage.centerOnScreen();
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public void closeView()
  {
    primaryStage.close();
  }

  private Region loadLoginView(String fxmlFile)
  {
    if (loginViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        loginViewController = loader.getController();
        loginViewController
            .init(this, viewModelFactory.getLoginViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      loginViewController.reset();
    }
    return loginViewController.getRoot();
  }

  private Region loadRegistrationView(String fxmlFile)
  {
    if (registrationViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        registrationViewController = loader.getController();
        registrationViewController
            .init(this, viewModelFactory.getRegistrationViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      registrationViewController.reset();
    }
    return registrationViewController.getRoot();
  }

  private Region loadMainView(String fxmlFile)
  {
    if (mainViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        mainViewController = loader.getController();
        mainViewController
            .init(this, viewModelFactory.getMainViewModel(), root);
        viewModelFactory.getMainViewModel().load();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      mainViewController.reset();
    }
    return mainViewController.getRoot();
  }

  private Region loadChatView(String fxmlFile)
  {
    if (chatViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        chatViewController = loader.getController();
        chatViewController
            .init(this, viewModelFactory.getChatViewModel(), root);
        chatViewController.load();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

    }
    else
    {
      chatViewController.reset();
      chatViewController.load();
    }
    return chatViewController.getRoot();
  }

  private Region loadAddView(String fxmlFile)
  {
    if (addViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addViewController = loader.getController();
        addViewController.init(this, viewModelFactory.getAddViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      addViewController.reset();
    }
    return addViewController.getRoot();
  }

  private Region loadSettingsView(String fxmlFile)
  {
    if (settingsViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        settingsViewController = loader.getController();
        settingsViewController
            .init(this, viewModelFactory.getSettingsViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      settingsViewController.reset();
    }
    return settingsViewController.getRoot();
  }

}

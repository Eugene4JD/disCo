package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.discussionList.MainViewController;
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
    primaryStage.show();
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

}

import mediator.RemoteModel;
import mediator.RemoteModelProxy;
import model.ClientModel;
import model.ClientModelManager;
import java.util.Scanner;

public class test
{
  //  1 void connect() throws IOException;
  //  2 void disconnect() throws IOException;
  //  3 void log(String login, String password, boolean isNewUser);
  //  4 void logToExistingDiscussion(String discussionId,String login);
  //  5 void createNewDiscussion(String discussionId,String loginOfEditor);
  //  6 void sendMessageInDiscussion(String discussionId, String sender, String message);
  //  7 void removeDiscussion(String discussionId);
  public static void main(String[] args)
  {
    try
    {
      ClientModel model=new ClientModelManager();
      Scanner in = new Scanner(System.in);
      System.out.println("Which function to test enter number");
      while (true)
      {
        int functionID = in.nextInt();
        switch (functionID)
        {
          case 1:
            model.connect();
            System.out.println("model connected");
            break;
          case 2:
            model.disconnect();
            System.out.println("model disconnected");
            break;
          case 3:
            in.nextLine();
            System.out.println("write String login");
            String login=in.nextLine();
            System.out.println("Write String password");
            String password=in.nextLine();
            System.out.println("Write boolean isNewUser");
            String isNewUser=in.nextLine();
            if (isNewUser.equals("true"))
            {
                model.log(login,password,true);
            }
            else
            {
              model.log(login,password,false);
            }
            System.out.println("log added");
            break;
          case 4:
            System.out.println("write String discussionId");
            String discussionId=in.nextLine();
            System.out.println("Write String login");
            String login1=in.nextLine();
            System.out.println("logToExistingDiscussion");
            break;
          case 5:
            in.nextLine();
            System.out.println("write String discussionId");
            String discussionId1=in.nextLine();
            //System.out.println("Write String loginOfEditor");
           // String loginOfEditor=in.nextLine();
            model.createDiscussion(discussionId1);
            System.out.println("createNewDiscussion completed");
            break;
          case 6:
            System.out.println("write String discussionId");
            String discussionId2=in.nextLine();
            System.out.println("Write String sender");
            String sender=in.nextLine();
            System.out.println("Write String message");
            String message=in.nextLine();
            System.out.println("sendMessageInDiscussion compelted");
            break;
          case 7:
            System.out.println("write String discussionId");
            in.nextLine();
            String discussionId3=in.nextLine();
            int discussionID = model.getDiscussionIdFromBuffer(discussionId3);
            int userID = model.getId();
            model.removeDiscussion(discussionID,userID);
            System.out.println("removed discussion");

            break;
          default:
            System.out.println("There are no function for this number");
        }
      }
    }
    catch (Exception e)
    {

    }
  }
}


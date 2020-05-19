package network;

public class CreateDiscussionRequest extends Request
{
  private  String discussionId;
  private String editorLogin;
  public CreateDiscussionRequest(String discussionId,String editorLogin)
  {
    super(RequestType.CreateDiscussion);
    this.discussionId =discussionId;
    this.editorLogin = editorLogin;
  }

  public String getDiscussionId()
  {
    return discussionId;
  }

  public String getEditorLogin()
  {
    return editorLogin;
  }
}

package network;

import model.Discussion;

public class CreateDiscussionRequest extends Request
{
  private Discussion discussion;
  public CreateDiscussionRequest(Discussion discussion)
  {
    super(RequestType.CreateDiscussion);
    this.discussion = discussion;
  }

  public Discussion getDiscussion()
  {
    return discussion;
  }
}

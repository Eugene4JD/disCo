package network;

public class SearchDiscussionByIdRequest extends Request
{
  int id;

  public SearchDiscussionByIdRequest(int id)
  {
    super(RequestType.SearchDiscussionById);
    this.id = id;
  }

  public int getId()
  {
    return id;
  }
}

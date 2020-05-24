package network;

public class SearchDiscussionsByNameRequest extends Request
{
  String name;
  public SearchDiscussionsByNameRequest(String name)
  {
    super(RequestType.SearchDiscussionsByName);
  }

  public String getName()
  {
    return name;
  }
}

package network;

public class SearchDiscussionsByNameRequest extends Request
{
  String name;
  public SearchDiscussionsByNameRequest(String name)
  {
    super(RequestType.SearchDiscussionsByName);
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
}

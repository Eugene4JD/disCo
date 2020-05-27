package model;

import java.util.ArrayList;

public class MessageList
{
  private ArrayList<Message> messages;

  public MessageList()
  {
    messages = new ArrayList<>();
  }

  public void addMessage(String text, int messageId)
  {
    messages.add(new Message(text, messageId));
  }

  public void clearList()
  {
    int index = messages.size();
    for (int i = 0; i < index; i++)
    {
      messages.remove(0);
    }
  }
  public Message getMessage(int index)
  {
    return messages.get(index);
  }

  public int size()
  {
    return messages.size();
  }

  private int getNewId()
  {
    return this.messages.size() + 1;
  }
}

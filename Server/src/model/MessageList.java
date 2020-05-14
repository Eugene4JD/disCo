package model;

import java.util.ArrayList;

public class MessageList
{
  private ArrayList<Message> messages;

  public MessageList()
  {
    messages = new ArrayList<>();
  }

  public void addMessage(String text, String senderId)
  {
    messages.add(new Message(senderId,text,getNewId()));
  }

  public void clearList()
  {
    int index = messages.size();
    for (int i =0; i<index; i++)
    {
      messages.remove(0);
    }
  }

  public Message getMessageById(int id)
  {
    for (int i =0; i<messages.size(); i++)
    {
      if (messages.get(i).getMessageId() == id)
      {
        return messages.get(i);
      }
    }
    return null;
  }

  private int getNewId()
  {
    return this.messages.size()+1;
  }
}

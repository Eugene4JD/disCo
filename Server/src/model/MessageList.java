package model;

import java.util.ArrayList;

/**
 * Class representing message list
 *
 * @author Group 2
 * @version 1.1
 */
public class MessageList
{
  private ArrayList<Message> messages;

  /**
   * Zero argument constructor
   * initializing the array list of message
   */
  public MessageList()
  {
    messages = new ArrayList<>();
  }

  /**
   * adding new message to this message list
   *
   * @param text      text of the message
   * @param messageId message id
   */
  public void addMessage(String text, int messageId)
  {
    messages.add(new Message(text, messageId));
  }

  /**
   * clearing the message list by deleting every message from it
   */
  public void clearList()
  {
    int index = messages.size();
    for (int i = 0; i < index; i++)
    {
      messages.remove(0);
    }
  }

  /**
   * returning the message by given message id
   *
   * @param index given message id
   * @return the message by given message id
   */
  public Message getMessage(int index)
  {
    return messages.get(index);
  }

  /**
   * returning the message list size
   *
   * @return size of message list
   */
  public int size()
  {
    return messages.size();
  }

}

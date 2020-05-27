package model;

import java.io.Serializable;

public class Message implements Serializable
{
  private String text;
  private int messageId;

  public Message(String text, int messageId)
  {
    this.text = text;
    this.messageId = messageId;
  }

  public String getText()
  {
    return this.text;
  }

  public int getMessageId()
  {
    return this.messageId;
  }


  public String toString()
  {
    return this.text;
  }

  @Override public boolean equals(Object obj)
  {
    if (!(obj instanceof Message))
      return false;
    Message newMessage = (Message) obj;
    return newMessage.getMessageId() == this.messageId;
  }
}

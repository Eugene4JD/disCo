package model;

import java.io.Serializable;

public class Message implements Serializable
{
  private String text;
  private int messageId;
  private String senderId;
  private DateTime dateTime;

  public Message(String senderId, String text, int messageId)
  {
    this.dateTime = new DateTime();
    this.senderId = senderId;
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

  public String getSenderId()
  {
    return this.senderId;
  }

  public String toString()
  {
    return this.dateTime.getTimestamp() + " " + senderId + " : " + text;
  }

  @Override public boolean equals(Object obj)
  {
    if (!(obj instanceof Message))
      return false;
    Message newMessage = (Message) obj;
    return newMessage.getMessageId() == this.messageId;
  }
}

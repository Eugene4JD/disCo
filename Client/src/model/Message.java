package model;

import java.io.Serializable;

/**
 * class representing Message
 *
 * @author Group 2
 * @version 1.1
 */
public class Message implements Serializable
{
  private String text;
  private int messageId;

  /**
   * Two arguments constructor
   *
   * @param text      text of the message
   * @param messageId message id
   */
  public Message(String text, int messageId)
  {
    this.text = text;
    this.messageId = messageId;
  }

  /**
   * the text of the message
   *
   * @return the text of the message
   */
  public String getText()
  {
    return this.text;
  }

  /**
   * returns the id of this message
   *
   * @return id of the message
   */
  public int getMessageId()
  {
    return this.messageId;
  }

  /**
   * returns String interpretation of this message
   *
   * @return String  interpretation of this message
   */
  public String toString()
  {
    return this.text;
  }

  /**
   * equals method
   *
   * @param obj given object
   * @return if  given object equals returns true , if not false
   */
  @Override public boolean equals(Object obj)
  {
    if (!(obj instanceof Message))
      return false;
    Message newMessage = (Message) obj;
    return newMessage.getMessageId() == this.messageId;
  }
}

package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A class representing DateTime when the message was sent
 *
 * @author Group 2
 * @version 1.1
 */
public class DateTime
{
  private Date date;

  /**
   * Zero argument constructor which Saves current date to the date instance of this class.
   */
  public DateTime()
  {
    date = Calendar.getInstance().getTime();// store current time
  }

  /**
   * @return the string interpretation of a time stamp of instance date
   */
  public String getTimestamp()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return sdf.format(date);
  }

  /**
   * @return the String interpretation of a date stamp of instance date
   */
  public String getSortableDate()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(date);
  }
}
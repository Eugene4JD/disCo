package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime
{
  private Date date;

  public DateTime()
  {
    date = Calendar.getInstance().getTime();// store current time
  }

  public String getTimestamp()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return sdf.format(date);
  }

  public String getSortableDate()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(date);
  }
}
package utility;

import java.beans.PropertyChangeListener;

public interface ultimateUnnamedPropertyChangeSubject extends PropertyChangeListener
{
  void addListener(PropertyChangeListener listener);
  void removeListener(PropertyChangeListener listener);
}

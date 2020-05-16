package utility;

import java.beans.PropertyChangeListener;

public interface NamedPropertyChangeSubject
{
  void addListener(String propertyName, PropertyChangeListener listener);
  void removeListener(String propertyName, PropertyChangeListener listener);
}
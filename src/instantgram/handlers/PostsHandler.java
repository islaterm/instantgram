package instantgram.handlers;

import instantgram.IGUser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Ignacio Slater Muñoz
 * @since 0.1b4
 */
public class PostsHandler implements PropertyChangeListener {

  private IGUser subscriber;

  public PostsHandler(final IGUser igUser) {
    subscriber = igUser;
  }

  /**
   * This method gets called when a bound property is changed.
   *
   * @param evt
   *     A PropertyChangeEvent object describing the event source
   *     and the property that has changed.
   */
  @Override
  public void propertyChange(final PropertyChangeEvent evt) {
    subscriber.addToFeed((String) evt.getNewValue());
  }
}

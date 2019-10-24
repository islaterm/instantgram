package instantgram.handlers;

import instantgram.IGUser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for the new followers of an Instantgram user.
 *
 * @author Ignacio Slater Muñoz
 * @version 0.1b4
 * @since 0.1
 */
public class FollowsHandler implements PropertyChangeListener {

  private final IGUser subscriber;

  /**
   * Creates a new handler for the new followers of a user
   *
   * @param subscriber
   *     The object to be notified of the change.
   */
  public FollowsHandler(final IGUser subscriber) {
    this.subscriber = subscriber;
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
    subscriber.addToFeed(evt.getNewValue() + " is now following you!");
  }
}

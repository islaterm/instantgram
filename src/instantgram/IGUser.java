package instantgram;

import instantgram.handlers.FollowsHandler;
import instantgram.handlers.PostsHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a user of the new revolutionary social network Instantgram.
 * <p>
 * Users can:
 * - Follow other users
 * - Unfollow users
 * - Make a post
 * <p>
 * Whenever a user post content it is notified to all of it's followers.
 *
 * @author Ignacio Slater Muñoz
 * @version 1.0rc1
 * @since 0.1
 */
public class IGUser {

  private List<IGUser> followers = new ArrayList<>();
  private String name;
  private String feed = "";

  private PropertyChangeSupport
      followNotification = new PropertyChangeSupport(this),
      postNotification = new PropertyChangeSupport(this);

  /**
   * Creates a new user without followers.
   *
   * @param name
   *     the name of the user
   */
  public IGUser(final String name) {
    this.name = name;
    final FollowsHandler followsHandler = new FollowsHandler(this);
    followNotification.addPropertyChangeListener(followsHandler);
  }

  /**
   * Subscribes to another user to be notified of it's posts.
   * The other user is notified of his new follower.
   *
   * @param igUser
   *     the user to follow
   */
  public void follow(final IGUser igUser) {
    igUser.followers.add(this);

    igUser.postNotification.addPropertyChangeListener(new PostsHandler(this));
    igUser.followNotification
        .firePropertyChange(new PropertyChangeEvent(this, "New follower", feed, this.name));
  }

  /**
   * @return the user's name
   */
  public String getName() {
    return name;
  }

  // region : getters/setters

  /**
   * Posts a new message to be shared to this user's followers.
   *
   * @param message
   *     the text to be shared
   */
  public void post(final String message) {
    postNotification.firePropertyChange(new PropertyChangeEvent(this, "New post", "", message));
  }

  /**
   * @return a list with the followers of this user
   */
  public List<IGUser> getFollowers() {
    return List.copyOf(followers);
  }

  /**
   * @return a string with all the notifications this user has received
   */
  public String getFeed() {
    return feed;
  }

  /**
   * Adds content to this users feed.
   *
   * @param message
   *     the information that will be added to the feed
   */
  public void addToFeed(final String message) {
    feed += message + System.lineSeparator();
  }
  // endregion
}

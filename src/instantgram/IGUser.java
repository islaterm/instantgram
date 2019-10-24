package instantgram;

import instantgram.handlers.followsHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a user of the revolutionary social network Instantgram.
 * <p>
 * Users can:
 * - Follow other users
 * - Unfollow users
 * - Post pictures
 * - Post stories
 * - Livestream
 * <p>
 * Whenever a user post content it is notified to all of it's followers.
 *
 * @author Ignacio Slater Muñoz
 * @version 0.1b3
 * @since 0.1
 */
public class IGUser {

  private List<IGUser> followers = new ArrayList<>();
  private String name;
  private String feed = "";

  private PropertyChangeSupport newFollowerNotification = new PropertyChangeSupport(this);

  /**
   * Creates a new user without followers.
   *
   * @param name
   *     the name of the user
   */
  public IGUser(final String name) {
    this.name = name;
    final followsHandler followsHandler = new followsHandler(this);
    newFollowerNotification.addPropertyChangeListener(followsHandler);
  }

  /**
   * @return a list with the followers of this user
   */
  public List<IGUser> getFollowers() {
    return List.copyOf(followers);
  }

  /**
   * Subscribes to another user to be notified of it's posts.
   * The other user is notified of his new follower.
   *
   * @param igUser
   *     the user to follow
   */
  public void follow(final IGUser igUser) {
    igUser.addFollower(this);
  }

  private void addFollower(final IGUser igUser) {
    followers.add(igUser);
    newFollowerNotification
        .firePropertyChange(new PropertyChangeEvent(this, "New follower", feed, igUser.getName()));
  }

  /**
   * @return the user's name
   */
  public String getName() {
    return name;
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
}

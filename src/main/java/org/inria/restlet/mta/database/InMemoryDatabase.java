package org.inria.restlet.mta.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;

/**
 *
 * In-memory database
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class InMemoryDatabase
{

    /** User count (next id to give).*/
    private int userCount_;
    
    private int tweetCount_;
    
    /** User Hashmap. */
    Map<Integer, User> users_;
    Map<Integer,Tweet> tweets_;

    public InMemoryDatabase()
    {
        users_ = new HashMap<Integer, User>();
    }

    /**
     *
     * Synchronized user creation.
     * @param name
     * @param age
     *
     * @return the user created
     */
    public synchronized User createUser(String name, int age)
    {
        User user = new User(name, age);
        user.setId(userCount_);
        users_.put(userCount_, user);
        userCount_ ++;
        return user;
    }
    
    public synchronized Tweet createTweet(String contenu)
    {
        Tweet tweet = new Tweet(contenu);
        tweet.setId_tweet(tweetCount_);
        tweets_.put(tweetCount_, tweet);
        tweetCount_ ++;
        return tweet;
    }
    
    public Collection<User> getUsers()
    {
        return users_.values();
    }

    public User getUser(int id)
    {
        return users_.get(id);
    }
    
    public Tweet getTweet(int id)
    {
        return tweets_.get(id);
    }
    
    public Collection<Tweet> getTweets()
    {
        return tweets_.values();
    }

}

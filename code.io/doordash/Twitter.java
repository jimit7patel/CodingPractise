package doordash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/*
* Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
*/
public class Twitter {
    record Tweet(int tweetId, long time){}
    long tweetTime = 0;
    HashMap<Integer, Set<Integer>> followerDb;
    HashMap<Integer, List<Tweet>> tweetDb;

    public Twitter() {
        followerDb = new HashMap<>();
        tweetDb = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        List<Tweet> list = tweetDb.getOrDefault(userId,new ArrayList<>());
        list.add(new Tweet(tweetId,tweetTime++));
        tweetDb.put(userId,list);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();
        PriorityQueue<Tweet> pq = new PriorityQueue<>(Comparator.comparing(Tweet::time).reversed());
        if(tweetDb.containsKey(userId)) {
            for (Tweet tid : tweetDb.get(userId)) {
                pq.add(tid);
            }
        }
        if(followerDb.containsKey(userId)){
            for(Integer u: followerDb.get(userId)) {
                for (Tweet tid : tweetDb.getOrDefault(u,new ArrayList<>())) {
                    pq.add(tid);
                }
            }
        }
        int size = 10;
        while( !pq.isEmpty() && size-- >0){
            feed.add(pq.remove().tweetId);
        }
        return feed;

    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> list = followerDb.getOrDefault(followerId, new HashSet<>());
        list.add(followeeId);
        followerDb.put(followerId, list);
    }

    public void unfollow(int followerId, int followeeId) {
        if(followerDb.containsKey(followerId)){
            if(followerDb.get(followerId).contains(followeeId)){
                followerDb.get(followerId).remove(followeeId);
            }
        }
    }

    public static void main(String[] args){

        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        twitter.follow(1,2);
        twitter.follow(2,1);
        twitter.getNewsFeed(2);
        twitter.postTweet(2,6);
        twitter.getNewsFeed(1);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

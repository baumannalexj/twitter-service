package com.baumannalexj.service;

import com.baumannalexj.exception.DuplicateTweetException;
import com.baumannalexj.exception.GenericTwitterException;
import com.baumannalexj.exception.TwitterAccountNotFoundException;
import com.baumannalexj.dao.TwitterAccountDao;
import com.baumannalexj.model.TwitterAccount;
import com.baumannalexj.model.request.PostStatusRequest;
import com.baumannalexj.model.response.HomeTimelineResponse;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class TwitterService {
    private static final String ERROR_STATUS_IS_DUPLICATE = "Status is a duplicate.";

    @CreateSqlObject
    abstract TwitterAccountDao twitterAccountDao();

    protected Twitter getTwitterClient(String twitterOauthToken, String twitterOauthSecret) {
        Twitter twitterClient = TwitterFactory.getSingleton();
        twitterClient.setOAuthAccessToken(new AccessToken(twitterOauthToken, twitterOauthSecret));
        return twitterClient;
    }

    public Optional<TwitterAccount> getTwitterAccountById(long rowId) {
        return Optional.ofNullable(twitterAccountDao().getTwitterAccountById(rowId));
    }

    public HomeTimelineResponse getHomeTimelineById(long rowId) throws TwitterAccountNotFoundException, GenericTwitterException {
        Optional<TwitterAccount> twitterAccount = getTwitterAccountById(rowId);
        if (!twitterAccount.isPresent()) {
            throw new TwitterAccountNotFoundException("Account id:" + rowId + " not found.");
        }

        List<HomeTimelineResponse.TimelineTweet> timelineTweets = new ArrayList<>();
        Twitter twitterClient = getTwitterClient(twitterAccount.get().getOauthToken(), twitterAccount.get().getOauthSecret());
        try {
            List<Status> homeTimelineStatuses = twitterClient.getHomeTimeline();
            timelineTweets = getTimelineTweetsFromStatus(homeTimelineStatuses);

        } catch (TwitterException e) {
            throw new GenericTwitterException(e.getErrorCode(), e.getErrorMessage());
        }

        HomeTimelineResponse homeTimelineResponse = new HomeTimelineResponse();
        homeTimelineResponse.setTimelineTweets(timelineTweets);
        return homeTimelineResponse;
    }

    protected List<HomeTimelineResponse.TimelineTweet> getTimelineTweetsFromStatus(List<Status> homeTimelineTweets) {
        List<HomeTimelineResponse.TimelineTweet> timelineTweets = new ArrayList<>();
        for (Status status : homeTimelineTweets) {
            HomeTimelineResponse.TimelineTweet timelineTweet = new HomeTimelineResponse.TimelineTweet();
            timelineTweet.setScreenName(status.getUser().getScreenName());
            timelineTweet.setText(status.getText());
            timelineTweet.setCreatedAtUnixTime(status.getCreatedAt().getTime());
            timelineTweet.setProfileImageUrl(status.getUser().getBiggerProfileImageURLHttps());

            timelineTweets.add(timelineTweet);
        }

        return timelineTweets;
    }

    public Status postStatus(Long id, PostStatusRequest postStatusRequest) throws TwitterAccountNotFoundException, DuplicateTweetException, GenericTwitterException {
        Optional<TwitterAccount> twitterAccount = getTwitterAccountById(id);
        if (!twitterAccount.isPresent()) {
            throw new TwitterAccountNotFoundException("Account id:" + id + " not found.");
        }

        Twitter twitterClient = getTwitterClient(twitterAccount.get().getOauthToken(), twitterAccount.get().getOauthSecret());

        Status status = null;
        String tweetText = postStatusRequest.getText();
        try {
            status = twitterClient.updateStatus(tweetText);
        } catch (TwitterException e) {
            if (ERROR_STATUS_IS_DUPLICATE.equals(e.getErrorMessage())) {
                throw new DuplicateTweetException("Duplicate status: \"" + tweetText + "\"");
            }
            e.printStackTrace();

            throw new GenericTwitterException(e.getErrorCode(), e.getErrorMessage());
        }

        return status;
    }
}

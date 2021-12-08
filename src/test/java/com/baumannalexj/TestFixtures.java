package com.baumannalexj;

import com.baumannalexj.model.TwitterAccount;
import com.baumannalexj.model.response.HomeTimelineResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestFixtures {

    public static List<HomeTimelineResponse.TimelineTweet> getHomeTimelineResponseList() {

        List<HomeTimelineResponse.TimelineTweet> homeTimelineResponseList = new ArrayList<>();
        HomeTimelineResponse.TimelineTweet timelineTweet = new HomeTimelineResponse.TimelineTweet();
        timelineTweet.setText("test text");
        timelineTweet.setScreenName("test screenname");
        timelineTweet.setProfileImageUrl("test image url");
        timelineTweet.setCreatedAtUnixTime(new Date().getTime());

        homeTimelineResponseList.add(timelineTweet);
        return homeTimelineResponseList;
    }

    public static TwitterAccount getTwitterAccount() {
        TwitterAccount twitterAccount = new TwitterAccount();
        twitterAccount.setTwitterId(1L);
        twitterAccount.setOauthToken("oauthtoken");
        twitterAccount.setOauthSecret("oauthsecret");

        return twitterAccount;
    }
}

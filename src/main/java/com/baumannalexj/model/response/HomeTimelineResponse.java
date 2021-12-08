package com.baumannalexj.model.response;

import java.util.List;

public class HomeTimelineResponse {

    private List<TimelineTweet> timelineTweets;

    public List<TimelineTweet> getTimelineTweets() {
        return timelineTweets;
    }

    public void setTimelineTweets(List<TimelineTweet> timelineTweets) {
        this.timelineTweets = timelineTweets;
    }

    public static class TimelineTweet {
        private String screenName;
        private String text;
        private Long createdAtUnixTime;
        private String profileImageUrl;

        public String getScreenName() {
            return screenName;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Long getCreatedAtUnixTime() {
            return createdAtUnixTime;
        }

        public void setCreatedAtUnixTime(Long createdAtUnixTime) {
            this.createdAtUnixTime = createdAtUnixTime;
        }

        public String getProfileImageUrl() {
            return profileImageUrl;
        }

        public void setProfileImageUrl(String profileImage) {
            this.profileImageUrl = profileImage;
        }
    }
}

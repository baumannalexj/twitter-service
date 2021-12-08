package com.baumannalexj.controller;

import com.baumannalexj.TwitterServiceApplication;
import com.baumannalexj.AppConfiguration;
import com.baumannalexj.model.response.HomeTimelineResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.AdditionalMatchers;

import javax.ws.rs.client.Client;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TwitterResourceTest {

    @Rule
    public final DropwizardAppRule<AppConfiguration> RULE =
            new DropwizardAppRule<>(
                    TwitterServiceApplication.class, "config.yml");

    @Test
    @Ignore("Set the twitter user id in twitter_accounts.id ")
    public void whenAccountExists_thenReturnTimeline() {
        Client client = new JerseyClientBuilder().build();
        HomeTimelineResponse homeTimelineResponse = client.target(
                String.format("http://localhost:%d/twitter/accounts/%d/home-timeline", RULE.getLocalPort(), 1))
                .request()
                .get()
                .readEntity(HomeTimelineResponse.class);

        assertThat(homeTimelineResponse.getTimelineTweets().size(), is(AdditionalMatchers.gt(0)));

    }

//    @Test
//    public void whenAccountDoesNotExist_thenReturnsA404() {
//        Client client = new JerseyClientBuilder().build();
//        ErrorResponse errorResponse = client.target(
//                String.format("http://localhost:%d/twitter/accounts/%d/home-timeline", RULE.getLocalPort(), 9999))
//                .request()
//                .get().readEntity(ErrorResponse.class);
//
//        //TODO can't get exception mapper working
////        assertThat(errorResponse.getCode(), is(404));
//    }
}
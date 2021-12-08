package com.baumannalexj.service;

import com.baumannalexj.TestFixtures;
import com.baumannalexj.dao.TwitterAccountDao;
import com.baumannalexj.exception.DuplicateTweetException;
import com.baumannalexj.exception.GenericTwitterException;
import com.baumannalexj.exception.TwitterAccountNotFoundException;
import com.baumannalexj.model.TwitterAccount;
import com.baumannalexj.model.request.PostStatusRequest;
import com.baumannalexj.model.response.HomeTimelineResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


public class TwitterServiceTest {

    @Mock
    TwitterAccountDao mockTwitterAccountDao;
    @Mock
    Twitter mockTwitterClient;
    @Inject
    TwitterService twitterService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        twitterService = Mockito.spy(TwitterService.class);
        when(twitterService.twitterAccountDao()).thenReturn(mockTwitterAccountDao);
        doReturn(mockTwitterClient).when(twitterService).getTwitterClient(anyString(), anyString());
    }

    @Test
    @Ignore("TODO - fix npe")
    public void whenAccountExists_thenReturnHomeTimelineResponse() throws Exception {
        TwitterAccount twitterAccount = TestFixtures.getTwitterAccount();

        List<HomeTimelineResponse.TimelineTweet> responseList = TestFixtures.getHomeTimelineResponseList();
        when(mockTwitterAccountDao.getTwitterAccountById(eq(1L))).thenReturn(twitterAccount);

        doReturn(responseList).when(twitterService).getTimelineTweetsFromStatus(anyList());

        HomeTimelineResponse homeTimelineResponse = twitterService.getHomeTimelineById(1L);

        assertThat(homeTimelineResponse.getTimelineTweets().size(), is(1));
    }

    @Test(expected = TwitterAccountNotFoundException.class)
    public void whenNoAccountExists_thenReturnTwitterAccountNotFoundException() throws Exception {
        when(mockTwitterAccountDao.getTwitterAccountById(anyLong())).thenReturn(null);
        twitterService.getHomeTimelineById(-1);
    }

    @Test
    public void whenPostStatus_thenStatusIsCreated() throws DuplicateTweetException, TwitterAccountNotFoundException, TwitterException, GenericTwitterException {
        TwitterAccount twitterAccount = TestFixtures.getTwitterAccount();

        PostStatusRequest postStatusRequest = new PostStatusRequest();
        postStatusRequest.setText("test tweet");

        Status mockStatus = Mockito.mock(Status.class);
        when(mockStatus.getId()).thenReturn(999L);

        when(mockTwitterAccountDao.getTwitterAccountById(eq(twitterAccount.getTwitterId()))).thenReturn(twitterAccount);
        when(mockTwitterClient.updateStatus(eq(postStatusRequest.getText()))).thenReturn(mockStatus);

        Status actualStatus = twitterService.postStatus(twitterAccount.getTwitterId(), postStatusRequest);

        assertThat(actualStatus.getId(), is(999L));
    }
}
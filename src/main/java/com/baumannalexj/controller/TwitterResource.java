package com.baumannalexj.controller;

import com.baumannalexj.exception.DuplicateTweetException;
import com.baumannalexj.exception.GenericTwitterException;
import com.baumannalexj.exception.TwitterAccountNotFoundException;
import com.baumannalexj.model.request.PostStatusRequest;
import com.baumannalexj.model.response.HomeTimelineResponse;
import com.baumannalexj.service.TwitterService;
import twitter4j.Status;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/twitter")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TwitterResource {


    private TwitterService twitterService;

    public TwitterResource(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GET
    @Path("/accounts/{twitter_account.id}/home-timeline")
    public Response getTimelineByAccountId(@PathParam("twitter_account.id") long id) throws TwitterAccountNotFoundException, GenericTwitterException {
        HomeTimelineResponse homeTimelineResponse = twitterService.getHomeTimelineById(id);

        Response.ResponseBuilder responseBuilder = Response
                .ok(homeTimelineResponse);

        return responseBuilder.build();
    }

    @POST
    @Path("/accounts/{twitter_account.id}/tweet")
    public Response postStatus(@PathParam("twitter_account.id") Long id, PostStatusRequest postStatusRequest) throws TwitterAccountNotFoundException, DuplicateTweetException, GenericTwitterException {
        Status updatedStatus = twitterService.postStatus(id, postStatusRequest);

        Response.ResponseBuilder responseBuilder;
        responseBuilder = Response
                .status(Response.Status.CREATED);

        return responseBuilder.build();

    }
}

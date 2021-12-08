package com.baumannalexj;

import com.baumannalexj.controller.TwitterResource;
import com.baumannalexj.service.TwitterService;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class TwitterServiceApplication extends Application<AppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TwitterServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "Homework";
    }

    @Override
    public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
    }

    @Override
    public void run(final AppConfiguration configuration, final Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "sqlite");

        Twitter twitterClient = TwitterFactory.getSingleton();
        twitterClient.setOAuthConsumer(configuration.getTwitterConsumerKey(), configuration.getTwitterConsumerSecret());

        registerResources(environment, jdbi);
    }

    private void registerResources(final Environment environment, DBI jdbi) {
        environment.jersey().register(new TwitterResource(jdbi.onDemand(TwitterService.class)));
    }

}

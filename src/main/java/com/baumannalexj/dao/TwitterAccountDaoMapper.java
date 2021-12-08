package com.baumannalexj.dao;

import com.baumannalexj.model.TwitterAccount;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TwitterAccountDaoMapper implements ResultSetMapper<TwitterAccount> {

    private static final String TWITTER_ID = "twitter_id";
    private static final String OAUTH_TOKEN = "oauth_token";
    private static final String OAUTH_SECRET = "oauth_secret";


    @Override
    public TwitterAccount map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        TwitterAccount twitterAccount = new TwitterAccount();
        twitterAccount.setTwitterId(resultSet.getLong(TWITTER_ID));
        twitterAccount.setOauthSecret(resultSet.getString(OAUTH_SECRET));
        twitterAccount.setOauthToken(resultSet.getString(OAUTH_TOKEN));

        return twitterAccount;
    }

}

package com.baumannalexj.dao;

import com.baumannalexj.model.TwitterAccount;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(TwitterAccountDaoMapper.class)
public interface TwitterAccountDao {

    @SqlQuery("select * from twitter_accounts where id = :id")
    TwitterAccount getTwitterAccountById(@Bind("id") final long id);
}

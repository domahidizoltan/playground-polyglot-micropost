/*
 * This file is generated by jOOQ.
*/
package micropost.data;


import javax.annotation.Generated;

import micropost.data.tables.Micropost;
import micropost.data.tables.PostStatistics;
import micropost.data.tables.User;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index MICROPOST_ID = Indexes0.MICROPOST_ID;
    public static final Index POST_STATISTICS_ID = Indexes0.POST_STATISTICS_ID;
    public static final Index USER_EMAIL = Indexes0.USER_EMAIL;
    public static final Index USER_NICKNAME = Indexes0.USER_NICKNAME;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 extends AbstractKeys {
        public static Index MICROPOST_ID = createIndex("micropost_id", Micropost.MICROPOST, new OrderField[] { Micropost.MICROPOST.POST_ID }, true);
        public static Index POST_STATISTICS_ID = createIndex("post_statistics_id", PostStatistics.POST_STATISTICS, new OrderField[] { PostStatistics.POST_STATISTICS.ID }, true);
        public static Index USER_EMAIL = createIndex("user_email", User.USER, new OrderField[] { User.USER.EMAIL }, true);
        public static Index USER_NICKNAME = createIndex("user_nickname", User.USER, new OrderField[] { User.USER.NICKNAME }, true);
    }
}

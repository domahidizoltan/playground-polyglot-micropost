/*
 * This file is generated by jOOQ.
*/
package micropost.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import micropost.data.tables.Micropost;
import micropost.data.tables.PostStatistics;
import micropost.data.tables.User;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 528683583;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.micropost</code>.
     */
    public final Micropost MICROPOST = micropost.data.tables.Micropost.MICROPOST;

    /**
     * The table <code>public.post_statistics</code>.
     */
    public final PostStatistics POST_STATISTICS = micropost.data.tables.PostStatistics.POST_STATISTICS;

    /**
     * The table <code>public.user</code>.
     */
    public final User USER = micropost.data.tables.User.USER;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.MICROPOST_ID_SEQ,
            Sequences.POST_STATISTICS_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Micropost.MICROPOST,
            PostStatistics.POST_STATISTICS,
            User.USER);
    }
}
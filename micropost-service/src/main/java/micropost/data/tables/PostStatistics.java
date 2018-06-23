/*
 * This file is generated by jOOQ.
*/
package micropost.data.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import micropost.data.Indexes;
import micropost.data.Keys;
import micropost.data.Public;
import micropost.data.tables.records.PostStatisticsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class PostStatistics extends TableImpl<PostStatisticsRecord> {

    private static final long serialVersionUID = -1777045585;

    /**
     * The reference instance of <code>public.post_statistics</code>
     */
    public static final PostStatistics POST_STATISTICS = new PostStatistics();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PostStatisticsRecord> getRecordType() {
        return PostStatisticsRecord.class;
    }

    /**
     * The column <code>public.post_statistics.id</code>.
     */
    public final TableField<PostStatisticsRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('post_statistics_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.post_statistics.words</code>.
     */
    public final TableField<PostStatisticsRecord, Integer> WORDS = createField("words", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    public final TableField<PostStatisticsRecord, Object> WORD_COUNTS = createField("word_counts", org.jooq.impl.SQLDataType.OTHER.nullable(false), this, "");

    /**
     * The column <code>public.post_statistics.micropost_id</code>.
     */
    public final TableField<PostStatisticsRecord, Integer> MICROPOST_ID = createField("micropost_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>public.post_statistics</code> table reference
     */
    public PostStatistics() {
        this(DSL.name("post_statistics"), null);
    }

    /**
     * Create an aliased <code>public.post_statistics</code> table reference
     */
    public PostStatistics(String alias) {
        this(DSL.name(alias), POST_STATISTICS);
    }

    /**
     * Create an aliased <code>public.post_statistics</code> table reference
     */
    public PostStatistics(Name alias) {
        this(alias, POST_STATISTICS);
    }

    private PostStatistics(Name alias, Table<PostStatisticsRecord> aliased) {
        this(alias, aliased, null);
    }

    private PostStatistics(Name alias, Table<PostStatisticsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.POST_STATISTICS_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<PostStatisticsRecord, Integer> getIdentity() {
        return Keys.IDENTITY_POST_STATISTICS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PostStatisticsRecord> getPrimaryKey() {
        return Keys.POST_STATISTICS_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PostStatisticsRecord>> getKeys() {
        return Arrays.<UniqueKey<PostStatisticsRecord>>asList(Keys.POST_STATISTICS_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<PostStatisticsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PostStatisticsRecord, ?>>asList(Keys.POST_STATISTICS__POST_STATISTICS_MICROPOST_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatistics as(String alias) {
        return new PostStatistics(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatistics as(Name alias) {
        return new PostStatistics(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PostStatistics rename(String name) {
        return new PostStatistics(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PostStatistics rename(Name name) {
        return new PostStatistics(name, null);
    }
}

/*
 * This file is generated by jOOQ.
*/
package micropost.data.tables.records;


import javax.annotation.Generated;

import micropost.data.tables.PostStatistics;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class PostStatisticsRecord extends UpdatableRecordImpl<PostStatisticsRecord> implements Record4<Integer, Integer, Object, Integer> {

    private static final long serialVersionUID = -802172705;

    /**
     * Setter for <code>public.post_statistics.id</code>.
     */
    public PostStatisticsRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.post_statistics.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.post_statistics.words</code>.
     */
    public PostStatisticsRecord setWords(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.post_statistics.words</code>.
     */
    public Integer getWords() {
        return (Integer) get(1);
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    public PostStatisticsRecord setWordCounts(Object value) {
        set(2, value);
        return this;
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    public Object getWordCounts() {
        return get(2);
    }

    /**
     * Setter for <code>public.post_statistics.micropost_id</code>.
     */
    public PostStatisticsRecord setMicropostId(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.post_statistics.micropost_id</code>.
     */
    public Integer getMicropostId() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Integer, Object, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Integer, Object, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return PostStatistics.POST_STATISTICS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return PostStatistics.POST_STATISTICS.WORDS;
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    @Override
    public Field<Object> field3() {
        return PostStatistics.POST_STATISTICS.WORD_COUNTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return PostStatistics.POST_STATISTICS.MICROPOST_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getWords();
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    @Override
    public Object component3() {
        return getWordCounts();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getMicropostId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getWords();
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    @Override
    public Object value3() {
        return getWordCounts();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getMicropostId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord value2(Integer value) {
        setWords(value);
        return this;
    }

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.
     */
    @java.lang.Deprecated
    @Override
    public PostStatisticsRecord value3(Object value) {
        setWordCounts(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord value4(Integer value) {
        setMicropostId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord values(Integer value1, Integer value2, Object value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PostStatisticsRecord
     */
    public PostStatisticsRecord() {
        super(PostStatistics.POST_STATISTICS);
    }

    /**
     * Create a detached, initialised PostStatisticsRecord
     */
    public PostStatisticsRecord(Integer id, Integer words, Object wordCounts, Integer micropostId) {
        super(PostStatistics.POST_STATISTICS);

        set(0, id);
        set(1, words);
        set(2, wordCounts);
        set(3, micropostId);
    }
}
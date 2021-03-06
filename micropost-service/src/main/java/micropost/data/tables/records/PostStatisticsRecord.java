/*
 * This file is generated by jOOQ.
*/
package micropost.data.tables.records;


import javax.annotation.Generated;

import micropost.data.tables.PostStatistics;

import org.jooq.Field;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.TableRecordImpl;


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
public class PostStatisticsRecord extends TableRecordImpl<PostStatisticsRecord> implements Record5<Integer, Integer, Integer, Integer, String> {

    private static final long serialVersionUID = -2102424962;

    /**
     * Setter for <code>public.post_statistics.post_id</code>.
     */
    public PostStatisticsRecord setPostId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.post_statistics.post_id</code>.
     */
    public Integer getPostId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.post_statistics.total_words</code>.
     */
    public PostStatisticsRecord setTotalWords(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.post_statistics.total_words</code>.
     */
    public Integer getTotalWords() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.post_statistics.alphanumeric</code>.
     */
    public PostStatisticsRecord setAlphanumeric(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.post_statistics.alphanumeric</code>.
     */
    public Integer getAlphanumeric() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.post_statistics.non_alphanumeric</code>.
     */
    public PostStatisticsRecord setNonAlphanumeric(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.post_statistics.non_alphanumeric</code>.
     */
    public Integer getNonAlphanumeric() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.post_statistics.word_occurrence</code>.
     */
    public PostStatisticsRecord setWordOccurrence(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.post_statistics.word_occurrence</code>.
     */
    public String getWordOccurrence() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Integer, Integer, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Integer, Integer, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return PostStatistics.POST_STATISTICS.POST_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return PostStatistics.POST_STATISTICS.TOTAL_WORDS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return PostStatistics.POST_STATISTICS.ALPHANUMERIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return PostStatistics.POST_STATISTICS.NON_ALPHANUMERIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return PostStatistics.POST_STATISTICS.WORD_OCCURRENCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getPostId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getTotalWords();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getAlphanumeric();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getNonAlphanumeric();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getWordOccurrence();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getPostId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getTotalWords();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getAlphanumeric();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getNonAlphanumeric();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getWordOccurrence();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord value1(Integer value) {
        setPostId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord value2(Integer value) {
        setTotalWords(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord value3(Integer value) {
        setAlphanumeric(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord value4(Integer value) {
        setNonAlphanumeric(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord value5(String value) {
        setWordOccurrence(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostStatisticsRecord values(Integer value1, Integer value2, Integer value3, Integer value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
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
    public PostStatisticsRecord(Integer postId, Integer totalWords, Integer alphanumeric, Integer nonAlphanumeric, String wordOccurrence) {
        super(PostStatistics.POST_STATISTICS);

        set(0, postId);
        set(1, totalWords);
        set(2, alphanumeric);
        set(3, nonAlphanumeric);
        set(4, wordOccurrence);
    }
}

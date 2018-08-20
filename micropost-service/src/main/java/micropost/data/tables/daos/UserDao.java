/*
 * This file is generated by jOOQ.
*/
package micropost.data.tables.daos;


import java.util.List;

import javax.annotation.Generated;

import micropost.data.tables.User;
import micropost.data.tables.records.UserRecord;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class UserDao extends DAOImpl<UserRecord, micropost.data.tables.pojos.User, String> {

    /**
     * Create a new UserDao without any configuration
     */
    public UserDao() {
        super(User.USER, micropost.data.tables.pojos.User.class);
    }

    /**
     * Create a new UserDao with an attached configuration
     */
    public UserDao(Configuration configuration) {
        super(User.USER, micropost.data.tables.pojos.User.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(micropost.data.tables.pojos.User object) {
        return object.getNickname();
    }

    /**
     * Fetch records that have <code>nickname IN (values)</code>
     */
    public List<micropost.data.tables.pojos.User> fetchByNickname(String... values) {
        return fetch(User.USER.NICKNAME, values);
    }

    /**
     * Fetch a unique record that has <code>nickname = value</code>
     */
    public micropost.data.tables.pojos.User fetchOneByNickname(String value) {
        return fetchOne(User.USER.NICKNAME, value);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<micropost.data.tables.pojos.User> fetchByEmail(String... values) {
        return fetch(User.USER.EMAIL, values);
    }

    /**
     * Fetch a unique record that has <code>email = value</code>
     */
    public micropost.data.tables.pojos.User fetchOneByEmail(String value) {
        return fetchOne(User.USER.EMAIL, value);
    }

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    public List<micropost.data.tables.pojos.User> fetchByFirstName(String... values) {
        return fetch(User.USER.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    public List<micropost.data.tables.pojos.User> fetchByLastName(String... values) {
        return fetch(User.USER.LAST_NAME, values);
    }
}

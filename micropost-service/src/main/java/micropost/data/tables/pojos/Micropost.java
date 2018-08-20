/*
 * This file is generated by jOOQ.
*/
package micropost.data.tables.pojos;


import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.annotation.Generated;


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
public class Micropost implements Serializable {

    private static final long serialVersionUID = -1509770576;

    private final Integer        postId;
    private final String         content;
    private final OffsetDateTime createdAt;
    private final String         userNickname;

    public Micropost(Micropost value) {
        this.postId = value.postId;
        this.content = value.content;
        this.createdAt = value.createdAt;
        this.userNickname = value.userNickname;
    }

    public Micropost(
        Integer        postId,
        String         content,
        OffsetDateTime createdAt,
        String         userNickname
    ) {
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
        this.userNickname = userNickname;
    }

    public Integer getPostId() {
        return this.postId;
    }

    public String getContent() {
        return this.content;
    }

    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    public String getUserNickname() {
        return this.userNickname;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Micropost (");

        sb.append(postId);
        sb.append(", ").append(content);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(userNickname);

        sb.append(")");
        return sb.toString();
    }
}

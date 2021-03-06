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

    private static final long serialVersionUID = 968204922;

    private final Integer        postId;
    private final String         userNickname;
    private final String         content;
    private final OffsetDateTime createdAt;

    public Micropost(Micropost value) {
        this.postId = value.postId;
        this.userNickname = value.userNickname;
        this.content = value.content;
        this.createdAt = value.createdAt;
    }

    public Micropost(
        Integer        postId,
        String         userNickname,
        String         content,
        OffsetDateTime createdAt
    ) {
        this.postId = postId;
        this.userNickname = userNickname;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Integer getPostId() {
        return this.postId;
    }

    public String getUserNickname() {
        return this.userNickname;
    }

    public String getContent() {
        return this.content;
    }

    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Micropost (");

        sb.append(postId);
        sb.append(", ").append(userNickname);
        sb.append(", ").append(content);
        sb.append(", ").append(createdAt);

        sb.append(")");
        return sb.toString();
    }
}

-- Adminer 4.6.2 PostgreSQL dump

DROP TABLE IF EXISTS "micropost";
DROP SEQUENCE IF EXISTS micropost_id_seq;
CREATE SEQUENCE micropost_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

DROP TABLE IF EXISTS "user";
CREATE TABLE "public"."user" (
    "nickname" character varying(32) NOT NULL,
    "email" character varying(256) NOT NULL,
    "first_name" character varying(256),
    "last_name" character varying(256),
    CONSTRAINT "user_email" UNIQUE ("email"),
    CONSTRAINT "user_nickname" PRIMARY KEY ("nickname")
) WITH (oids = false);

DROP TABLE IF EXISTS "micropost";
CREATE TABLE "public"."micropost" (
    "post_id" integer DEFAULT nextval('micropost_id_seq') NOT NULL,
    "content" text NOT NULL,
    "created_at" timestamp with time zone NOT NULL,
    "user_nickname" character varying(32) NOT NULL,
    CONSTRAINT "micropost_id" PRIMARY KEY ("post_id"),
    CONSTRAINT "micropost_user_nickname_fkey" FOREIGN KEY (user_nickname) REFERENCES "user"(nickname) ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);


DROP TABLE IF EXISTS "post_statistics";
DROP SEQUENCE IF EXISTS post_statistics_id_seq;
CREATE SEQUENCE post_statistics_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."post_statistics" (
    "id" integer DEFAULT nextval('post_statistics_id_seq') NOT NULL,
    "words" integer NOT NULL,
    "word_counts" json NOT NULL,
    "post_id" integer NOT NULL,
    CONSTRAINT "post_statistics_id" PRIMARY KEY ("id"),
    CONSTRAINT "post_statistics_post_id_fkey" FOREIGN KEY (post_id) REFERENCES micropost(post_id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);

-- 2018-06-23 06:53:55.806767+00

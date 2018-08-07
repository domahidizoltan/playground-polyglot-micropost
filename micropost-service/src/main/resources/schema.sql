-- Adminer 4.6.2 PostgreSQL dump

DROP TABLE IF EXISTS "micropost";
DROP SEQUENCE IF EXISTS micropost_id_seq;
CREATE SEQUENCE micropost_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

DROP TABLE IF EXISTS "user";
CREATE TABLE "public"."user" (
    "id" character varying(32) NOT NULL,
    "email" character varying(256) NOT NULL,
    "first_name" character varying(256),
    "last_name" character varying(256),
    CONSTRAINT "user_email" UNIQUE ("email"),
    CONSTRAINT "user_id" PRIMARY KEY ("id")
) WITH (oids = false);


CREATE TABLE "public"."micropost" (
    "id" integer DEFAULT nextval('micropost_id_seq') NOT NULL,
    "content" text NOT NULL,
    "created_at" timestamp with time zone NOT NULL,
    "user_id" character varying(32) NOT NULL,
    CONSTRAINT "micropost_id" PRIMARY KEY ("id"),
    CONSTRAINT "micropost_user_id_fkey" FOREIGN KEY (user_id) REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);


DROP TABLE IF EXISTS "post_statistics";
DROP SEQUENCE IF EXISTS post_statistics_id_seq;
CREATE SEQUENCE post_statistics_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."post_statistics" (
    "id" integer DEFAULT nextval('post_statistics_id_seq') NOT NULL,
    "words" integer NOT NULL,
    "word_counts" json NOT NULL,
    "micropost_id" integer NOT NULL,
    CONSTRAINT "post_statistics_id" PRIMARY KEY ("id"),
    CONSTRAINT "post_statistics_micropost_id_fkey" FOREIGN KEY (micropost_id) REFERENCES micropost(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);

-- 2018-06-23 06:53:55.806767+00
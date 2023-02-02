CREATE TABLE IF NOT EXISTS app.votes
(
    id bigint NOT NULL,
    about character varying(255) COLLATE pg_catalog."default",
    date timestamp without time zone,
    email character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT votes_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS app.genres
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT genres_pkey PRIMARY KEY (id)
)
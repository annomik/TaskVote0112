CREATE TABLE IF NOT EXISTS app.artists
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT artists_pkey PRIMARY KEY (id)
)
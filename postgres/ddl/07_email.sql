CREATE TABLE IF NOT EXISTS app.email
(
    id bigint NOT NULL,
    massage character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT votes_pkey PRIMARY KEY (id)
)

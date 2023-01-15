CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.artists
(
    id bigserial,
    name text NOT NULL,
    CONSTRAINT artists_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS app.artists
(
    id bigserial,
    name text NOT NULL,
    CONSTRAINT artists_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app.votes
(
    id bigserial,
    about text NOT NULL,
    email text NOT NULL,
    date timestamp without time zone NOT NULL,
    CONSTRAINT votes_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app.vote_artist
(
    artist_id bigint,
    vote_id bigint,
    CONSTRAINT vote_artist_vote_id_key UNIQUE (vote_id),
    CONSTRAINT vote_artist_artist_id_fkey FOREIGN KEY (artist_id)
        REFERENCES app.artists (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_artist_vote_id_fkey FOREIGN KEY (vote_id)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS app.vote_genres
(
    genre_id bigint,
    vote_id bigint,
    CONSTRAINT vote_genres_genre_id_vote_id_key UNIQUE (genre_id,vote_id)
        INCLUDE(vote_id),
    CONSTRAINT vote_genres_genre_id_fkey FOREIGN KEY (genre_id)
        REFERENCES app.genres (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT vote_genres_vote_id_fkey FOREIGN KEY (vote_id)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


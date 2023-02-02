CREATE TABLE IF NOT EXISTS app.vote_genre
(
    vote_id bigint NOT NULL,
    genre_id bigint NOT NULL,
    CONSTRAINT fk8sjif531qts8wgx0gtx3p9pg8 FOREIGN KEY (vote_id)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk9qb4ivb0o7pmnxfleayckyt1l FOREIGN KEY (genre_id)
        REFERENCES app.genres (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
CREATE TABLE IF NOT EXISTS app.vote_singer
(
    singer_id bigint,
    vote_id bigint NOT NULL,
    CONSTRAINT vote_singer_pkey PRIMARY KEY (vote_id),
    CONSTRAINT fkan1gpte6p9vud32yr83nc9bno FOREIGN KEY (vote_id)
        REFERENCES app.votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkd4wcc8vr1y1js9unuqg2qmqy9 FOREIGN KEY (singer_id)
        REFERENCES app.artists (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

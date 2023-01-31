CREATE TABLE IF NOT EXISTS app.mail
(
    id bigserial,
    message text NOT NULL,
    email text NOT NULL,
    createVote timestamp without time zone NOT NULL,
    statusSend boolean NOT NULL,
    statusEmail boolean NOT NULL,
    lastSend timestamp without time zone NOT NULL,
    CONSTRAINT mail_pkey PRIMARY KEY (id)
)

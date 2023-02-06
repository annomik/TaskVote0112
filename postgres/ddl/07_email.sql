    CREATE TABLE IF NOT EXISTS app.email
    (
        id bigint NOT NULL,
        email character varying(255),
        lastsendtime bigint,
        massage character varying(255),
        sendmassage boolean,
        validateemail boolean,
        CONSTRAINT email_pkey PRIMARY KEY (id)
  )
CREATE TABLE users
(
    id         BIGINT PRIMARY KEY,
    username   VARCHAR(50) UNIQUE       NOT NULL,
    password   TEXT                     NOT NULL,
    first_name TEXT                     NOT NULL,
    last_name  TEXT                     NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    unlocked   BOOLEAN                  NOT NULL,
    enabled    BOOLEAN                  NOT NULL
);

CREATE TABLE authorities
(
    id        BIGINT PRIMARY KEY,
    user_id   BIGINT      NOT NULL REFERENCES users (id),
    authority VARCHAR(50) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS users_id_seq INCREMENT BY 10;
CREATE SEQUENCE IF NOT EXISTS authorities_id_seq INCREMENT BY 10;

CREATE UNIQUE INDEX ON users (username);

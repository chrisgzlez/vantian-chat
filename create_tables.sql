-- User Table

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR (50) PRIMARY KEY  NOT NULL,
    password VARCHAR (120) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE TYPE status AS ENUM ('Active', 'Inactive', 'Pending');

CREATE TABLE IF NOT EXISTS requests (
    user_requester VARCHAR (50) REFERENCES users(username),
    user_accepter VARCHAR (50) REFERENCES users(username),
    request_status status NOT NULL, 
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    PRIMARY KEY (user_requester, user_accepter)
);

-- Indexes for performance
CREATE INDEX users_created_at on users (created_at);
CREATE INDEX users_updated_at on users (created_at);

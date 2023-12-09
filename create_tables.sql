-- User Table

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR (50) PRIMARY KEY  NOT NULL,
    password VARCHAR (120) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);




-- Indexes for performance
CREATE INDEX users_created_at on users (created_at);
CREATE INDEX users_updated_at on users (created_at);

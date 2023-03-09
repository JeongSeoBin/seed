DROP TABLE IF EXISTS tm_user;
CREATE TABLE tm_user (
    user_id varchar not null,
    password varchar not null,
    user_name varchar not null,
    user_state char not null
);

DROP TABLE IF EXISTS tm_user_role;
CREATE TABLE tm_user_role (
    user_id varchar not null,
    role_id varchar not null
);

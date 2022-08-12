INSERT INTO users (id, username, password, first_name, last_name, created_at, unlocked, enabled)
VALUES (nextval('users_id_seq'), 'alex@alexgutjahr.com', '$2a$12$yRyRslUX7PO3h8h8Jjl5IeCXe3NC.iEFr1V66ZW.nVz/FAWRda/C6',
        'Alex',
        'Gutjahr', current_timestamp, true, true);

INSERT INTO authorities (id, user_id, authority)
VALUES (nextval('authorities_id_seq'), (SELECT id FROM users WHERE username = 'alex@alexgutjahr.com'), 'ADMIN');

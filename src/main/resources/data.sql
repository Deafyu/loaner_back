INSERT INTO role_entity (id, name)
VALUES ('1', 'ROLE_USER'),
       ('2', 'ROLE_ADMIN'),
       ('3', 'ROLE_LENDER') ON CONFLICT (id) DO NOTHING;
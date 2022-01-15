INSERT INTO role_entity (id, name)
VALUES ('1', 'USER_ROLE'),
       ('2', 'ADMIN_ROLE'),
       ('3', 'LENDER_ROLE') ON CONFLICT (id) DO NOTHING;
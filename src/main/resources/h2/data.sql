INSERT INTO tm_user (
    user_id, password, user_name, user_state
) VALUES
('user01', '1234321', '유저01', 'A'),
('admin01', '1234321', '어드민01', 'A')
;

INSERT INTO tm_user_role (
    user_id, role_id
) VALUES
('user01', 'user'),
('admin01', 'admin')
;

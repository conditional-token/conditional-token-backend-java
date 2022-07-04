TRUNCATE TABLE balances, withdraws, deposits, users, social_logins, bookmakers, bookmakers_users, options,
matches, bets, user_grouping, grouping, grouping_permission, permission,
 oauth_client_details RESTART IDENTITY;

DELETE FROM balances;
DELETE FROM withdraws;
DELETE FROM deposits;
DELETE FROM users;
DELETE FROM social_logins;
DELETE FROM bookmakers;
DELETE FROM bookmakers_users;
DELETE FROM options;
DELETE FROM matches;
DELETE FROM bets;
DELETE FROM user_grouping;
DELETE FROM grouping;
DELETE FROM grouping_permission;
DELETE FROM permission;
DELETE FROM oauth_client_details;

ALTER sequence balances_balances_id_seq restart with 1;
ALTER sequence withdraws_withdraws_id_seq restart with 1;
ALTER sequence deposits_deposits_id_seq restart with 1;

ALTER sequence users_users_id_seq restart with 1;
ALTER sequence social_logins_social_logins_id_seq restart with 1;

ALTER sequence grouping_grouping_id_seq restart with 1;
ALTER sequence permission_permission_id_seq restart with 1;

ALTER sequence bookmakers_bookmakers_id_seq restart with 1;
ALTER sequence options_options_id_seq restart with 1;
ALTER sequence matches_matches_id_seq restart with 1;
ALTER sequence bets_bets_id_seq restart with 1;

INSERT INTO users (users_email, users_encrypted_password, users_name, users_photo_url, balances_id, users_gov_number, users_birthday) VALUES
('tiago.luks@gmail.com','$2a$12$Bcjm9WHjcSbrmxFyzimdO.zvMLRbsyggwBEc3uKX1rGWrLb3qn/Ru', 'Tiago Clementino', 'https://lh3.googleusercontent.com/a-/AOh14GjMBCYzy5VXeufBlMLb1184n-iAEJoWf0uLoaP_=s96-c',1, '00000000011', '1984-07-26');
INSERT INTO social_logins (social_logins_media, social_logins_hash, social_logins_email, social_logins_name, social_logins_photo_url, users_id) VALUES
(0, 'pLp78JUyfSM36YHswtU6wSgVUfe2', 'tiago.luks@gmail.com','Tiago Clementino', 'https://lh3.googleusercontent.com/a-/AOh14GjMBCYzy5VXeufBlMLb1184n-iAEJoWf0uLoaP_=s96-c',1);







INSERT INTO permission (permission_name, permission_description) VALUES ('CONSULT_USERS_GROUPS_PERMISSIONS', 'Allows consult users, groups or permissions');
INSERT INTO permission (permission_name, permission_description) VALUES ('EDIT_USERS_GROUPS_PERMISSIONS', 'Allows create or edit users, groups or permissions');

INSERT INTO grouping (grouping_name) VALUES ('Dealer'), ('Agent'), ('Better'), ('Bookmaker');

INSERT INTO grouping_permission (grouping_id, grouping_permission_id) VALUES (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 

INSERT INTO user_grouping (users_id, grouping_id) VALUES (1, 1), (1, 2);

INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'back-web', null, '$2y$12$w3igMjsfS5XoAYuowoH3C.54vRFWlcXSHLjX7MwF990Kc2KKKh72e',
  'READ,WRITE', 'password,refresh_token', null, null,
  60 * 60 * 6, 60 * 24 * 60 * 60, null
);

INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'backanalytics', null, '$2y$12$fahbH37S2pyk1RPuIHKP.earzFmgAJJGo26rE.59vf4wwiiTKHnzO',
  'READ,WRITE', 'authorization_code', 'http://localhost:8003', null,
  null, null, false
);

INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'faturamento', null, '$2y$12$fHixriC7yXX/i1/CmpnGH.RFyK/l5YapLCFOEbIktONjE8ZDykSnu',
  'READ,WRITE', 'client_credentials', null, 'CONSULT_BETS,GENERATE_REPORTS',
  null, null, false
);
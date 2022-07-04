CREATE TABLE IF NOT EXISTS grouping (
	grouping_id SERIAL CONSTRAINT pk_grouping PRIMARY KEY,
	grouping_name varchar(60) NOT NULL	
);

CREATE TABLE IF NOT EXISTS grouping_permission (
	grouping_id integer NOT NULL,
	grouping_permission_id integer NOT NULL,
	
	PRIMARY KEY	(grouping_id, grouping_permission_id)
);

CREATE TABLE IF NOT EXISTS permission (
	permission_id SERIAL CONSTRAINT pk_permission PRIMARY KEY,
	permission_name varchar(100) NOT NULL,
	permission_description varchar(60) NOT NULL	
);

CREATE TABLE IF NOT EXISTS user_grouping (
	users_id integer NOT NULL,
	grouping_id integer NOT NULL,
	
	PRIMARY KEY	(users_id, grouping_id)
);


ALTER TABLE grouping_permission add constraint fk_grouping_permission_permission
foreign key (grouping_permission_id) references permission (permission_id);

ALTER TABLE grouping_permission add constraint fk_grouping_permission_grouping
foreign key (grouping_id) references grouping (grouping_id);

ALTER TABLE user_grouping add constraint fk_user_grouping_grouping
foreign key (grouping_id) references grouping (grouping_id);

ALTER TABLE user_grouping add constraint fk_user_grouping_users
foreign key (users_id) references users (users_id);
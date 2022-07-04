ALTER TABLE grouping 
ADD	permission_id integer,
ADD constraint fk_grouping
foreign key (permission_id) references permission (id);


DELETE FROM grouping_permission;

DROP TABLE grouping_permission;

DELETE FROM user_grouping;

DROP TABLE user_grouping;

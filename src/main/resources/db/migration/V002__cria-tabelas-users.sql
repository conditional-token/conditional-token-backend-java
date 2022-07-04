CREATE TABLE IF NOT EXISTS users (
    users_id SERIAL CONSTRAINT pk_id_users PRIMARY KEY,
    users_email varchar(50) NOT NULL,
    users_encrypted_password varchar(700) not null,
    users_status integer NOT NULL default 1, -- 0 inative, 1 active, -1 removed
    users_name varchar(150) NOT NULL,
    users_gov_number varchar(50) NOT NULL,
    users_description varchar(1000),        
    users_photo_url varchar(200),             
    users_birthday date NOT NULL,  
    users_created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,   
    balances_id integer REFERENCES balances ON DELETE SET NULL,  
    UNIQUE(users_email)
);

CREATE TABLE IF NOT EXISTS social_logins (
    social_logins_id SERIAL CONSTRAINT pk_id_social_logins PRIMARY KEY,
    social_logins_media integer NOT NULL default 0, -- 0 Google, 1 Facebook, 2 Apple
    social_logins_hash varchar(700) NOT NULL, -- uuid
    social_logins_email varchar(50) NOT NULL,
    social_logins_status integer NOT NULL default 1, -- 0 inative, 1 active, -1 removed
    social_logins_name varchar(150),
    social_logins_photo_url varchar(200),
    users_id integer,    
    UNIQUE(social_logins_email,social_logins_media), 
    
    CONSTRAINT fk_social_logins_users
      FOREIGN KEY(users_id) 
	  REFERENCES users(users_id) ON DELETE SET NULL
);
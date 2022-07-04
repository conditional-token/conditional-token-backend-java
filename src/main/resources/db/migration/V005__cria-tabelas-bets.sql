CREATE TABLE IF NOT EXISTS bookmakers (
    bookmakers_id SERIAL CONSTRAINT pk_id_bookmakers PRIMARY KEY,
    bookmakers_name varchar(150),
    bookmakers_description varchar(1000),
    bookmakers_created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,      
    bookmakers_status integer NOT NULL default 1, -- 0 inative, 1 active, -1 removed
    bookmakers_classification integer NOT NULL default 0, -- 0 unprofessional, 1 professional, 2 professional and sponsored, 3 us
    balances_id integer, -- REFERENCES balances,
    
    CONSTRAINT fk_bookmakers_balances
      FOREIGN KEY(balances_id) 
	  REFERENCES balances(balances_id)  ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS bookmakers_users (
    users_id integer REFERENCES users ON DELETE CASCADE,
    bookmakers_id integer REFERENCES bookmakers ON DELETE CASCADE,    
    PRIMARY KEY(users_id,bookmakers_id)
);

CREATE TABLE IF NOT EXISTS matches (
    matches_id SERIAL CONSTRAINT pk_id_matches PRIMARY KEY,
    matches_title varchar(150) NOT NULL,
    matches_description varchar(1000),
    matches_created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,   
    matches_visibility integer NOT NULL default 1, -- 0 public but not listed, 1 public
    matches_expired_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,   
    bookmakers_id integer, -- REFERENCES bookmakers ON DELETE CASCADE, 
    matches_insurance_amount integer NOT NULL, -- cents 
    
    CONSTRAINT fk_matches_bookmakers
      FOREIGN KEY(bookmakers_id) 
	  REFERENCES bookmakers(bookmakers_id) ON DELETE SET NULL
    
);

CREATE TABLE IF NOT EXISTS options (
    options_id SERIAL CONSTRAINT pk_id_options PRIMARY KEY,
    options_title varchar(150) NOT NULL,
    options_description varchar(1000),
    options_created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,   
    options_odd real NOT NULL default 1.0, -- award = bet amount * options_odd   
    matches_id integer, 
    
    CONSTRAINT fk_options_matches
      FOREIGN KEY(matches_id) 
	  REFERENCES matches(matches_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS bets (
    bets_id SERIAL CONSTRAINT pk_id_bets PRIMARY KEY,
    bets_short_description varchar(150),
    bets_hash varchar(1000) NOT NULL,
    bets_odd real NOT NULL default 1.0, -- award = bet amount * bets_odd   
    bets_created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,   
    bets_status integer NOT NULL default 0, -- 0 open, 1 win, 2 lose, 3 win and paid
    bets_amount integer NOT NULL, -- cents
    
    deposits_id integer REFERENCES deposits ON DELETE SET NULL,     
    withdraws_id integer REFERENCES withdraws ON DELETE SET NULL,   
    options_id integer, -- REFERENCES options ON DELETE SET NULL, 
    
    CONSTRAINT fk_bets_options
      FOREIGN KEY(options_id) 
	  REFERENCES options(options_id) ON DELETE SET NULL
);
CREATE TABLE IF NOT EXISTS balances (
    balances_id SERIAL CONSTRAINT pk_id_balances PRIMARY KEY,
    balances_amount integer NOT NULL -- cents
);

CREATE TABLE IF NOT EXISTS withdraws (
    withdraws_id SERIAL CONSTRAINT pk_id_withdraws PRIMARY KEY,
    withdraws_amount integer NOT NULL, -- cents
    balances_id integer REFERENCES balances ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS deposits (
    deposits_id SERIAL CONSTRAINT pk_id_deposits PRIMARY KEY,
    deposits_amount integer NOT NULL, -- cents
    balances_id integer REFERENCES balances ON DELETE SET NULL
);
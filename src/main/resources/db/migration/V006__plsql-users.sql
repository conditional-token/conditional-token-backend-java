CREATE OR REPLACE FUNCTION create_first_balance()
RETURNS TRIGGER
LANGUAGE PLPGSQL
AS 
$$
DECLARE
bls_id int;
BEGIN
  with rows as (
    INSERT INTO balances(balances_amount) VALUES(0) returning balances_id
  )
  select balances_id into bls_id from rows;
  NEW.balances_id := bls_id;
  RETURN NEW;
END;
$$;


CREATE TRIGGER create_user
BEFORE INSERT
ON users
FOR EACH ROW
EXECUTE PROCEDURE create_first_balance();
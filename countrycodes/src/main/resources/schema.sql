CREATE TABLE country_phone_code
(
    country_name VARCHAR_IGNORECASE(128) NOT NULL ,
    country_code VARCHAR(2) NOT NULL ,
    phone_code   VARCHAR(64) NOT NULL
);
CREATE INDEX country_name_idx ON country_phone_code (country_name);

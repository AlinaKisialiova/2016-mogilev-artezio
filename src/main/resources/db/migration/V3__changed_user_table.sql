-- Alter tables
-- Table: user
ALTER TABLE "user" ADD COLUMN gender VARCHAR (10) NOT NULL;
ALTER TABLE "user" ADD COLUMN birth_date TIMESTAMP;

INSERT INTO "user" (login, email, password, gender, birth_date)
  VALUES ('user', 'user@test.com', '$2a$10$bliv0GnZxtwCELrGYlzV2Oo7BjwCrKrUL2HB9FR7gvAGmIreTdM7O', 'male', now());
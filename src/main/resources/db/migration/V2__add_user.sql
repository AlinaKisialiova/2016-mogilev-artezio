INSERT INTO "user" (login, email, password)
  values('user', 'user@test.com', '$2a$10$bliv0GnZxtwCELrGYlzV2Oo7BjwCrKrUL2HB9FR7gvAGmIreTdM7O');

-- Indexes
-- User indexes
CREATE INDEX User_idx_1 on "user" (id);
CREATE INDEX User_idx_2 on "user" (login);
CREATE INDEX User_idx_3 on "user" (email);

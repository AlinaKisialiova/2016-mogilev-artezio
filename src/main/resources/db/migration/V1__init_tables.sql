-- Sequences
-- Sequence: user_id_seq
CREATE SEQUENCE user_id_seq
  START WITH 1
  MINVALUE 1
  INCREMENT BY 1;

-- Sequence: advice_id_seq
CREATE SEQUENCE advice_id_seq
  START WITH 1
  MINVALUE 1
  INCREMENT BY 1;

-- Sequence: list_id_seq
CREATE SEQUENCE list_id_seq
  START WITH 1
  MINVALUE 1
  INCREMENT BY 1;

-- Sequence: list_item_id_seq
CREATE SEQUENCE list_item_id_seq
  START WITH 1
  MINVALUE 1
  INCREMENT BY 1;

-- Sequence: user_advice_id_seq
CREATE SEQUENCE user_advice_id_seq
  START WITH 1
  MINVALUE 1
  INCREMENT BY 1;

-- Tables
-- Table: persistent_logins
CREATE TABLE "user" (
  id              BIGINT       NOT NULL DEFAULT nextval('user_id_seq'),
  login           VARCHAR(255) NOT NULL,
  email           VARCHAR(255) NOT NULL,
  password        VARCHAR(255) NOT NULL,
  mood            FLOAT8       NOT NULL DEFAULT 0,
  last_event_date TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT User_pk PRIMARY KEY (id),
  CONSTRAINT User_login_ak UNIQUE (login),
  CONSTRAINT User_email_ak UNIQUE (email)
);

-- Table: persistent_logins
CREATE TABLE persistent_logins (
  series    VARCHAR(64)                 NOT NULL PRIMARY KEY,
  username  VARCHAR(255)                NOT NULL,
  token     VARCHAR(64)                 NOT NULL,
  last_used TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- Table: advice
CREATE TABLE advice (
  id          BIGINT        NOT NULL DEFAULT nextval('advice_id_seq'),
  description VARCHAR(2048) NOT NULL,
  emotion     VARCHAR(8)    NOT NULL,
  CONSTRAINT Advice_pk PRIMARY KEY (id)
);

-- Table: advice_list
CREATE TABLE advice_list (
  id              BIGINT      NOT NULL DEFAULT nextval('list_id_seq'),
  user_id         BIGINT      NOT NULL,
  create_date     TIMESTAMP   NOT NULL,
  end_date        TIMESTAMP   NULL,
  current_emotion VARCHAR(64) NULL,
  photo           BYTEA       NOT NULL,
  CONSTRAINT Advice_List_pk PRIMARY KEY (id)
);

-- Tables: list_item
CREATE TABLE list_item (
  id             BIGINT    NOT NULL DEFAULT nextval('list_item_id_seq'),
  list_id        BIGINT    NOT NULL,
  user_advice_id BIGINT    NOT NULL,
  end_date       TIMESTAMP NULL,
  complete       BOOLEAN   NOT NULL DEFAULT FALSE,
  CONSTRAINT List_item_pk PRIMARY KEY (id)
);


-- Tables: user_advice
CREATE TABLE user_advice (
  id        BIGINT NOT NULL DEFAULT nextval('user_advice_id_seq'),
  advice_id BIGINT NOT NULL,
  weight    INT    NOT NULL,
  user_id   BIGINT NOT NULL,
  CONSTRAINT User_advice_pk PRIMARY KEY (id)
);

-- Foreign keys
-- Reference:  AdviceList_User (table: advice_list)
ALTER TABLE advice_list ADD CONSTRAINT AdviceList_User
FOREIGN KEY (user_id)
REFERENCES "user" (id)
ON DELETE  RESTRICT
ON UPDATE  RESTRICT;

-- Reference:  User_Advice_Advice (table: user_advice)
ALTER TABLE user_advice ADD CONSTRAINT User_Advice_Advice
FOREIGN KEY (advice_id)
REFERENCES advice (id)
ON DELETE  RESTRICT
ON UPDATE  RESTRICT;

-- Reference:  User_Advice_User (table: advice_user)
ALTER TABLE user_advice ADD CONSTRAINT User_Advice_User
FOREIGN KEY (user_id)
REFERENCES "user" (id)
ON DELETE  RESTRICT
ON UPDATE  RESTRICT;

-- Reference:  List_Item_Advice_List (table: list_item)

ALTER TABLE list_item ADD CONSTRAINT List_Item_Advice_List
FOREIGN KEY (list_id)
REFERENCES advice_list (id)
ON DELETE  RESTRICT
ON UPDATE  RESTRICT;

-- Reference:  List_Item_Advice_User (table: list_item)

ALTER TABLE list_item ADD CONSTRAINT List_Item_Advice_User
FOREIGN KEY (user_advice_id)
REFERENCES user_advice (id)
ON DELETE  RESTRICT
ON UPDATE  RESTRICT;
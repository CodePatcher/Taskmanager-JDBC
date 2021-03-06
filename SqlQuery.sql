CREATE TABLE IF NOT EXISTS  users (id SERIAL PRIMARY KEY NOT NULL,first_name CHAR (100) NOT NULL,last_name CHAR (100) NOT NULL,user_name CHAR (100) NOT NULL,active_user BOOLEAN NOT NULL,team_id INT);

CREATE TABLE IF NOT EXISTS team (id SERIAL PRIMARY KEY NOT NULL,team_name CHAR (100) NOT NULL,active_team BOOLEAN NOT NULL);

CREATE TYPE STATUS AS ENUM('Started','Unstarted','Done','Archive');

CREATE TABLE IF NOT EXISTS work_item (id SERIAL PRIMARY KEY NOT NULL,title CHAR(100) NOT NULL,description CHAR (200),current_status STATUS NOT NULL,user_id INT);

CREATE TABLE IF NOT EXISTS issue (id SERIAL PRIMARY KEY NOT NULL,title CHAR(100) NOT NULL,description CHAR (100) NOT NULL,open_issue BOOLEAN NOT NULL,work_item_id INT NOT NULL );

ALTER TABLE users ADD FOREIGN KEY (team_id) REFERENCES team(id);
ALTER TABLE issue ADD FOREIGN KEY (work_item_id) REFERENCES work_item (id);
ALTER TABLE work_item ADD FOREIGN KEY (user_id) REFERENCES users(id);
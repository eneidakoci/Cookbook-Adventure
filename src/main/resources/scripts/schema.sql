create DATABASE cookbook_adventure;
USE cookbook_adventure;
CREATE TABLE profile(
                        profile_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        date_created DATE,
                        last_modified DATE,
                        deleted BIT(1),
                        profile_link VARCHAR(255)
);
CREATE TABLE user(
                     user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     role VARCHAR(20) NOT NULL,
                     username VARCHAR(20) UNIQUE NOT NULL,
                     password VARCHAR(255) NOT NULL
);
CREATE TABLE member(
                       member_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(20) NOT NULL,
                       last_name VARCHAR(20) NOT NULL,
                       date_created DATE,
                       last_modified DATE,
                       deleted BIT(1),
                       profile_id INT,
                       user_id INT,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES user (user_id),
                       CONSTRAINT `profile_id_fk` FOREIGN KEY(`profile_id`) REFERENCES `profile`(`profile_id`)
);

CREATE TABLE category(
                         category_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         category_name VARCHAR(20) NOT NULL
);
CREATE TABLE recipe(
                       recipe_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       recipe_name VARCHAR(255) NOT NULL,
                       ingredients VARCHAR(255) NOT NULL,
                       date_published DATE NOT NULL,
                       member_id INT,
                       category_id INT,
                       description TEXT,
                       likes INT,
                       CONSTRAINT `member_id_fk` FOREIGN KEY(`member_id`) REFERENCES `member`(`member_id`), -- nqs nuk funksionon delete, mund te duhet ON DELETE CASCADE
                       CONSTRAINT `category_id_fk` FOREIGN KEY(`category_id`) REFERENCES `category`(`category_id`)
);
CREATE TABLE comment(
                        comment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        comment_text TEXT,
                        member_id INT,
                        recipe_id INT,
                        date_published DATE,
                        CONSTRAINT `member_id_comment_fk` FOREIGN KEY(`member_id`) REFERENCES `member`(`member_id`), -- nqs nuk funksionon delete, mund te duhet ON DELETE CASCADE
                        CONSTRAINT `recipe_id_fk` FOREIGN KEY(`recipe_id`) REFERENCES `recipe`(`recipe_id`)
);
CREATE TABLE rating(
                       rating_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       member_id INT,
                       recipe_id INT,
                       rate INT,
                       CONSTRAINT `member_id_rating_fk` FOREIGN KEY(`member_id`) REFERENCES `member`(`member_id`), -- nqs nuk funksionon delete, mund te duhet ON DELETE CASCADE
                       CONSTRAINT `recipe_id_rating_fk` FOREIGN KEY(`recipe_id`) REFERENCES `recipe`(`recipe_id`),
                       CONSTRAINT check_rate_range CHECK (rate >= 1 AND rate <= 5) -- ose rregulloje nga back end
);
CREATE TABLE recipe_category (
                                 recipe_id INT,
                                 category_id INT,
                                 PRIMARY KEY (recipe_id, category_id),
                                 FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id),
                                 FOREIGN KEY (category_id) REFERENCES category (category_id)
);
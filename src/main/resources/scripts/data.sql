-- INSERTS
INSERT INTO profile (date_created, last_modified, deleted, profile_link)
VALUES ('2023-10-20', '2023-10-20', 0, 'profile_link_1'),
       ('2023-10-21', '2023-10-21', 0, 'profile_link_2'),
       ('2023-10-22', '2023-10-22', 0, 'profile_link_3'),
       ('2023-10-23', '2023-10-23', 0, 'profile_link_4'),
       ('2023-10-24', '2023-10-24', 0, 'profile_link_5'),
       ('2023-10-25', '2023-10-25', 0, 'profile_link_6'),
       ('2023-10-26', '2023-10-26', 0, 'profile_link_7'),
       ('2023-10-27', '2023-10-27', 0, 'profile_link_8'),
       ('2023-10-28', '2023-10-28', 0, 'profile_link_9'),
       ('2023-10-29', '2023-10-29', 0, 'profile_link_10');

INSERT INTO user (role, username, password)
VALUES ('Admin', 'admin1', 'admin_password1'),
       ('User', 'user1', 'user_password1'),
       ('User', 'user2', 'user_password2'),
       ('User', 'user3', 'user_password3'),
       ('User', 'user4', 'user_password4'),
       ('User', 'user5', 'user_password5'),
       ('User', 'user6', 'user_password6'),
       ('User', 'user7', 'user_password7'),
       ('User', 'user8', 'user_password8'),
       ('User', 'user9', 'user_password9');

INSERT INTO member (name, last_name, date_created, last_modified, deleted, profile_id, user_id, email)
VALUES ('John', 'Doe', '2023-10-20', '2023-10-20', 0, 1, 1, 'john@example.com'),
       ('Alice', 'Smith', '2023-10-21', '2023-10-21', 0, 2, 2, 'alice@example.com'),
       ('Bob', 'Johnson', '2023-10-22', '2023-10-22', 0, 3, 3, 'bob@example.com'),
       ('Eva', 'Brown', '2023-10-23', '2023-10-23', 0, 4, 4, 'eva@example.com'),
       ('Mike', 'White', '2023-10-24', '2023-10-24', 0, 5, 5, 'mike@example.com'),
       ('Laura', 'Davis', '2023-10-25', '2023-10-25', 0, 6, 6, 'laura@example.com'),
       ('Tom', 'Wilson', '2023-10-26', '2023-10-26', 0, 7, 7, 'tom@example.com'),
       ('Sara', 'Lee', '2023-10-27', '2023-10-27', 0, 8, 8, 'sara@example.com'),
       ('Chris', 'Turner', '2023-10-28', '2023-10-28', 0, 9, 9, 'chris@example.com'),
       ('Emily', 'Clark', '2023-10-29', '2023-10-29', 0, 10, 10, 'emily@example.com');
INSERT INTO category (category_name)
VALUES ('Breakfast'),
       ('Lunch'),
       ('Dinner'),
       ('Dessert'),
       ('Vegetarian'),
       ('Vegan'),
       ('Gluten-Free'),
       ('Low-Carb'),
       ('Seafood'),
       ('Italian');

INSERT INTO recipe (recipe_name, ingredients, date_published, member_id, category_id, description, likes)
VALUES
    ('Pancakes', 'Flour, milk, eggs', '2023-10-20', 1, 1, 'Delicious homemade pancakes.', 100),
    ('Sandwich', 'Bread, cheese, ham', '2023-10-21', 2, 2, 'A classic sandwich for lunch.', 75),
    ('Spaghetti', 'Pasta, tomato sauce, meatballs', '2023-10-22', 3, 3, 'Italian spaghetti dish.', 120),
    ('Chocolate Cake', 'Chocolate, flour, sugar', '2023-10-23', 4, 4, 'Rich and moist chocolate cake.', 90),
    ('Salad', 'Lettuce, tomatoes, dressing', '2023-10-24', 5, 5, 'Healthy and fresh salad.', 80),
    ('Vegan Soup', 'Vegetables, vegetable broth', '2023-10-25', 6, 6, 'A delicious vegan soup.', 110),
    ('Gluten-Free Pizza', 'Gluten-free crust, toppings', '2023-10-26', 7, 7, 'Pizza for gluten-free diets.', 95),
    ('Low-Carb Wrap', 'Lettuce wrap, lean protein', '2023-10-27', 8, 8, 'Low-carb wrap with lean protein.', 70),
    ('Grilled Salmon', 'Salmon fillet, herbs', '2023-10-28', 9, 9, 'Grilled salmon with herbs.', 130),
    ('Lasagna', 'Pasta, cheese, meat sauce', '2023-10-29', 10, 10, 'Classic Italian lasagna.', 105);


INSERT INTO comment (comment_text, member_id, recipe_id, date_published)
VALUES ('Great recipe!', 1, 1, '2023-10-20'),
       ('I love it!', 2, 2, '2023-10-21'),
       ('Delicious!', 3, 3, '2023-10-22'),
       ('Yummy!', 4, 4, '2023-10-23'),
       ('Healthy choice!', 5, 5, '2023-10-24'),
       ('Amazing!', 6, 6, '2023-10-25'),
       ('Tasty!', 7, 7, '2023-10-26'),
       ('Perfect!', 8, 8, '2023-10-27'),
       ('Superb!', 9, 9, '2023-10-28'),
       ('Excellent recipe!', 10, 10, '2023-10-29');

INSERT INTO rating (member_id, recipe_id, rate)
VALUES (1, 1, 5),
       (2, 2, 4),
       (3, 3, 5),
       (4, 4, 4),
       (5, 5, 5),
       (6, 6, 4),
       (7, 7, 5),
       (8, 8, 4),
       (9, 9, 5),
       (10, 10, 4);

INSERT INTO recipe_category (recipe_id, category_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);
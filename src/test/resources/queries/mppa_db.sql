SELECT *
FROM accounts;

SELECT *
FROM accounts
LIMIT 5;

SELECT *
FROM accounts
WHERE public_name = 'Shopping'
  AND version = 1;

SELECT *
FROM preferences
ORDER BY updated_at DESC;

SELECT account_id
FROM preferences
WHERE updated_at = '2023-08-19 22:39:29.311025 +00:00';

SELECT account_id
FROM preferences
WHERE value = 'gallery';

SELECT public_name, created_at
FROM accounts
WHERE status = 'INACTIVE'
ORDER BY created_at DESC;

SELECT *
FROM ingredients;

SELECT *
FROM ingredients i
         FULL JOIN shopping_items sh ON i.ingredient_id = sh.ingredient_id;

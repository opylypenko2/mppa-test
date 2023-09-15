select *
from accounts;

select *
from accounts
limit 5;

select *
from accounts
where public_name = 'Shopping'
  and version = 1;

select *
from preferences
order by updated_at desc;

select account_id
from preferences
where updated_at = '2023-08-19 22:39:29.311025 +00:00';

select account_id
from preferences
where value = 'gallery';

select public_name, created_at
from accounts
where status = 'INACTIVE'
order by created_at desc;

select *
from ingredients;

select *
from ingredients i
         full join shopping_items sh on i.ingredient_id = sh.ingredient_id;



-- Delete Tables
Drop TABLE _user;
Drop TABLE product;
Drop TABLE order_list;
-- View Tables
SELECT * FROM _user;
SELECT * FROM product;
SELECT * FROM order_list;

-- Custom commands
SELECT p FROM order_list p 
ORDER BY p.time_ordered DESC;

SELECT o FROM order_list o 
JOIN FETCH o.product p 
JOIN FETCH p.user u 
WHERE u.id = 1;
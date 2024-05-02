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

SELECT ol FROM order_list ol 
JOIN product p ON ol.product_id = p.id 
WHERE p.user_id = 2;


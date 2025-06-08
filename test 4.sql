SELECT d.* 
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
WHERE i.VIN = '1HGCM82633A004352';
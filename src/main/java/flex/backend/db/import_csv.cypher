// Create Cities
USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:World_Cities_Location_table.csv" AS row
CREATE (:City {name: row.City, country: row.Country, latitude: row.Latitude, longitude: row.Logitude, altitude: row.Altitude});

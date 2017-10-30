echo 'who am i'
mvn properties:read-project-properties -DENVIRONMENT=development glassfish:stop-domain

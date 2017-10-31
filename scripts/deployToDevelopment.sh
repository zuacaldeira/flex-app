echo 'mvn properties:read-project-properties -DENVIRONMENT=development glassfish:redeploy'
ENVIRONMENT = $1
mvn properties:read-project-properties -DENVIRONMENT=$ENVIRONMENT glassfish:redeploy

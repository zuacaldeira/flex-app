ENV=$1
echo 'Redeploying ' $ENV
mvn properties:read-project-properties -DENVIRONMENT=$ENV glassfish:redeploy

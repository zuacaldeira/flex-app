ENV=$1
echo 'Restarting domain ' $ENV
~/Servers/glassfish4-latest/glassfish/bin/asadmin restart-domain $ENV

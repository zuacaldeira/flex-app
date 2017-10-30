git add .
git commit -m "Enter release preparation"
git push origin dev

git checkout release
git pull --rebase
git stash
git merge dev
# TODO: unstash
# Continue...
mvn release:clean release:prepare -Prelease
git add .
git commit -m "Release prepared."
git push origin release

git checkout master
git pull --rebase
git stash
git merge release
git commit -m "Release to Production."
git push origin master
# TODO: unstash
# Continue...

git checkout dev
git pull --rebase
git stash
git merge release
git commit -m "Enter new development cycle."
git push origin dev
# TODO: unstash
# Continue...

/Users/zua/Servers/glassfish4-latest/glassfish/bin/asadmin restart-domain domain1
/Users/zua/Servers/glassfish4-latest/glassfish/bin/asadmin restart-domain production


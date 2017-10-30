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
git push origin master
# TODO: unstash
# Continue...

git checkout dev
git pull --rebase
git stash
git merge release
git push origin dev
# TODO: unstash
# Continue...

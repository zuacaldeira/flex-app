echo "RELEASE"

git checkout release
git pull --rebase
git merge dev
# TODO: unstash
# Continue...
mvn release:clean release:prepare -Prelease
git commit -m "Release prepared."

git checkout master
git pull --rebase
git merge release
git commit -m "Release to Production."
git push origin master
# TODO: unstash
# Continue...

git checkout dev
git pull --rebase
git merge release
git commit -m "Enter new development cycle."
git push origin dev
# TODO: unstash
# Continue...


echo "Prepare release"
git checkout release
git merge dev
mvn release:clean release:prepare -Prelease
git push origin release

echo "Update master"
git checkout master
git merge release
git push origin master

echo "Update dev"
git checkout dev
git merge release
git push origin dev

echo "Finished"

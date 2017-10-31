echo "Checkout release"
git checkout release

echo "Merge dev into release"
git merge dev

echo "Clean prepare release"
mvn release:clean release:prepare -Prelease

echo "Add and commit changes on release"
git add .
git commit -m "Release Preparation done"

echo "Push changes to origin/release"
git push origin release

echo "Checkout master"
git checkout master

echo "Merge release into master"
git merge release
git push origin master

echo "Add and commit changes on master"
git add .
git commit -m "Master update to latest release"

echo "Checkout dev"
git checkout dev

echo "Merge release into dev"
git merge release


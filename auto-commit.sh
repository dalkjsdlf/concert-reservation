#!/bin/bash
echo $1
if [ "$1" = "" ]; then
	echo "You should input a commit message"
	exit -1
fi

git status

git add .

git commit -m "$1"

git push origin main

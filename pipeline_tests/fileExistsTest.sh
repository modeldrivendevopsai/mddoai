#!/bin/sh

if ! [ -f ./test/generatedCode/.gitlab-ci.yml ]; then
	echo "File not generated"; 
	exit 1;
fi
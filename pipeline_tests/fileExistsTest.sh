#!/bin/sh

if ! [ -f ./main/test/generatedCode/.gitlab-ci.yml ]; then
    echo "File not generated"; 
    exit 1;
fi
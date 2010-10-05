#!/bin/bash

cd ./trafficExamples
for f in `ls *`
do
grep '<span>' $f > ../zoneList/$f
done

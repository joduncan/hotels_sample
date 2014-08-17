#!/bin/bash

#prep
rm -rf ./classes
mkdir ./classes
cd ./classes

# compile
groovyc ../src/com/joduncan/hotels/*.groovy

# more prep/packaging.
jar cvf ../hotels.jar *

cd ..

# run tests.
for i in tests/src/com/joduncan/hotels/*.groovy
do
  groovy -cp ./hotels.jar $i
done

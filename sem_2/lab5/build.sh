#!/bin/bash
mkdir -p bin
javac -encoding "UTF-8" -sourcepath src -d bin -classpath lib/* src/Main.java
# java -cp bin;lib/Pokemon.jar Main
#: for linux and ; for windows

mkdir -p build
#jar -cfm build/lab5.jar mf.txt -C bin .
jar -xvf lib/gson-2.8.2.jar bin
jar -cfe build/lab5.jar Main -C bin .
#если просто указать bin без C то в jar будет создан каталог bin
#эквивалентно
#cd bin
#jar -cfm ../build/lab2.jar ../mf.txt ./*
#cp -r lib/* build/
cp -r lib/* build/

java -jar build/lab5.jar

CC = javac
FILES = find ./src/*/*.java
TESTFILES = find ./test/*/*.java | find ./src/*/*.java

run:
	java -cp ./src/ Main
MUD:
	$(FILES) | xargs $(CC) ./src/Main.java
clear:
	find ./bin/*.class | xargs rm -f
	find ./src/*.class | xargs rm -f
	find ./bin/*/*.class | xargs rm -f
	find ./src/*/*.class | xargs rm -f
	find ./test/*/*.class | xargs rm -f
tests:
	$(TESTFILES) | xargs $(CC) -cp junit-4.*.jar
	# find ./test/creaturePackTest/*.class | java -cp ./test/creaturePackTest
.PHONY: tests
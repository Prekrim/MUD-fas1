CC = javac
FILES = find ./src/*/*.java
TESTFILES = find ./test/*/*.java

run: MUD
	java ./src/Main
MUD:
	$(FILES) | xargs $(CC) ./src/Main.java
	$(CC) ./src/Main.java
clear:
	find ./bin/*/*.class | xargs rm -f
	find ./src/*/*.class | xargs rm -f
	rm -f ./src/Main.class
tests: MUD
	$(TESTFILES) | xargs $(CC)
.PHONY: tests
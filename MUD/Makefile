CC = javac
FILES = find ./src/*/*.java
TESTFILES = find ./test/*/*.java

MUD:
	$(FILES) | xargs $(CC)
clear:
	find ./bin/*/*.class | xargs rm -f
tests: MUD
	$(TESTFILES) | xargs $(CC)
.PHONY: tests
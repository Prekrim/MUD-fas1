CC = javac
FILES = find ./src/*/*.java
TESTFILES = find ./src/*/*.java

run:
	java -cp ./src/ controller.Pong
pong:
	$(FILES) | xargs $(CC)
clear:
	find ./src/*.class | xargs rm -f
	find ./src/*/*.class | xargs rm -f
	find ./test/*/*.class | xargs rm -f
clearall: clear
	find ./bin/*/*.class | xargs rm -f
	find ./bin/*.class | xargs rm -f
tests:
	$(TESTFILES) | xargs $(CC) -cp junit-4.*.jar ./tests/modelTests/MyPongModelTest.java
	java -cp .:/*/share/java/junit.jar org.junit.runner.JUnitCore MyPongModelTest
.PHONY: tests
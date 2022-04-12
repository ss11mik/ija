# just for me

build:
	mvn compile package

run:
	mvn javafx:run

javadoc:
	rm -r doc/
	mvn javadoc:javadoc

clean:
	mvn clean

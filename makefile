COPT = -cp bin -d bin -Xlint:unchecked

default: compile
all: compile jar zip

compile:
	mkdir -p bin/util bin/mapmaker/mapcmd
	javac $(COPT) src/util/*.java src/mapmaker/mapcmd/*.java
	javac $(COPT) src/mapmaker/*.java
	javac $(COPT) src/*.java src/*/*.java
clean:
	rm -r bin
jar:
	cd bin && jar cvfe ../MapMaker.jar MapMaker *.class mapmaker/*.class mapmaker/mapcmd/*.class util/*.class
zip:
	tar cfz mapmaker.tgz MapMaker.jar doc maps src makefile gpl-3.0.txt readme.txt

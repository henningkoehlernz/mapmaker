COPT = -cp bin -d bin -Xlint:unchecked

default: compile
all: clean compile jar

compile:
	mkdir -p bin/util bin/mapmaker/mapcmd
	javac $(COPT) src/util/*.java src/mapmaker/mapcmd/*.java
	javac $(COPT) src/mapmaker/*.java
	javac $(COPT) src/*.java src/*/*.java
clean:
	rm -r bin
jar:
	cd bin && jar cvfe ../MapMaker.jar MapMaker *.class mapmaker/*.class mapmaker/mapcmd/*.class util/*.class
	jar uvf MapMaker.jar doc maps readme.txt

default: compile
all: clean compile jar

compile:
	mkdir -p bin/util bin/mapmaker/mapcmd
	javac -cp bin -d bin src/util/*.java src/mapmaker/mapcmd/*.java
	javac -cp bin -d bin src/mapmaker/*.java
	javac -cp bin -d bin src/*.java src/*/*.java
clean:
	rm -r bin
jar:
	cd bin && jar cvfe ../MapMaker.jar MapMaker *.class mapmaker/*.class mapmaker/mapcmd/*.class util/*.class
	jar uvf MapMaker.jar doc maps readme.txt

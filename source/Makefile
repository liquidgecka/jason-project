# Butchered from Amit's P6 Makefile =)

JFLAGS=

# Recognize files with .class and .java extensions
.SUFFIXES: .class .java

# This is a rule to convert a file with .java extension
# into a file with a .class extension. The macro $< just
# supplies the name of the file (without the extension) 
# that invoked this rule.

.java.class:
	javac -Xlint:deprecation $(JFLAGS) $<

# To satisfy the rule named compile, we must have the  following 
# class files (with date no later than the source .java files).
# We also must have satisfied the rule named doc.

all: compile 

game:
	make -C game

tictactoe:
	make -C tictactoe

gui:
	make -C gui

network:
	make -C network

compile: NetworkViewer.class  NetworkPlayer.class LocalPlayer.class \
         LocalFast.class
	make -C game
	make -C tictactoe
	make -C gui
	make -C network


runplayer: compile
	java -Xms100m -Xmx200m NetworkPlayer

runviewer: compile
	java -Xms100m -Xmx200m NetworkViewer

run: compile
	java -Xms100m -Xmx200m LocalPlayer

runfast:
	java -Xms100m -Xmx200m LocalFast

clean: 
	rm --force *.class */*.class


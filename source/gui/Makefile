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

gui: JLinePanel.class JTicTacToePanel.class JXorOPanel.class \
     TicTacToeGamePlayer.class JGameStateViewer.class TreeElement.class \
	 JTreePanel.class JTicTacToeAIViewer.class

compile: gui


clean: 
	rm --force *.class


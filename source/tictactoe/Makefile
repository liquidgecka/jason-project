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

tictactoe: TicTacToeAIListener.class TicTacToeAIPlayer.class \
           TicTacToeGameState.class TicTacToeHumanPlayer.class \
           TicTacToeMove.class

compile: tictactoe


clean: 
	rm --force *.class


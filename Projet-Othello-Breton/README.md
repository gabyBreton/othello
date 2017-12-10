# OTHELLO

## About the game
Othello, known as Reversi: "is a strategy board game for two players, 
played on an 8×8 uncheckered board. There are sixty-four identical game pieces 
called disks (often spelled "discs"), which are light on one side and dark on 
the other. Players take turns placing disks on the board with their assigned 
color facing up. During a play, any disks of the opponent's color that are in 
a straight line and bounded by the disk just placed and another disk of the 
current player's color are turned over to the current player's color." 
ref: [Wikipedia] 

## Implementations
The source code provides two views: console and JavaFX
The project implements the **Strategy** and **Observer** Design Patterns.
### View Console
This is a test view, all in console.
With this view, the user can use four commands to interact with the game: 
**play X Y** , **wall X Y**, **show** 
and **help** where X Y identifies the targeted position on the game board.
The console view does not provides the possibility to play against the computer.
### View FX
It is the GUI of the game. This view is made of three scenes and offers the 
possibility to play against the computer. Lets see what is inside it.
#### StartLayout
It is the entering door of the game, where the players can enter their 
pseudonymes and choose to play or not against the computer. This class use:
- **TextFieldLimited**: creates text fields for the pseudos that redefines 
- TextField class to create ones that have a maximal length, by default set on 
- 10. 

At this point, the user can choose if it want to play against the computer by 
selecting a the corresponding radio button.
The user is also obligated to enter a pseudo of at least one character, if not, 
an alert dialog window appears to explain it.

#### GameLayout
It the where to play.
This class use:
- A board created by the class **BoardPane**.
- Score informations such as score, pseudo and colored pawn, created by the 
class **ScoreInfos**.
- Game informations such as history table view (provides by 
**ActionHistoryTable**), progress indicator that shows the progression of the 
game, a progress bar that shows the proportion of black and white pawns on the 
board and a walls counter.
- Buttons to **pass** the current turn, **abandon** and **restart** (the 
restart button is still not effective as restarting but make an information 
dialog box to explain it).

### FinalLayout
This class shows **who wins** and provides a button to **quit** and **restart** 
(same problem as above for restart).

## Compilation and running
### Compilation
First you need to clone the project
```
$ git clone https://git.esi-bru.be/2017-18-DEVG3-C112/Projet-Othello-Breton.git
```
Then compile .java files
```
$ javac *.java -d ~/Projet-Othello-Breton/Projet-Othello-Breton/build/classes/
```
If you have troubles using javac, you may need read the Troubles section.
### Running
Setting the class path
```
$ CLASSPATH=~/Projet-Othello-Breton/Projet-Othello-Breton/build/classes/
$ export CLASSPATH
```
Running Fx view
```
$ java project.othello.breton.viewFx.MainFx
```
#### Troubles
To install jdk (contains java and javac) :
```
$ apt-get install openjdk-8-jdk
```
If you get in trouble as setting PATH or CLASSPATH, there is some 
documentation: [PATH and CLASSPATH], [CLASSPATH]

#### Credits

[wikipedia]:<https://en.wikipedia.org/wiki/Reversi>
[Dillinger]:<https://dillinger.io>
[PATH and CLASSPATH]:<https://docs.oracle.com/javase/tutorial/essential/environment/paths.html>
[CLASSPATH]:<http://docs.oracle.com/javase/6/docs/technotes/tools/solaris/classpath.html>

# OTHELLO

## About the game
Othello, known as Reversi, "is a strategy board game for two players, 
played on an 8×8 uncheckered board. There are sixty-four identical game pieces 
called disks (often spelled "discs"), which are light on one side and dark on 
the other. Players take turns placing disks on the board with their assigned 
color facing up. During a play, any disks of the opponent's color that are in 
a straight line and bounded by the disk just placed and another disk of the 
current player's color are turned over to the current player's color." ref:
[Wikipedia] 

## Implementations
The source code provides two views: console and JavaFX
### Console

### View FX
In this implementations there is three scene.

#### StartLayout
It is the entering door of the game, where the players can enter their pseudo 
and choose to play or not against the computer.

    - **TextFieldLimited** creates text fields for the pseudos that redefines 
    TextField to create ones that have a maximal length, by default set on 10.

#### GameLayout
It the where to play.
This class creates:
    - a board created by the class **BoardPane**.
    - score information such as score, pseudo and colored pawn, created by the 
class **ScoreInfos**.

## How to install

## 

[wikipedia]:<https://en.wikipedia.org/wiki/Reversi>

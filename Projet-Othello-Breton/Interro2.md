
# Interro 2
## Implementations
#### Counter for the takings:
When the black or the white player place a pawn, they takes at least one
pawn of the other player.
Now, two counters are displayed below the counter of walls, in the game info 
area.
They shows the total of takings for each players.
**New**:
* Each player have an attribute for its total taking, a setter and a getter for 
it.
* The class game info have a new gridpane where display the two counters of the 
total takings.
* The facade have two methods to get the total taking value of both players.

#### Delete a wall:
A player can now delete an existing wall by right-clicking on it. No matters
who had placed this wall.
When a player delete the wall, it's make the turn finishing and add a new 
action in the history table.
**New**:
* A method to decrement the counter of walls on the board.
* A method in OthelloImpl to verify if a cell is occupied by a wall.
* A method in OthelloImpl to delete a wall. This method delete the targeted wall 
and create a new action for the history table view.

#### Chart for scores evolution:
Now, players can load a line chart where are displayed the evolutions of both 
scores by clicking on the menu item 'Edit'.
**New**:
* A menu bar with a menu item 'Edit'.
* A new scene where is the line chart, loaded in GameLayout class by 'Edit' menu
 item.
* A new class ChartsScoresLayout where are implemented all the needed methods
to create and update the line chart.



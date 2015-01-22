# Maal Counter
Maal Counter is an android app that calculates and keeps score for card game called Marriage. Marriage is a three-pack Rummy game that is popular in Nepal.  The game requires at least two players and at most six players. Three standard 52-card packs are used without the printed jokers. Due to the number of players in the game and the number of cards used, the calculation of points can get complicated. For example, even though, a player completed a hand, he/she may not win the game as the total points are determined by the total "maal" all the players have. This app aims to solve this issue through an algorithm that calculates total points and determines a winner. The app has necessary user interface for users to easily keep track of their scores.

## The Card Game called Marriage
The main objective behind this game is to collect sets of same suit consecutive cards or different suit identical cards. A set is typically made up of 3 cards. A set of same suit consecutive cards is called a sequence, whereas, a set of different suit but identical cards is called tunnella. A dealer, who can be any player, deals cards to each player, one at a time. After each player receives 21 cards, the dealer puts the rest of the cards face down in a stack. The game then begins. Each player in turn may take either a top card from the stack or the card discarded by the previous player (on the very first turn, the first player may take the card turned up by the dealer). The player must then discard one card face up on the discard pile. Players are only allowed to pick up the latest discard. 

Any player who has 3 sets of sequence or tunnella needs to show it to everybody in the game and can now choose a card at random from the stack of cards without showing anybody else. This card is called tiplu and put at the end of the stack of cards. Tiplu determines all the jokers for the current hand and carries point value. Therefore, the first objective of any player is to get three eligible sets to determine tiplu before anybody else sees it and gain an additional advantage in the game, because players who have not seen tiplu might discard it. If players do not have three sets, then they will have to keep waiting for their turn to change a card from the stack or discard pile. Later, every other player who can show three eligible sets is then allowed to look at the tiplu on the bottom of the stack, and therefore, find out which cards are jokers. 

The card immediately above the tiplu is called poplu and that immediately below the tiplu is called jhiplu. For example, if the tiplu is ♣J then:
* the other two ♣J are tiplu;
* the three ♣Q are poplu;
* the three ♣10 are jhiplu;
* there are 12 ordinary jokers: all the  ♠J, ♦J and ♥J.

Any of the cards above can be a joker. A pure sequence of three jokers (jhiplu-tiplu-poplu: ♣10-♣J-♣Q) is called a marriage, and is the most valuable combination in the game.
The different types of three-card combination that can be collected are as follows (in the examples we continue to assume that the  5 is the tiplu):
* A tunnella consists of three identical cards - for example ♥6-♥6-♥6.
* A pure sequence consists of three consecutive cards of the same suit - for example ♠A-♠2-♠3.
* A dirty sequence consists of two cards of the same suit and a joker representing a third card of that suit to form a consecutive sequence - for example ♦6-♣6-♦8, or any three cards two of which are jokers.
* A triplet consists of three cards of the same rank, but all of different suits - for example ♠K-♣K-♥K is a triplet. ♠9-♠9-♣9 is not a valid combination - the suits must all be different.
* A dirty triplet consists of two cards of the same rank and different suits plus a joker - for example ♦5-♣2-♥2 - or  any three cards two of which are jokers.
Additional information
* In a sequence an ace can be at either end (Q-K-A or A-2-3) but not in the middle (K-A-2 is not a sequence).
* Two identical cards plus a joker, such as ♦10-♦10-♦5, is not a valid combination: a joker cannot be used to make a tunnella, and it's not a triplet because it has two cards of the same suit.
* Any player who is dealt a tunnella (three identical cards) can expose these cards immediately, and they may then be worth points at the end of the game. A tunnella that is not exposed at the start, because the owner either acquired it later or chose not to expose it, has no point value.

Apart from tunnellas at the start of the game and combinations needed to look at the tiplu, completed combinations are not laid down during a hand but kept with the player until the hand ends.

## Algorithm
A game has multiple hands. When a hand ends, players count their points. Points are scored for 'maal' (any tiplu, poplu or jhiplu in the player's final hand) and for any tunnellas laid down immediately after the deal. 
Below is the table of maal points that is primarily loaded by the app. However, these points can be modified in the app.
<table style="width:80%" border="1">
  <tr>
    <td></td>
    <td>ordinary card</td>		
    <td>ordinary joker</td>
    <td>poplu or jhiplu</td>
    <td>tiplu</td>
    <td>marriage</td>
  </tr>
  <tr>
    <td>single</td>
    <td>-</td>		
    <td>-</td>
    <td>2</td>
    <td>3</td>
    <td>10</td>
  </tr>
  <tr>
    <td>double</td>
    <td>-</td>		
    <td>-</td>
    <td>5</td>
    <td>7</td>
    <td>30</td>
  </tr>
<tr>
    <td>triple</td>
    <td>-</td>		
    <td>-</td>
    <td>10</td>
    <td>-</td>
    <td>-</td>
  </tr>
<tr>
    <td>tunnella</td>
    <td>5</td>		
    <td>10</td>
    <td>20</td>
    <td>-</td>
    <td>-</td>
  </tr>
</table>
Each card can only be counted towards one item in this table - for example, a marriage is worth 10 points instead of the 2+2+3 points for the individual cards in it.

For convenience we call the player who ended a hand the winner, even though this player may make a net loss if the other players have many "maals." 

If a player did not finish the hand, then they need to pay the winner some points based on whether this player has seen the joker. The points are calculated as below:

* If the player has seen the joker but didn't win, points to be paid to the winner: 3
* If the player hasn't seen the joker, points to be paid to the winner: 10

For convenience, let these points be called game score or g.

Each player pays each other player the difference between the number of points they scored for cards and the total points in the pot. Let the total number of players be n and the score of an individual player be s. Let P be the total maal points of all players in a hand. 

`Let x1, x2, x3 and x4 be four players playing a hand of marriage. Let x4 be the winner of this hand. Let x1's maal points be s1 and game score be g1. Let x2's maal points be s2 and game score be g2 and x3's maal points be s3 and game score be g3. x4's maal points will be s4 but won't have any game score because x4 is the winner.`

 `x1 needs to get s1 points from other 3 players but need to pay game score, g1, to x4 for not being able to win and pay all the other players as well.`

`total points for x1 (p1) = 3* s1 - g1 –( s2 + s3 + s4 )……(i)`
`Similarly, total points for x2 (p2) = 3* s2 – g2 –( s1 + s3 + s4 )….(ii) and `
`total points for x3 (p3) = 3* s3 – g3 –( s2 + s3 + s1 )….(iii).`

`Finally total points for the winner x4 = 3* s4 + g1 + g2 + g3 –( s1 + s2 + s3 )…(iv) and the process gets quite complicated. Based on the calculations above, a general pattern for calculation for each player other than the winner would be:`
`total points for x1 (p1)= n* s1 - g1 – P where P=( s1 + s2 + s3 + s4 ) and n=4`

`Simplifying further,`
`p1 = n* s1 - g1 – P = 4* s1 - g1 – ( s1 + s2 + s3 + s4 )= 4* s1 - s1 – g1 -( s2 + s3 + s4 )`
`= 3* s1 - g1 –( s2 + s3 + s4 ) which is (i).`

`Thus we can say that, `
`total points for x2 (p2)= n* s2 – g2 – P where P=( s1 + s2 + s3 + s4 ) and n=4`
`total points for x3 (p3)= n* s3 – g3 – P where P=( s1 + s2 + s3 + s4 ) and n=4`

`Finally, total points for x4 will be the sum of other three players multiplied by -1 because when any of the players have negative points, the winner is supposed to get them and their points have to be positive. If any of the players have positive points, then the winner has to pay out and their points have to be negative. `

`total points for x4 (p4)= -1*(p1 + p2 + p3)`
`= -1*[( n* s1 - g1 – P)+( n* s2 – g2 – P)+( n* s3 – g3 – P)]`
`= 3*P + g1 + g2 + g3 – n* s1 – n* s2 – n* s3 `
`=3*( s1 + s2 + s3 + s4 ) + g1 + g2 + g3 – 4* (s1 + s2 + s3)`
`= 3* s4 + g1 + g2 + g3 - (s1 + s2 + s3) which is (iv).`

`Therefore, in order to make the process simple, we need to calculate the points for x4 last and calculate the points for other players.`

`For any player i other than the winner, the calculation of points will be,`
`total points for player i (pi) = n* si – gi – P and the winner points will be, -1 * (∑n≠i (pn))`

## Class Structure
 
MainHandler class- This class makes interaction between the UI and java classes possible. It creates Player objects and adds a new hand and calculates points through Hand class based on player input passed from Activity classes. 

Player class- This class creates a player object with a name. It also keeps track of a user's game score and the points scored by this player in different hands.

Hand class- This class calculates all players' points including the winner's. The algorithm defined above has been implemented in this class.

MainActivity class- An activity class that shows the splash screen of MaalCounter. This activity's content is activity_main_page.xml.

MenuActivity class- An activity class that shows all the main buttons like Start a Scoreboard and Edit Maal Points to interact with this app. This activity's content is activity_menu.xml.

AddPlayersActivity class- An activity class that gets player names input from the user. This activity's content is activity_add_players.xml.

ScoreboardActivity class- An activity class that has a table like structure, which shows points of different users for different hands. This activity's content is activity_scoreboard.xml.

DatabasePointsHandler class- This Handler class creates and manages table of player points, which should be created empty when a user starts a new scoreboard.

DatabaseMaalHandler class- This Handler class creates and manages table of maal values which should never be empty and update itself when user edits the maal point values.

AddPointsActivity class- This activity class lets user to add points for a hand in the game. This activity's content is activity_add_points.xml.

EditMaalActivity class- This activity class lets the user edit the maal point values and puts the updates values in Points database. This activity's content is activity_edit_maal.xml.

##Limitations of the current version

Currently, there are certain limitations to this program.

1.	The program is fully functional and calculates the correct scores only if there are six players to play.

2.	Only five hands can be played in a game. If users want to play more than 5 hands, users will have to start a new scoreboard again.

3.	Players with the same name can play. 

4.	If less than two players are added to play, the program won't show an error message to the user to prompt to add more players. 

5.	In the AddPointsActivity, a user can select all three options:  
A user should only be able to click one of these three options.

6.	If another user has already clicked Completed Game, the other users should not be able to click on it again. However, this functionality has not been implemented.

7.	The multiplier and currency options are not currently available. Although, there is some code in place.

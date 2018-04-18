# USTH SE 2018 
- Group members: Le Huy Duc-036, Cao Phuong Linh-091, Bui Vu Huy-082, Huynh Vinh Nam-114
- Project: make a game
- Inspire from: Megaman x4/ mummy maze/ striker 1945
https://en.wikipedia.org/wiki/Maze_of_the_Mummy
https://en.wikipedia.org/wiki/Mega_Man_X4
https://www.youtube.com/watch?v=l3VRxpvlTOQ

+ First function: Game menu, and make background images
+ Framework: just Java


************************************************************************************************************************************************************************************
************************************************************************************************************************************************************************************

Software engineering project design detail
I. Overview:
a) General:
- The project use model-view-controller (MVC) pattern.
- Each part can be implemented separately.
- How it work:
+ Model: information about objects in the game world. Models do not know about “views” or “controllers”. Base class: “GameObject”.
+ View: functions to display objects using information from models. When something needs to be render, the base class “GameView” will get information from a “GameObject” and draw it.
+ Controller: allow high-level control such as user-input. Also, it allow interaction between views and models.
b) Game window display:
-	The program use java class “Frame” to display everything to the screen. This is implemented in class “GameWindow”.
II. Details of each package:
1.	Package “main”:
-	Contain functions to display the game window and to get information about different game levels.
a)	GameConfig: (deprecated):
- Contain global information about the current gameplay. This will be moved to class “GameMap”.
b) GameMap:
- Contain information about the current levels: location of walls, of exit, size of the map,..
	c) GameWindow:
	- The main function of the game. This display the game to a window using java “Frame”.
- It uses a thread to update game logic and render. Game logic and render are updated at different intervals.
- To change the game scene, we use the class “GameScreen”. Instead of coding everything that needs to be displayed in GameWindow, we split the actual content of the scenes to different class (game menu, game play, credits,…). When a scene needs to be displayed, we create that scene and draw it on GameWindow.
d) package “gameScreens”:
- Base class: “GameScreen”: this is just a abstract class for other classes to inherit.
- PlayGameScreen: every gameplay objects is drawn here.
2. Package “models”:
- Models are basically objects in the game world, models “live” only inside the game world. They do not know they’re being displayed or controlled.
- The base class is “GameObject”.
3. Package “views”:
- Base class: GameView
- “Views” are classes that contain functions about drawing. 
- Each “view” is connected to one “model”. Its mission is to get information from the model and render it.
- ImageView: for objects that only has a constant image (wall, floor, …)
- AnimationView: for objects that has animation (movement, fighting, …)
4. Package “controllers”:
- “Controller” allow more interaction between “model” and “view”. And it allow higher-level control such as player control.
- “ControllerController” is just a class to order all “controllers” to run in a specific order/at once.


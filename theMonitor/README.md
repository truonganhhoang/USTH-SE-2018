TEAM: theMonitor

INTRODUCTION:
Members:
+ Tran Thi Thuy Kieu
+ Le Gia Anh Quy
+ Vu Hoang Minh

Inspired software: 2048. Link: https://gabrielecirulli.github.io/2048/
First function: Create tiles which have a value 2,4,... and move them by sliding forward, backward, left, right.
Framework: Java.


PROCESS: WATER FALL

I-Requirement:
1.	Functions: Create Tiles, Moving Tiles, Logical operations, Record score.
2.	Interface: 2D game, easy to play, simple.
3.	Performance: High FPS, smooth.
	Trying to create the game as same as the original game as possible.

II- Design:
1. Gameplay/Start:
+ Start class main method
+ Game class extends JPanel
+ GameLoop
+ KeyListener
+ Update/Render
2.	Keyboard:
+ Static class
+ Previous and current keystate Boolean[]
+ Gets set in Gameplay class
3.	Tile:
+ On create, draw image and store
+ Keep track of animations
+ Position offsetted from GameBoard image
4.	GameBoard:
+ Tile[][]
+ move method for combining and setting animation on Tile
+ Key input here
+ Created and used in Gameplay/Start
+ Board image
5.	Stuff to add:
+ Sound
+ GUI
+ App stuff
+ Bot

III- Implementation: using Java

IV- Verification: Not yet

V- Maintenance:Not yet

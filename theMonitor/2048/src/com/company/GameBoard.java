package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameBoard {

    public static final int ROWS = 4; // ROW & COLUMN of the board
    public static final int COLS = 4;

    private final int startingTiles = 2; // Number of starting tiles
    private Tile[][] board; // Tiles in the board game
    private boolean lose;
    private boolean win;
    private BufferedImage gameBoard;    // Create Image of game board
    private BufferedImage finalBoard;   //
    private int x;  // Coordinate of the board game in JFrame
    private int y;

    private static int SPACING = 10;    //Space between tiles
    public static int BOARD_WIDTH = (COLS + 1)*SPACING+COLS*Tile.WIDTH; // Board game's width & height
    public static int BOARD_HEIGHT = (ROWS + 1)*SPACING+ROWS*Tile.HEIGHT;


    public GameBoard(int x, int y){  // Constructor of the Board Game
        this.x = x;
        this.y = y;
        board = new Tile[ROWS][COLS];
        gameBoard = new BufferedImage(BOARD_WIDTH,BOARD_HEIGHT,BufferedImage.TYPE_INT_RGB);
        finalBoard = new BufferedImage(BOARD_WIDTH,BOARD_HEIGHT,BufferedImage.TYPE_INT_RGB);

        createBoardImage(); // Function to draw Board's image
        start(); // Start the game
    }

    private void createBoardImage(){
        Graphics2D g = (Graphics2D) gameBoard.getGraphics();
        g.setColor(Color.lightGray);
        g.fillRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
        g.setColor(Color.white);

        for(int row = 0; row < ROWS; row++){
            for(int col = 0;col < COLS;col++){
                int x = SPACING + SPACING * col + Tile.WIDTH*col;
                int y = SPACING + SPACING * row + Tile.HEIGHT*row;
                g.fillRoundRect(x,y,Tile.WIDTH,Tile.HEIGHT,Tile.ARC_WIDTH,Tile.ARC_HEIGHT);
            }
        }
    }

    private void start(){
        for(int i = 0;i < startingTiles; i++){
            spawnRandom();
        }
    }

    // function to spawn random tiles
    private void spawnRandom(){
        Random random = new Random();
        boolean notValid = true;

        while(notValid){
            int location = random.nextInt(ROWS * COLS);
            int row = location / ROWS;
            int col = location % COLS;
            Tile current = board [row][col];
            if(current == null){
                int value = random.nextInt(10) < 8 ? 2 : 4;
                Tile tile = new Tile(value,getTileX(col),getTileY(row));
                board[row][col] = tile;
                notValid = false;
            }
        }
    }

    public int getTileX(int col){
        return SPACING + col * Tile.WIDTH + col * SPACING;
    }

    public int getTileY(int row){
        return SPACING + row * Tile.HEIGHT + row * SPACING;
    }

    public void render(Graphics2D g){
        Graphics2D g2d = (Graphics2D)finalBoard.getGraphics();
        g2d.drawImage(gameBoard,0,0,null);

        //draw tiles
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                Tile current = board[row][col];
                if(current == null) continue;
                current.render(g2d);
            }
        }

        g.drawImage(finalBoard,x,y,null);

    }

    public void update(){
        checkKeys();

        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                Tile current = board[row][col];
                if(current == null) continue;
                current.update();
                //reset position
                if(current.getValue() == 4096){
                    win = true;
                }
            }
        }
    }

    private void checkKeys(){
        if(Keyboard.typed(KeyEvent.VK_LEFT)){
            //move tiles left

        }
        if(Keyboard.typed(KeyEvent.VK_RIGHT)){
            //move tiles right

        }
        if(Keyboard.typed(KeyEvent.VK_UP)){
            //move tiles up

        }
        if(Keyboard.typed(KeyEvent.VK_DOWN)){
            //move tiles down

        }
    }
}

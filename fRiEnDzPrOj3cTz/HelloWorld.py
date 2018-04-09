import pygame
import sys
from pygame.locals import *

SCREENWIDTH = 800
SCREENHEIGHT = 600
SCREENFONT = "fonts/Fixedsys500c.ttf"

pygame.init()

screen = pygame.display.set_mode((SCREENWIDTH,SCREENHEIGHT))
pygame.display.set_caption('Hello World')

while True: 
    for event in pygame.event.get():
    	if event.type == QUIT:      
    		pygame.quit()
    		sys.exit()
    	font = pygame.font.Font(SCREENFONT, 50)
        text = font.render("Hello World!...",0,(255,100,200))  
        screen.blit(text,(180,100))
                      
    pygame.display.update()
import pygame,sys
from pygame.locals import *

FPS = 40
SCREENHEIGHT = 800
SCREENHWIDTH = 600

pygame.init()

screen = pygame.display.set_mode((SCREENHEIGHT, SCREENHWIDTH))
pygame.display.set_caption('Hello World')

while True: 
	for event in pygame.event.get():
		if event.type == QUIT:      
			pygame.quit()
			sys.exit()
	font = pygame.font.SysFont ("fonts/Fixedsys500c.ttf", 50)
	rendered = font.render("Hello World!...",0,(255,100,200))  
	screen.blit(rendered,(220,220))
	pygame.display.update()
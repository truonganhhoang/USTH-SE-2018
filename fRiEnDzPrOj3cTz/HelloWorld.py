import pygame,sys
from pygame.locals import *
pygame.init()

hello = pygame.display.set_mode((800,600))
pygame.display.set_caption('Hello World')

while True: 
    for event in pygame.event.get():
    	if event.type == QUIT:      
    		pygame.quit()
    		sys.exit()
    	myfont = pygame.font.SysFont ("fonts/Fixedsys500c.ttf", 50)
        rendered = myfont.render("Hello World!...",0,(255,100,200))  
        hello.blit(rendered,(180,100))
                      
    pygame.display.update() 








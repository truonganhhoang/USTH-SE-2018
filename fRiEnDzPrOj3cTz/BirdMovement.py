import pygame
import os


img_path = os.path.join('imgs/bluebird-upflap.png')

class Bird(object):  # represents the bird, not the game
    def __init__(self):
        # the constructor of the class 
        self.image = pygame.image.load(img_path)
        # the bird's position
        self.x = 0
        self.y = 0

    def handle_keys(self):
        #handles keys by user
        key = pygame.key.get_pressed()

        speed = 10 if key[pygame.K_LSHIFT] else 5 # declare the speed of the bird

        if key[pygame.K_DOWN]: # down key
            self.y += speed # move down
        elif key[pygame.K_UP]: # up key
            self.y -= speed # move up
        if key[pygame.K_RIGHT]: # right key
            self.x += speed # move right
        elif key[pygame.K_LEFT]: # left key
            self.x -= speed # move left

        if self.y < 0:
            self.y = 0
        if self.y > SCREENHEIGHT:
            self.y = SCREENHEIGHT #TODO: edit this line into next scene
        if self.x < 0:
            self.x = 0
        if self.x > SCREENWIDTH:
            self.x = SCREENWIDTH #TODO: edit this line into the background floor



    def draw(self, surface):
        # draw on the surface
        # blit yourself at your current position
        surface.blit(self.image, (self.x, self.y))


FPS = 60
SCREENWIDTH = 640
SCREENHEIGHT = 400
pygame.init()
screen = pygame.display.set_mode((SCREENWIDTH, SCREENHEIGHT))

bird = Bird() # create an instance
clock = pygame.time.Clock()

playerAlive = True
while playerAlive:
    # handle every event since the last frame.
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit() # quit the screen
            playerAlive = False

    bird.handle_keys() # handle the keys

    screen.fill((255,255,255)) # fill the screen with white color
    bird.draw(screen) # draw the bird to the screen
    pygame.display.update() # update the screen

    clock.tick(FPS)
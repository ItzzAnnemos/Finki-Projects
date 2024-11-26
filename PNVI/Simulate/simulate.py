# Simulate (a Simon clone)
# By Al Sweigart al@inventwithpython.com
# http://inventwithpython.com/pygame
# Released under a "Simplified BSD" license

import random, sys, time, pygame
from pygame.locals import *

FPS = 30
WINDOWWIDTH = 1080
WINDOWHEIGHT = 720
FLASHSPEED = 500  # in milliseconds
FLASHDELAY = 200  # in milliseconds
BUTTONSIZE = 200
BUTTONGAPSIZE = 20
TIMEOUT = 5  # seconds before game over if no button is pushed.

#                R    G    B
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
BRIGHTRED = (255, 0, 0)
RED = (155, 0, 0)
BRIGHTGREEN = (0, 255, 0)
GREEN = (0, 155, 0)
BRIGHTBLUE = (0, 0, 255)
BLUE = (0, 0, 155)
BRIGHTYELLOW = (255, 255, 0)
YELLOW = (155, 155, 0)

BRIGHTORANGE = (255, 165, 0)
ORANGE = (155, 90, 0)
BRIGHTPURPLE = (160, 32, 240)
PURPLE = (100, 20, 155)
BRIGHTCYAN = (0, 255, 255)
CYAN = (0, 155, 155)
BRIGHTPINK = (255, 105, 180)
PINK = (155, 65, 110)
BRIGHTTEAL = (0, 128, 128)
TEAL = (0, 80, 80)
BRIGHTLIME = (50, 205, 50)
LIME = (30, 120, 30)
BRIGHTMAGENTA = (255, 0, 255)
MAGENTA = (155, 0, 155)
BRIGHTOLIVE = (128, 128, 0)
OLIVE = (85, 85, 0)
BRIGHTTURQUOISE = (64, 224, 208)
TURQUOISE = (40, 140, 130)
BRIGHTINDIGO = (75, 0, 130)
INDIGO = (45, 0, 80)
BRIGHTCHARTREUSE = (127, 255, 0)
CHARTREUSE = (85, 155, 0)
BRIGHTCORAL = (255, 127, 80)
CORAL = (155, 85, 50)

DARKGRAY = (40, 40, 40)
bgColor = BLACK

XMARGIN = int((WINDOWWIDTH - (2 * BUTTONSIZE) - BUTTONGAPSIZE) / 2)
YMARGIN = int((WINDOWHEIGHT - (2 * BUTTONSIZE) - BUTTONGAPSIZE) / 2)

# Rect objects for each of the four buttons
YELLOWRECT = pygame.Rect(XMARGIN, YMARGIN, BUTTONSIZE, BUTTONSIZE)
BLUERECT = pygame.Rect(XMARGIN + BUTTONSIZE + BUTTONGAPSIZE, YMARGIN, BUTTONSIZE, BUTTONSIZE)
REDRECT = pygame.Rect(XMARGIN, YMARGIN + BUTTONSIZE + BUTTONGAPSIZE, BUTTONSIZE, BUTTONSIZE)
GREENRECT = pygame.Rect(XMARGIN + BUTTONSIZE + BUTTONGAPSIZE, YMARGIN + BUTTONSIZE + BUTTONGAPSIZE, BUTTONSIZE,
                        BUTTONSIZE)


def main():
    global FPSCLOCK, DISPLAYSURF, BASICFONT, BEEP1, BEEP2, BEEP3, BEEP4, TIMEOUT, size

    pygame.init()
    FPSCLOCK = pygame.time.Clock()
    DISPLAYSURF = pygame.display.set_mode((WINDOWWIDTH, WINDOWHEIGHT))
    pygame.display.set_caption('Simulate')

    BASICFONT = pygame.font.Font('freesansbold.ttf', 16)
    infoSurf = BASICFONT.render('Match the pattern by clicking on the button or using the Q, W, A, S keys.', 1,
                                DARKGRAY)
    infoRect = infoSurf.get_rect()
    infoRect.topleft = (10, WINDOWHEIGHT - 25)

    # load the sound files
    BEEP1 = pygame.mixer.Sound('beep1.ogg')
    BEEP2 = pygame.mixer.Sound('beep2.ogg')
    BEEP3 = pygame.mixer.Sound('beep3.ogg')
    BEEP4 = pygame.mixer.Sound('beep4.ogg')

    # Initialize game variables
    pattern = []
    current_step = 0
    last_click_time = 0
    score = 0
    waiting_for_input = False
    trigger = 0

    XMARGIN = int((WINDOWWIDTH - (2 * BUTTONSIZE) - BUTTONGAPSIZE) / 2)
    YMARGIN = int((WINDOWHEIGHT - (2 * BUTTONSIZE) - BUTTONGAPSIZE) / 2)

    # Basic 4-button layout
    basic_buttons = {
        'yellow': {'color': YELLOW, 'bright': BRIGHTYELLOW,
                   'rect': pygame.Rect(XMARGIN, YMARGIN, BUTTONSIZE, BUTTONSIZE), 'beep': BEEP1},
        'blue': {'color': BLUE, 'bright': BRIGHTBLUE,
                 'rect': pygame.Rect(XMARGIN + BUTTONSIZE + BUTTONGAPSIZE, YMARGIN, BUTTONSIZE, BUTTONSIZE),
                 'beep': BEEP2},
        'red': {'color': RED, 'bright': BRIGHTRED,
                'rect': pygame.Rect(XMARGIN, YMARGIN + BUTTONSIZE + BUTTONGAPSIZE, BUTTONSIZE, BUTTONSIZE),
                'beep': BEEP3},
        'green': {'color': GREEN, 'bright': BRIGHTGREEN,
                  'rect': pygame.Rect(XMARGIN + BUTTONSIZE + BUTTONGAPSIZE, YMARGIN + BUTTONSIZE + BUTTONGAPSIZE,
                                      BUTTONSIZE, BUTTONSIZE), 'beep': BEEP4}
    }

    GRID_WIDTH = 3 * BUTTONSIZE + 2 * BUTTONGAPSIZE
    GRID_HEIGHT = 3 * BUTTONSIZE + 2 * BUTTONGAPSIZE
    START_X = (WINDOWWIDTH - GRID_WIDTH) // 2
    START_Y = (WINDOWHEIGHT - GRID_HEIGHT) // 2

    advanced_buttons = {
        'orange': {'color': ORANGE, 'bright': BRIGHTORANGE,
                   'rect': pygame.Rect(START_X, START_Y, BUTTONSIZE, BUTTONSIZE), 'beep': BEEP1},
        'purple': {'color': PURPLE, 'bright': BRIGHTPURPLE,
                   'rect': pygame.Rect(START_X + BUTTONSIZE + BUTTONGAPSIZE, START_Y, BUTTONSIZE, BUTTONSIZE),
                   'beep': BEEP2},
        'cyan': {'color': CYAN, 'bright': BRIGHTCYAN,
                 'rect': pygame.Rect(START_X + 2 * (BUTTONSIZE + BUTTONGAPSIZE), START_Y, BUTTONSIZE, BUTTONSIZE),
                 'beep': BEEP3},
        'pink': {'color': PINK, 'bright': BRIGHTPINK,
                 'rect': pygame.Rect(START_X, START_Y + BUTTONSIZE + BUTTONGAPSIZE, BUTTONSIZE, BUTTONSIZE),
                 'beep': BEEP4},
        'teal': {'color': TEAL, 'bright': BRIGHTTEAL,
                 'rect': pygame.Rect(START_X + BUTTONSIZE + BUTTONGAPSIZE, START_Y + BUTTONSIZE + BUTTONGAPSIZE,
                                     BUTTONSIZE, BUTTONSIZE), 'beep': BEEP1},
        'orange2': {'color': ORANGE, 'bright': BRIGHTORANGE,
                    'rect': pygame.Rect(START_X + 2 * (BUTTONSIZE + BUTTONGAPSIZE),
                                        START_Y + BUTTONSIZE + BUTTONGAPSIZE, BUTTONSIZE, BUTTONSIZE), 'beep': BEEP2},
        'purple2': {'color': PURPLE, 'bright': BRIGHTPURPLE,
                    'rect': pygame.Rect(START_X, START_Y + 2 * (BUTTONSIZE + BUTTONGAPSIZE), BUTTONSIZE, BUTTONSIZE),
                    'beep': BEEP3},
        'cyan2': {'color': CYAN, 'bright': BRIGHTCYAN,
                  'rect': pygame.Rect(START_X + BUTTONSIZE + BUTTONGAPSIZE, START_Y + 2 * (BUTTONSIZE + BUTTONGAPSIZE),
                                      BUTTONSIZE, BUTTONSIZE), 'beep': BEEP4},
        'pink2': {'color': PINK, 'bright': BRIGHTPINK, 'rect': pygame.Rect(START_X + 2 * (BUTTONSIZE + BUTTONGAPSIZE),
                                                                           START_Y + 2 * (BUTTONSIZE + BUTTONGAPSIZE),
                                                                           BUTTONSIZE, BUTTONSIZE), 'beep': BEEP1}
    }

    while True:  # main game loop
        clicked_button = None  # button that was clicked (set to YELLOW, RED, GREEN, or BLUE)
        DISPLAYSURF.fill(bgColor)

        current_buttons = advanced_buttons if score > 3 else basic_buttons

        for btn in current_buttons.values():
            pygame.draw.rect(DISPLAYSURF, btn['color'], btn['rect'])

        score_surf = BASICFONT.render('Score: ' + str(score), 1, WHITE)
        score_rect = score_surf.get_rect()
        score_rect.topleft = (WINDOWWIDTH - 100, 10)
        DISPLAYSURF.blit(score_surf, score_rect)

        DISPLAYSURF.blit(infoSurf, infoRect)

        checkForQuit()

        for event in pygame.event.get():  # event handling loop
            if event.type == MOUSEBUTTONUP:
                mousex, mousey = event.pos
                clicked_button = getButtonClicked(mousex, mousey, current_buttons)
            elif event.type == KEYDOWN:
                if score <= 3:
                    if event.key == K_q:
                        clickedButton = YELLOW
                    elif event.key == K_w:
                        clickedButton = BLUE
                    elif event.key == K_a:
                        clickedButton = RED
                    elif event.key == K_s:
                        clickedButton = GREEN

        if score > 10 and TIMEOUT > 3:
            TIMEOUT -= 1

        if not waiting_for_input:
            pygame.display.update()
            pygame.time.wait(1000)
            if trigger == 4:
                pattern.clear()
                for i in range(score):
                    pattern.append(random.choice(list(current_buttons.keys())))

            pattern.append(random.choice(list(current_buttons.keys())))

            for button_name in pattern:
                flashButtonAnimation(current_buttons[button_name])
                pygame.time.wait(FLASHDELAY)
            waiting_for_input = True
            trigger += 1
        else:
            # wait for the player to enter buttons
            if clicked_button and clicked_button == pattern[current_step]:
                flashButtonAnimation(current_buttons[clicked_button])
                current_step += 1
                last_click_time = time.time()

                if current_step == len(pattern):
                    changeBackgroundAnimation()
                    score += 1
                    waiting_for_input = False
                    current_step = 0
            elif (clicked_button and clicked_button != pattern[current_step]) or \
                 (current_step != 0 and time.time() - TIMEOUT > last_click_time):
                gameOverAnimation()
                pattern = []
                current_step = 0
                waiting_for_input = False
                score = 0
                pygame.time.wait(1000)
                changeBackgroundAnimation()
                trigger = 0

        pygame.display.update()
        FPSCLOCK.tick(FPS)


def flashButtonAnimation(button_info, animation_speed=50):
    rect = button_info['rect']
    flash_color = button_info['bright']
    sound = button_info['beep']

    orig_surf = DISPLAYSURF.copy()
    flash_surf = pygame.Surface((BUTTONSIZE, BUTTONSIZE))
    flash_surf = flash_surf.convert_alpha()
    r, g, b = flash_color
    sound.play()

    for start, end, step in ((0, 255, 1), (255, 0, -1)):
        for alpha in range(start, end, animation_speed * step):
            checkForQuit()
            DISPLAYSURF.blit(orig_surf, (0, 0))
            flash_surf.fill((r, g, b, alpha))
            DISPLAYSURF.blit(flash_surf, rect.topleft)
            pygame.display.update()
            FPSCLOCK.tick(FPS)
    DISPLAYSURF.blit(orig_surf, (0, 0))


def getButtonClicked(x, y, current_buttons):
    for button_name, button_info in current_buttons.items():
        if button_info['rect'].collidepoint((x, y)):
            return button_name
    return None


def changeBackgroundAnimation(animationSpeed=40):
    global bgColor
    newBgColor = (random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))
    newBgSurf = pygame.Surface((WINDOWWIDTH, WINDOWHEIGHT))
    newBgSurf = newBgSurf.convert_alpha()
    r, g, b = newBgColor
    for alpha in range(0, 255, animationSpeed):
        checkForQuit()
        DISPLAYSURF.fill(bgColor)
        newBgSurf.fill((r, g, b, alpha))
        DISPLAYSURF.blit(newBgSurf, (0, 0))
        pygame.display.update()
        FPSCLOCK.tick(FPS)
    bgColor = newBgColor


def gameOverAnimation(color=WHITE, animationSpeed=50):
    origSurf = DISPLAYSURF.copy()
    flashSurf = pygame.Surface(DISPLAYSURF.get_size())
    flashSurf = flashSurf.convert_alpha()
    BEEP1.play()
    BEEP2.play()
    BEEP3.play()
    BEEP4.play()
    r, g, b = color
    for i in range(3):
        for start, end, step in ((0, 255, 1), (255, 0, -1)):
            for alpha in range(start, end, animationSpeed * step):
                checkForQuit()
                flashSurf.fill((r, g, b, alpha))
                DISPLAYSURF.blit(origSurf, (0, 0))
                DISPLAYSURF.blit(flashSurf, (0, 0))
                pygame.display.update()
                FPSCLOCK.tick(FPS)


def terminate():
    pygame.quit()
    sys.exit()


def checkForQuit():
    for event in pygame.event.get(QUIT):
        terminate()
    for event in pygame.event.get(KEYUP):
        if event.key == K_ESCAPE:
            terminate()
        pygame.event.post(event)


if __name__ == '__main__':
    main()
import pygame, random

from memoryGame import ALLCOLORS

FPS = 30
WINDOWHEIGHT = 640
WINDOWWIDTH = 480
BOXSIZE = 70
GAPSIZE = 10
BOARDWIDTH = 5  # num of boxes in a row
BOARDHEIGHT = 5  # num of boxes in a column

XMARGIN = int((WINDOWWIDTH - (BOARDWIDTH * (BOXSIZE + GAPSIZE))) / 2)
YMARGIN = 30

GRAY = (100, 100, 100)
NAVYBLUE = (60, 60, 100)
WHITE = (255, 255, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)
BLACK = (0, 0, 0)

BGCOLOR = NAVYBLUE
LIGHTBGCOLOR = GRAY
BOXCOLOR = WHITE

ALLCOLORS = (RED, GREEN, BLUE, YELLOW)

pygame.font.init()
font = pygame.font.Font(None, 26)
large_font = pygame.font.Font(None, 74)


def getColors():
    colors = []
    for i in range(BOARDWIDTH * BOARDHEIGHT):
        colors.append(ALLCOLORS[i % len(ALLCOLORS)])

    random.shuffle(colors)

    return colors

def drawBoard():
    DISPLAYSURF.fill(BGCOLOR)
    for row in range(BOARDHEIGHT):
        for column in range(BOARDWIDTH):
            x = XMARGIN + column * (BOXSIZE + GAPSIZE)
            y = YMARGIN + row * (BOXSIZE + GAPSIZE)
            color = currentState[row][column] if currentState[row][column] is not None else WHITE
            pygame.draw.rect(DISPLAYSURF, color, (x, y, BOXSIZE, BOXSIZE))


def drawColors(mainColor, nextColor=None):
    mainColorSize = 150
    nextColorSize = 100

    mainColorX = (WINDOWWIDTH - mainColorSize) // 2
    mainColorY = WINDOWHEIGHT - mainColorSize - 20

    pygame.draw.rect(DISPLAYSURF, mainColor, (mainColorX, mainColorY, mainColorSize, mainColorSize))
    current_color_text = font.render('Current Color', True, WHITE)
    current_color_text_rect = current_color_text.get_rect(
        center=(mainColorX + mainColorSize / 2, mainColorY + mainColorSize / 2))
    DISPLAYSURF.blit(current_color_text, current_color_text_rect)

    if nextColor is not None:
        nextColorX = mainColorX - nextColorSize - 10
        nextColorY = WINDOWHEIGHT - nextColorSize - 20

        pygame.draw.rect(DISPLAYSURF, nextColor, (nextColorX, nextColorY, nextColorSize, nextColorSize))
        next_color_text = font.render('Next Color', True, WHITE)
        next_color_text_rect = next_color_text.get_rect(
            center=(nextColorX + nextColorSize / 2, nextColorY + nextColorSize / 2))
        DISPLAYSURF.blit(next_color_text, next_color_text_rect)


def leftTopCoordsOfBox(boxX, boxY):
    left = boxX * (BOXSIZE + GAPSIZE) + XMARGIN
    top = boxY * (BOXSIZE + GAPSIZE) + YMARGIN
    return (left, top)


def getBoxAtPixel(x, y):
    for boxX in range(BOARDWIDTH):
        for boxY in range(BOARDHEIGHT):
            left, top = leftTopCoordsOfBox(boxX, boxY)
            boxRect = pygame.Rect(left, top, BOXSIZE, BOXSIZE)
            if boxRect.collidepoint(x, y):
                return (boxX, boxY)
    return None, None


def updateCurrentState(x, y, color):
    if 0 <= x < BOARDWIDTH and 0 <= y < BOARDHEIGHT:
        currentState[y][x] = color


def checkGameState(x, y):
    current_color = currentState[y][x]

    directions = [
        (0, -1),  # up
        (0, 1),  # down
        (-1, 0),  # left
        (1, 0)  # right
    ]

    for dx, dy in directions:
        new_x, new_y = x + dx, y + dy
        if (0 <= new_x < BOARDWIDTH and
                0 <= new_y < BOARDHEIGHT and
                currentState[new_y][new_x] is not None and
                currentState[new_y][new_x] == current_color):
            return True

    return False


def drawGameOver():
    overlay = pygame.Surface((WINDOWWIDTH, WINDOWHEIGHT))
    overlay.fill(BLACK)
    overlay.set_alpha(128)
    DISPLAYSURF.blit(overlay, (0, 0))

    gameOverText = large_font.render('Game Over!', True, WHITE)
    textRect = gameOverText.get_rect(center=(WINDOWWIDTH // 2, WINDOWHEIGHT // 2 - 50))
    DISPLAYSURF.blit(gameOverText, textRect)

    buttonWidth = 200
    buttonHeight = 50
    buttonX = (WINDOWWIDTH - buttonWidth) // 2
    buttonY = WINDOWHEIGHT // 2 + 20

    pygame.draw.rect(DISPLAYSURF, WHITE, (buttonX, buttonY, buttonWidth, buttonHeight))
    tryAgainText = font.render('Try Again', True, BLACK)
    textRect = tryAgainText.get_rect(center=(WINDOWWIDTH // 2, buttonY + buttonHeight // 2))
    DISPLAYSURF.blit(tryAgainText, textRect)

    return pygame.Rect(buttonX, buttonY, buttonWidth, buttonHeight)


def drawWinScreen():
    overlay = pygame.Surface((WINDOWWIDTH, WINDOWHEIGHT))
    overlay.fill((0, 0, 0))
    overlay.set_alpha(128)
    DISPLAYSURF.blit(overlay, (0, 0))

    winText = large_font.render('You Won!', True, (255, 215, 0))
    textRect = winText.get_rect(center=(WINDOWWIDTH // 2, WINDOWHEIGHT // 2 - 50))
    DISPLAYSURF.blit(winText, textRect)

    buttonWidth = 200
    buttonHeight = 50
    buttonX = (WINDOWWIDTH - buttonWidth) // 2
    buttonY = WINDOWHEIGHT // 2 + 20

    pygame.draw.rect(DISPLAYSURF, WHITE, (buttonX, buttonY, buttonWidth, buttonHeight))
    playAgainText = font.render('Play Again', True, BLACK)
    textRect = playAgainText.get_rect(center=(WINDOWWIDTH // 2, buttonY + buttonHeight // 2))
    DISPLAYSURF.blit(playAgainText, textRect)

    return pygame.Rect(buttonX, buttonY, buttonWidth, buttonHeight)

def printCurrentState():
    print("\nCurrent Board State:")
    for row in currentState:
        print([str(color) if color else "None" for color in row])

def resetGame():
    global currentState
    currentState = [[None for _ in range(BOARDWIDTH)] for _ in range(BOARDHEIGHT)]
    return getColors()


def checkWin():
    for row in range(BOARDHEIGHT):
        for col in range(BOARDWIDTH):
            if currentState[row][col] is None:
                return False

    for row in range(BOARDHEIGHT):
        for col in range(BOARDWIDTH):
            if col < BOARDWIDTH - 1:
                if currentState[row][col] == currentState[row][col + 1]:
                    return False
            if row < BOARDHEIGHT - 1:
                if currentState[row][col] == currentState[row + 1][col]:
                    return False

    return True

def main():
    global FPSCLOCK, DISPLAYSURF, currentState
    pygame.init()
    FPSCLOCK = pygame.time.Clock()
    DISPLAYSURF = pygame.display.set_mode((WINDOWWIDTH, WINDOWHEIGHT))
    currentState = [[None for _ in range(BOARDHEIGHT)] for _ in range(BOARDWIDTH)]

    pygame.display.set_caption('Color Fill Puzzle')

    running = True
    gameOver = False
    gameWon = False
    mouseX, mouseY = 0, 0

    colors = getColors()
    mainColor = colors.pop()
    nextColor = colors.pop()

    while running:
        drawBoard()
        drawColors(mainColor, nextColor)

        if gameOver:
            tryAgainButton = drawGameOver()
        elif gameWon:
            tryAgainButton = drawWinScreen()

        for event in pygame.event.get():
            if event.type == pygame.QUIT or (event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE):
                running = False
            if event.type == pygame.MOUSEMOTION:
                mouseX, mouseY = pygame.mouse.get_pos()
            if event.type == pygame.MOUSEBUTTONUP:
                mouseX, mouseY = pygame.mouse.get_pos()

                if gameOver or gameWon:
                    if tryAgainButton.collidepoint(mouseX, mouseY):
                        gameOver = False
                        gameWon = False
                        colors = resetGame()
                        mainColor = colors.pop()
                        nextColor = colors.pop() if colors else None
                else:
                    boxX, boxY = getBoxAtPixel(mouseX, mouseY)
                    if boxX is not None and boxY is not None:
                        updateCurrentState(boxX, boxY, mainColor)
                        if checkGameState(boxX, boxY):
                            gameOver = True
                        elif checkWin():
                            gameWon = True
                        else:
                            if nextColor is not None:
                                mainColor = nextColor
                                nextColor = colors.pop() if colors else None

        pygame.display.update()
        FPSCLOCK.tick(FPS)

    pygame.quit()

if __name__ == '__main__':
    main()
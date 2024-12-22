import pygame, random, math, os

FPS = 30
WINDOWHEIGHT = 640
WINDOWWIDTH = 480
clock = pygame.time.Clock()

pygame.font.init()
pygame.display.init()
pygame.mixer.init()

font = pygame.font.Font(None, 26)
large_font = pygame.font.Font(None, 74)

spaceShipImg = pygame.image.load("space_ship.png")
crystalImg = pygame.image.load("crystal.png")
bulletImg = pygame.image.load("bullet.png")
backgroundImg = pygame.image.load("background.jpg")
meteorImg = pygame.image.load("rock.png")
mediumMeteorImg = pygame.image.load("rockMedium.png")
bigMeteorImg = pygame.image.load("rockBig.png")
backgroundSound = pygame.mixer.Sound("backgroundSound.wav")
bulletSound = pygame.mixer.Sound("bulletSound.wav")
explosionSound = pygame.mixer.Sound("explosionSound.wav")
crystalSound = pygame.mixer.Sound("crystalSound.wav")
gameOverSound = pygame.mixer.Sound("gameOverSound.wav")

bulletSound.set_volume(0.5)
explosionSound.set_volume(0.5)
crystalSound.set_volume(0.5)
backgroundSound.set_volume(0.5)
backgroundSound.play(-1)

spaceShipX = 200
spaceShipY = 500

smallMeteors = []
for _ in range(12):
    meteorX = random.randint(0, WINDOWWIDTH - 64)
    meteorY = random.randint(-700, -70)
    smallMeteors.append({"x": meteorX, "y": meteorY})

mediumMeteors = []
for _ in range(7):
    meteorX = random.randint(0, WINDOWWIDTH - 80)
    meteorY = random.randint(-900, -85)
    mediumMeteors.append({"x": meteorX, "y": meteorY})

bigMeteors = []
for _ in range(5):
    meteorX = random.randint(0, WINDOWWIDTH - 100)
    meteorY = random.randint(-1100, -105)
    bigMeteors.append({"x": meteorX, "y": meteorY})

crystalX = random.randint(0, WINDOWWIDTH - 64)
crystalY = -70

bulletX = 1000
bulletY = 1000
bulletState = 'ready'
bulletCooldown = 0
MAX_BULLET_COOLDOWN = 15 * FPS

def loadHighScore():
    if os.path.exists("highscore.txt"):
        with open("highscore.txt", "r") as file:
            try:
                return int(file.read().strip())
            except ValueError:
                return 0
    return 0

def saveHighScore(highscore):
    with open("highscore.txt", "w") as file:
        file.write(str(highscore))

def drawSpaceShip():
    screen.blit(spaceShipImg, (spaceShipX, spaceShipY))


def drawMeteors(meteors):
    for meteor in meteors:
        screen.blit(meteorImg, (meteor['x'], meteor['y']))


def drawMediumMeteors(meteors):
    for meteor in meteors:
        screen.blit(mediumMeteorImg, (meteor['x'], meteor['y']))


def drawBigMeteors(meteors):
    for meteor in meteors:
        screen.blit(bigMeteorImg, (meteor['x'], meteor['y']))


def crystal():
    screen.blit(crystalImg, (crystalX, crystalY))


def fireBullet(x, y):
    global bulletState
    bulletState = 'fire'
    screen.blit(bulletImg, (x + 20, y))


def drawBulletCooldown():
    bullet_icon_size = 30
    scaled_bulletImg = pygame.transform.scale(bulletImg, (bullet_icon_size, bullet_icon_size))

    if bulletState == 'ready' and bulletCooldown == 0:
        screen.blit(scaled_bulletImg, (WINDOWWIDTH - 50, WINDOWHEIGHT - 50))
    else:
        scaled_bulletImg.set_alpha(100)
        screen.blit(scaled_bulletImg, (WINDOWWIDTH - 50, WINDOWHEIGHT - 50))

    if bulletCooldown > 0:
        cooldown_text = font.render(str(bulletCooldown // FPS), True, pygame.Color('white'))
        screen.blit(cooldown_text, (WINDOWWIDTH - 55, WINDOWHEIGHT - 30))


def isCollision(x1, y1, x2, y2):
    distance = math.sqrt(math.pow(x1 - x2, 2) + math.pow(y1 - y2, 2))
    return distance < 50


def drawScore():
    score_surf = font.render('Score: ' + str(score), 1, pygame.Color('white'))
    score_rect = score_surf.get_rect()
    score_rect.topleft = (5, 30)
    screen.blit(score_surf, score_rect)

def drawHighScore():
    score_surf = font.render('Highscore: ' + str(highscore), 1, pygame.Color('lime'))
    score_rect = score_surf.get_rect()
    score_rect.topleft = (5, 10)
    screen.blit(score_surf, score_rect)


def drawGameOver():
    overlay = pygame.Surface((WINDOWWIDTH, WINDOWHEIGHT))
    overlay.fill(pygame.Color('black'))
    overlay.set_alpha(128)
    screen.blit(overlay, (0, 0))

    gameOverText = large_font.render('Game Over!', True, pygame.Color("white"))
    textRect = gameOverText.get_rect(center=(WINDOWWIDTH // 2, WINDOWHEIGHT // 2 - 50))
    screen.blit(gameOverText, textRect)

    buttonWidth = 200
    buttonHeight = 50
    buttonX = (WINDOWWIDTH - buttonWidth) // 2
    buttonY = WINDOWHEIGHT // 2 + 20

    pygame.draw.rect(screen, pygame.Color("white"), (buttonX, buttonY, buttonWidth, buttonHeight))
    tryAgainText = font.render('Try Again', True, pygame.Color("black"))
    textRect = tryAgainText.get_rect(center=(WINDOWWIDTH // 2, buttonY + buttonHeight // 2))
    screen.blit(tryAgainText, textRect)

    return pygame.Rect(buttonX, buttonY, buttonWidth, buttonHeight)


def resetGame():
    global spaceShipX, spaceShipY, crystalX, crystalY, score, bulletState, bulletCooldown, bulletX, smallMeteors,\
        mediumMeteors, bigMeteors, smallMeteorSpeed, mediumMeteorSpeed, bigMeteorSpeed, gameOverSoundPlayed
    score = 0
    spaceShipX = 200
    spaceShipY = 500
    gameOverSoundPlayed = False
    crystalX = random.randint(0, WINDOWWIDTH - 64)
    crystalY = -70
    bulletX = 1000
    bulletState = 'ready'
    bulletCooldown = 0
    smallMeteorSpeed = 3
    mediumMeteorSpeed = 2
    bigMeteorSpeed = 1
    smallMeteors = []
    for _ in range(12):
        meteorX = random.randint(0, WINDOWWIDTH - 64)
        meteorY = random.randint(-700, -70)
        smallMeteors.append({"x": meteorX, "y": meteorY})

    mediumMeteors = []
    for _ in range(7):
        meteorX = random.randint(0, WINDOWWIDTH - 80)
        meteorY = random.randint(-900, -85)
        mediumMeteors.append({"x": meteorX, "y": meteorY})

    bigMeteors = []
    for _ in range(5):
        meteorX = random.randint(0, WINDOWWIDTH - 100)
        meteorY = random.randint(-1100, -105)
        bigMeteors.append({"x": meteorX, "y": meteorY})


def main():
    global screen, spaceShipX, spaceShipY, crystalX, crystalY, bulletX, bulletY, bulletState, bulletCooldown, \
        smallMeteors, mediumMeteors, bigMeteors, score, highscore, smallMeteorSpeed, mediumMeteorSpeed, bigMeteorSpeed,\
        gameOverSoundPlayed
    pygame.init()

    pygame.display.set_caption('Space Scavenger')
    screen = pygame.display.set_mode((WINDOWWIDTH, WINDOWHEIGHT))

    gameOver = False
    running = True
    xChange = 0
    yChange = 0
    score = 0
    highscore = loadHighScore()

    smallMeteorSpeed = 3
    mediumMeteorSpeed = 2
    bigMeteorSpeed = 1

    gameOverSoundPlayed = False

    while running:
        screen.fill((0, 0, 0))
        screen.blit(backgroundImg, (0, 0))

        if gameOver:
            tryAgain = drawGameOver()
            backgroundSound.stop()
            if score > highscore:
                highscore = score
                saveHighScore(highscore)
        else:
            drawSpaceShip()
            crystal()
            drawScore()
            drawHighScore()
            drawMeteors(smallMeteors)
            if score >= 5:
                drawMediumMeteors(mediumMeteors)

            if score >= 15:
                drawBigMeteors(bigMeteors)

            if bulletCooldown > 0:
                bulletCooldown -= 1
                if bulletCooldown == 0:
                    bulletState = 'ready'
            drawBulletCooldown()

        for event in pygame.event.get():
            if event.type == pygame.QUIT or (event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE):
                running = False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT or event.key == pygame.K_a:
                    xChange = -5
                if event.key == pygame.K_RIGHT or event.key == pygame.K_d:
                    xChange = 5
                if event.key == pygame.K_UP or event.key == pygame.K_w:
                    yChange = -5
                if event.key == pygame.K_DOWN or event.key == pygame.K_s:
                    yChange = 5
                if event.key == pygame.K_SPACE:
                    if bulletState == 'ready' and bulletCooldown == 0:
                        bulletState = 'fire'
                        bulletX = spaceShipX
                        bulletY = spaceShipY
                        fireBullet(bulletX, bulletY)
                        bulletCooldown = MAX_BULLET_COOLDOWN
                        bulletSound.play()
            if event.type == pygame.KEYUP:
                if (event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT
                        or event.key == pygame.K_a or event.key == pygame.K_d):
                    xChange = 0
                if (event.key == pygame.K_UP or event.key == pygame.K_DOWN
                        or event.key == pygame.K_w or event.key == pygame.K_s):
                    yChange = 0
            if event.type == pygame.MOUSEBUTTONUP:
                mouseX, mouseY = pygame.mouse.get_pos()

                if gameOver:
                    if tryAgain.collidepoint(mouseX, mouseY):
                        gameOver = False
                        backgroundSound.play(-1)
                        resetGame()

        if bulletY < 0:
            bulletY = 1000
            bulletX = 1000
            bulletState = 'ready'
        if bulletState == 'fire':
            fireBullet(bulletX, bulletY)
            bulletY -= 7

        spaceShipX += xChange
        spaceShipY += yChange

        spaceShipX = max(0, min(spaceShipX, WINDOWWIDTH - 64))
        spaceShipY = max(0, min(spaceShipY, WINDOWHEIGHT - 64))

        crystalY += 2
        if crystalY > WINDOWHEIGHT:
            crystalX = random.randint(0, WINDOWWIDTH - 64)
            crystalY = -70

        if isCollision(spaceShipX, spaceShipY, crystalX, crystalY):
            score += 1
            crystalSound.play()
            crystalX = random.randint(0, WINDOWWIDTH - 64)
            crystalY = -70

        for meteor in smallMeteors:
            meteor['y'] += smallMeteorSpeed

            if meteor['y'] > WINDOWHEIGHT:
                meteor['x'] = random.randint(0, WINDOWWIDTH - 64)
                meteor['y'] = random.randint(-700, -70)

            if isCollision(meteor['x'], meteor['y'], bulletX, bulletY):
                bulletState = 'ready'
                explosionSound.play()
                bulletY = 1000
                bulletX = 1000
                meteor['x'] = random.randint(0, WINDOWWIDTH - 64)
                meteor['y'] = random.randint(-700, -70)

            if isCollision(meteor['x'], meteor['y'], spaceShipX, spaceShipY):
                if not gameOverSoundPlayed:
                    gameOverSound.play()
                    gameOverSoundPlayed = True
                gameOver = True

        if score >= 5:
            for meteor in mediumMeteors:
                meteor['y'] += mediumMeteorSpeed

                if meteor['y'] > WINDOWHEIGHT:
                    meteor['x'] = random.randint(0, WINDOWWIDTH - 80)
                    meteor['y'] = random.randint(-900, -85)

                if isCollision(meteor['x'], meteor['y'], bulletX, bulletY):
                    bulletState = 'ready'
                    explosionSound.play()
                    bulletY = 1000
                    bulletX = 1000
                    meteor['x'] = random.randint(0, WINDOWWIDTH - 80)
                    meteor['y'] = random.randint(-900, -85)

                if isCollision(meteor['x'], meteor['y'], spaceShipX, spaceShipY):
                    if not gameOverSoundPlayed:
                        gameOverSound.play()
                        gameOverSoundPlayed = True
                    gameOver = True

        if score >= 15:
            for meteor in bigMeteors:
                meteor['y'] += bigMeteorSpeed

                if meteor['y'] > WINDOWHEIGHT:
                    meteor['x'] = random.randint(0, WINDOWWIDTH - 100)
                    meteor['y'] = random.randint(-1100, -105)

                if isCollision(meteor['x'], meteor['y'], bulletX, bulletY):
                    bulletState = 'ready'
                    explosionSound.play()
                    bulletY = 1000
                    bulletX = 1000
                    meteor['x'] = random.randint(0, WINDOWWIDTH - 100)
                    meteor['y'] = random.randint(-1100, -105)

                if isCollision(meteor['x'], meteor['y'], spaceShipX, spaceShipY):
                    if not gameOverSoundPlayed:
                        gameOverSound.play()
                        gameOverSoundPlayed = True
                    gameOver = True

        if score > 0 and score % 10 == 0:
            smallMeteorSpeed += 0.005
            mediumMeteorSpeed += 0.005
            bigMeteorSpeed += 0.005

        pygame.display.update()
        clock.tick(FPS)


if __name__ == '__main__':
    main()
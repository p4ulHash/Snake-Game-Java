
// Aufgabe 2b: Erbt von AudGameWindow
public class SnakeGame extends AudGameWindow {

    // Aufgabe 2f: Konstante fuer Gittergroesse
    public static final int SQUARE_SIZE = 16;
    // Aufgabe 5d: Zeit pro Schritt (ms)
    public static final int STEP_TIME = 100;
    // Aufgabe 11a: Wachstum pro Apfel
    public static final int GROW_AMOUNT = 5;

    // Aufgabe 2g: Spielfeldgroesse in Gitter-Einheiten
    int width;
    int height;

    // Aufgabe 5f: Zeitstempel
    private long lastSnakeUpdate;

    // Aufgabe 2e / 11e: Punktestand
    int score;

    // Spielobjekte (Aufgaben 4f, 10d, 8c)
    private Snake snake;
    private Apple apple;
    private Brick[] wall;

    // Aufgabe 2e/91: Standard-Konstruktor
    public SnakeGame() {
        super(); // AudGameWindow initialisieren

        // Aufgabe 2g: Breite/Hoehe berechnen
        this.width = getGameAreaWidth() / SQUARE_SIZE;
        this.height = getGameAreaHeight() / SQUARE_SIZE;

        // Aufgabe 2e: Titel setzen
        this.score = 0;
        setTitle("AuD-Snake Score: " + score);

        // Aufgabe 5f: Zeit initialisieren
        this.lastSnakeUpdate = System.currentTimeMillis();

        // Aufgabe 8c: Mauer bauen (Umfang)
        int numBricks = (width * 2) + ((height - 2) * 2);
        wall = new Brick[numBricks];
        int wallIndex = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Nur am Rand bauen
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    wall[wallIndex++] = new Brick(x, y);
                }
            }
        }

        // Aufgabe 4f: Schlange erstellen
        snake = new Snake(width / 2, height / 2);

        // Aufgabe 10e: Ersten Apfel erstellen
        createNewApple();
    }

    // Aufgabe 2c: Main-Methode
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.start();
    }

    // Aufgabe 10d: Neuen Apfel erzeugen (ohne Kollision)
    private void createNewApple() {
        while (true) {
            // Zufallsposition innerhalb der Mauern
            int x = (int) (Math.random() * (width - 2)) + 1;
            int y = (int) (Math.random() * (height - 2)) + 1;

            // Pruefen ob Platz frei ist
            if (snake.collidesWith(x, y)) {
                continue;
            }

            this.apple = new Apple(x, y);
            break;
        }
    }

    // Aufgabe 5e: Spielzustand aktualisieren
    public void updateGame(long time) {
        // Aufgabe 5g: Schritte ausfuehren, wenn Zeit vergangen ist
        while (time - lastSnakeUpdate >= STEP_TIME) {
            snake.step();
            checkCollisions(); // Aufgabe 9b
            lastSnakeUpdate += STEP_TIME;
        }
    }

    // Aufgabe 2d: Spiel zeichnen
    public void paintGame(AudGraphics g) {
        // Hintergrund
        g.setColor(AudColor.WHITE);
        g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());

        // Aufgabe 8c: Mauer zeichnen
        for (Brick b : wall) {
            if (b != null) b.paint(g);
        }

        // Aufgabe 10b: Apfel zeichnen
        if (apple != null) {
            apple.paint(g);
        }

        // Aufgabe 4g: Schlange zeichnen
        if (snake != null) {
            snake.paint(g);
        }
    }

    // Aufgabe 6a: Eingabe verarbeiten
    @Override
    public void handleInput(int keyCode) {
        // Aufgabe 6b: Mapping auf Direction
        switch (keyCode) {
            case KeyEvent.VK_UP:
                snake.setNextDirection(Snake.Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.setNextDirection(Snake.Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.setNextDirection(Snake.Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setNextDirection(Snake.Direction.RIGHT);
                break;
        }
    }

    // Aufgabe 9b: Kollisionspruefung
    private void checkCollisions() {
        // Aufgabe 9c: Kollision mit Mauer
        for (Brick b : wall) {
            if (snake.collidesWith(b)) {
                stop();
                showDialog("You died! Score: " + score);
                return;
            }
        }

        // Aufgabe 9d: Kollision mit sich selbst
        if (snake.collidesWithSelf()) {
            stop();
            showDialog("You died! Score: " + score);
            return;
        }

        // Aufgabe 11d: Kollision mit Apfel
        if (apple != null && snake.collidesWith(apple)) {
            snake.grow(GROW_AMOUNT);        // Wachsen
            score += apple.getValue();      // Punkte (Aufgabe 11e)
            setTitle("AuD-Snake Score: " + score); // Titel update (Aufgabe 11f)
            createNewApple();               // Neuer Apfel
        }
    }
}
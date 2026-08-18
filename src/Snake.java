public class Snake {
    // Aufgabe 5a: Enumeration fuer Bewegungsrichtungen
    public enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    // Aufgabe 5b: Naechste Richtung
    private Direction nextDirection;
    // Aufgabe 6c: Letzte Richtung (gegen 180 grad-Wenden)
    private Direction lastDirection;

    // Aufgabe 4e.i: Farbe
    private AudColor color;

    // Aufgabe 4a: Array der Koerperteile (Array von Points)
    private Point[] points;

    // Aufgabe 4c: Konstruktor mit Laenge
    public Snake(int x, int y, int length) {
        // Aufgabe 4c.i: Exception bei ungueltiger Laenge
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        // Aufgabe 4c.i: Array anlegen (Standardwert ist null)
        points = new Point[length];

        // Aufgabe 4c.ii: Kopf setzen (Index 0) Rest bleibt null
        points[0] = new Point(x, y);

        // Aufgabe 4e.i: Farbe setzen
        this.color = AudColor.BLUE;

        // Aufgabe 5b: Richtung initialisieren
        this.nextDirection = Direction.RIGHT;
        this.lastDirection = Direction.RIGHT;
    }

    // Aufgabe 4d: ueberladener Konstruktor (Standardlaenge 5)
    public Snake(int x, int y) {
        this(x, y, 5);
    }

    // Aufgabe 5b: Setter fuer Richtung
    public void setNextDirection(Direction direction) {
        // Aufgabe 6c: Verhindern von 180-Grad-Drehungen
        // Abstand ordinal()-Werte gegenueberliegender Richtungen ist immer 2
        int diff = Math.abs(lastDirection.ordinal() - direction.ordinal());
        if (diff != 2) {
            this.nextDirection = direction;
        }
    }

    // Aufgabe 5c: Bewegungsschritt
    public void step() {
        // Aufgabe 5c.ii: Koerperteile verschieben (von vorne nach hinten)
        System.arraycopy(points, 0, points, 1, points.length - 1);

        // Aufgabe 5c.iii: Neuen Kopf berechnen basierend auf dem alten Kopf
        int headX = points[0].getX(); // Alter Kopf (wurde auf Index 1 kopiert)
        int headY = points[0].getY();

        switch (nextDirection) {
            case RIGHT: headX++; break;
            case DOWN:  headY++; break;
            case LEFT:  headX--; break;
            case UP:    headY--; break;
        }

        // Aufgabe 5c.iii: Neuen Kopf setzen
        points[0] = new Point(headX, headY);

        // Aufgabe 6c: lastDirection aktualisieren
        lastDirection = nextDirection;
    }

    // Aufgabe 11b: Wachsen
    public void grow(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Grow amount must be positive");
        }

        // Aufgabe 11c: Neues groesseres Array anlegen und alten Inhalt kopieren
        Point[] newPoints = new Point[points.length + amount];
        System.arraycopy(points, 0, newPoints, 0, points.length);
        this.points = newPoints;
    }

    // Aufgabe 9a: Kollision mit Item
    public boolean collidesWith(GameItem item) {
        return collidesWith(item.getPosition().getX(), item.getPosition().getY());
    }

    // Aufgabe 9a: Kollision mit Koordinaten
    public boolean collidesWith(int x, int y) {
        for (int i = 0; i < points.length; i++) {
            // Wichtig: null-Check, da das Array hinten leer sein kann
            if (points[i] != null && points[i].getX() == x && points[i].getY() == y) {
                return true;
            }
        }
        return false;
    }

    // Aufgabe 9d: Kollision mit sich selbst
    public boolean collidesWithSelf() {
        Point head = points[0];
        // Start bei 1, da Kopf (0) immer auf Kopf (0) liegt
        for (int i = 1; i < points.length; i++) {
            if (points[i] != null &&
                    points[i].getX() == head.getX() &&
                    points[i].getY() == head.getY()) {
                return true;
            }
        }
        return false;
    }

    // Aufgabe 4e.ii: Zeichnen
    public void paint(AudGraphics g) {
        g.setColor(color);
        int size = SnakeGame.SQUARE_SIZE;

        for (int i = 0; i < points.length; i++) {
            // Aufgabe 4e.iii: null-Eintraege ueberspringen
            if (points[i] != null) {
                g.fillRect(points[i].getX() * size, points[i].getY() * size, size, size);
            }
        }
    }
}

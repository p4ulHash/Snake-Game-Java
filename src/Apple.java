// Aufgabe 10a: Erbt von GameItem
public class Apple extends GameItem {

    // Aufgabe 10c: Statischer Zaehler fuer den Wert des naechsten Apfels
    private static int nextValue = 1;

    // Aufgabe 10c: Wert DIESES Apfels (konstant)
    public final int VALUE;

    // Aufgabe 10a: Konstruktor
    public Apple(int x, int y) {
        super(x, y);
        // Aufgabe 10c: Wert zuweisen und Zaehler erhoehen
        this.VALUE = nextValue;
        nextValue++;
    }

    // Aufgabe 10c: Getter
    public int getValue() {
        return VALUE;
    }

    // Aufgabe 10b: Zeichnet einen roten Kreis (Oval)
    public void paint(AudGraphics g) {
        g.setColor(AudColor.RED);
        int size = SnakeGame.SQUARE_SIZE;
        g.fillOval(position.getX() * size, position.getY() * size, size, size);
    }
}

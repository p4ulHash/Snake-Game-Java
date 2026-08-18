// Aufgabe 7: Muss abstract sein, da paint() abstrakt ist
public abstract class GameItem {

    // Aufgabe 7a: Position als Point-Objekt
    protected Point position;

    // Aufgabe 7a: Konstruktor
    public GameItem(int x, int y) {
        this.position = new Point(x, y);
    }

    // Aufgabe 7a: Getter fuer Position
    public Point getPosition() {
        return position;
    }

    // Aufgabe 7b: paint-Methode (Unterklassen muessen diese implementieren)
    public abstract void paint(AudGraphics g);
}
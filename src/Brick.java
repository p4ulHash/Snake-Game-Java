// Aufgabe 8: Erbt von GameItem
public class Brick extends GameItem {

    // Aufgabe 8b: Konstruktor ruft Eltern-Konstruktor auf
    public Brick(int x, int y) {
        super(x, y);
    }

    // Aufgabe 8a: Zeichnet ein dunkelgraues Quadrat
    @Override
    public void paint(AudGraphics g) {
        g.setColor(AudColor.DARK_GRAY);

        // Umrechnung Gitter -> Pixel
        int size = SnakeGame.SQUARE_SIZE;
        g.fillRect(getPosition().getX() * size, getPosition().getY() * size, size, size);
    }
}
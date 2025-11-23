import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Raindrop {

    private double x;
    private double y;
    private double speed;
    // private double accel;
    // private double drift;

    public Raindrop(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        // this.accel = accel;
        // this.drift = drift;
    }

    public void update(int panelHeight) {
        y += speed; // * accel;
        // x = x + drift;

        // Wrap to top when leaving screen
        if (y > panelHeight) {
            y = -20;
        }
    }

    public void draw(Graphics2D g) {
        g.draw(new Line2D.Double(x, y, x, y + 10));
    }

    // Optional: getters and setters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getSpeed() { return speed; }
    // public double getAccel() { return accel; }
    // public double getDrift() { return drift; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setSpeed(double speed) { this.speed = speed; }
    // public void setAccel(double accel) { this.speed = accel; }
    // public void setDrift(double drift) { this.speed = drift; }
}


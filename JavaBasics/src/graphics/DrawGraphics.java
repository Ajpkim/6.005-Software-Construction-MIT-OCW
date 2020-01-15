package graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class DrawGraphics {
    
    ArrayList<BouncingBox> boxes;
    BouncingBox box;
    
    /** Initializes this class for drawing. */
    public DrawGraphics() {
    
        boxes = new ArrayList<BouncingBox>();
        boxes.add(new BouncingBox(200, 50, Color.BLUE));
        boxes.add(new BouncingBox(85, 120, Color.RED));
        boxes.add(new BouncingBox(50, 200, Color.BLACK));
        
        // Setting movement
        boxes.get(0).setMovementVector(10, 6);
        boxes.get(1).setMovementVector(3, 6);
        boxes.get(2).setMovementVector(6, 6);
    }

    /** Draw the contents of the window on surface. Called 20 times per second. */
    public void draw(Graphics surface) {
        surface.drawLine(50, 50, 250, 250);
        surface.draw3DRect(50, 50, 100, 100, true);
        surface.drawString("YOOOO", 100, 90);
        surface.drawArc(95, 60, 50, 50, 90, 360);
        surface.fillOval(180, 180, 25, 30);
        
        
        for (int i = 0; i < boxes.size(); i++) {
            boxes.get(i).draw(surface);
        }
    }
} 
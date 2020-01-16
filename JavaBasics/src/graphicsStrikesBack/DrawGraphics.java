package graphicsStrikesBack;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class DrawGraphics {
    ArrayList<Bouncer> movingSprites;
    
    /** Initializes this class for drawing. */
    public DrawGraphics() {

        Rectangle box = new Rectangle(15, 20, Color.RED);
        Oval oval = new Oval(50, 50, Color.BLUE);
        
        
        movingSprites = new ArrayList<Bouncer>();
        movingSprites.add(new Bouncer(100, 170, box));
        movingSprites.add(new Bouncer(150, 75, box));
        movingSprites.add(new Bouncer(85, 85, oval));
        
        
        movingSprites.get(0).setMovementVector(3, 1);
        movingSprites.get(1).setMovementVector(4, 4);
        movingSprites.get(2).setMovementVector(3, 8);
        
    }

    /** Draw the contents of the window on surface. */
    public void draw(Graphics surface) {
        
        for (int i = 0; i < movingSprites.size(); i++) {
            movingSprites.get(i).draw(surface);
        
    }
}
}

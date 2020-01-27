/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        turtle.forward(sideLength);
        turtle.turn(90.0);
        turtle.forward(sideLength);
        turtle.turn(90.0);
        turtle.forward(sideLength);
        turtle.turn(90.0);
        turtle.forward(sideLength);
        turtle.turn(90.0);
        
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        double angle;
        angle = ((sides - 2) * 180.0) / sides;  // it's " / sides " because it's a REGULAR polygon
        return angle;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {

        int sides;
        sides = (int)java.lang.Math.rint(360.0 / (180.0 - angle));

        return sides;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        
        double angle;
        angle = calculateRegularPolygonAngle(sides);
        
        for(int i = 0; i < sides; i++) {
            turtle.forward(sideLength);
            turtle.turn(180 - angle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        
        double tangent = (double) (targetX - currentX) / (double) (targetY - currentY);
        
        // used atan function instead of atan2

        double angle = Math.toDegrees(Math.atan(tangent)) - currentHeading;
        
        return angle < 0.0 ? 360.0 + angle: angle;
        
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        
        ArrayList<Double> headings = new ArrayList<Double>();
        double heading = 0;
        for(int i = 0; i < (xCoords.size() - 1); i++) {
            heading = calculateHeadingToPoint(heading, xCoords.get(i), yCoords.get(i), 
                                                       xCoords.get(i + 1), yCoords.get(i + 1));
            headings.add(heading);    
        }
        return headings;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    
    /** helper methods... drawStar, relocateTriangle, drawRandomPoly */
    public static void drawStar(Turtle turtle, int num, int length, int angle){
        for(int i = 0; i < num; i++) {
            turtle.forward(length);
            turtle.turn(angle);
        }
    }
    
    public static void relocateTri(Turtle turtle, int initial, int length) {
        turtle.forward(initial);
        turtle.turn(120);
        turtle.forward(250);
        turtle.turn(120);
        turtle.forward(250);
    }
    
    public static void drawRandomPoly(Turtle turtle) {
      int polyLength;
      int polySides;
      double move;
      
      for(int i = 0; i < 10; i++) {
           polyLength = (int)(java.lang.Math.rint(Math.random() * 100));
           polySides = (int)(java.lang.Math.rint(Math.random() * 10));
          drawRegularPolygon(turtle, polySides, polyLength);
          
          // Moving the polygon starting places
          move = (Math.random());
          if (move < 0.25) {
              turtle.forward(45);
              turtle.turn(45);
          } if (move > 0.25 || move < 0.50) {
              turtle.forward(-120);
              turtle.turn(90);
          } if (move > 0.50 || move < 0.75) {
              turtle.forward(135);
              turtle.turn(60);
          } if (move > 0.75) {
              turtle.forward(60);
              turtle.turn(150);                
          }   
      }
    }
    
    public static void drawPersonalArt(Turtle turtle) {
        
       drawStar(turtle, 27, 200, 140);
       
       turtle.forward(450);
       turtle.turn(120);
       for(int i = 0; i < 4; i++) {
           drawRegularPolygon(turtle, 3, 115);
           turtle.forward(75);
       }
       turtle.forward(44);
       turtle.turn(121);
       turtle.forward(450);
       turtle.turn(135);
       turtle.forward(125);
       
       drawStar(turtle, 27, 200, 140);
       turtle.turn(315);       
       turtle.forward(90);
       
       drawStar(turtle, 27, 200, 140);
       
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

//        drawSquare(turtle, 40);
        
//        drawRegularPolygon(turtle, 7, 50);  // Testing drawRegularPolygon method

        drawPersonalArt(turtle);  // Creating own personal art
        
        // draw the window
        turtle.draw();
    }

}

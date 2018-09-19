//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Creates, displays, and modifies tigers in a Jungle Park
// Files:           JunglePark.java
// Course:          CS 300, 2018
//
// Author:          Daniel Chu
// Email:           dchu22@wisc.edu
// Lecturer's Name: Professor Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Tolga Beser
// Partner Email:   tbeser@wisc.edu
// Partner Lecturer's Name: Professor Gary Dahl
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: N/A
// Online Sources: N/A
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;

public class JunglePark {

  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Tiger[] tigers; // array storing the current tigers present
  // in the Jungle Park

  private static Random randGen; // Generator of random numbers

  /**
   * Main method for the program, calls startApplication which starts the whole program
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Utility.startApplication();
  }

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed into
    // the input argument parameter
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color
    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width
    // [resp. height] of the display window.
    tigers = new Tiger[8];
    randGen = new Random();

  }

  /**
   * Continuously updates the screen, specifically drawing the tigers and reloading the background
   * images
   */
  public static void update() {
    processing.background(245, 255, 250);
    backgroundImage = processing.loadImage("images/background.png");
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // Draw the tigers
    for (int j = 0; j < tigers.length; j++) {
      if (tigers[j] != null) {
        tigers[j].draw();
      }
    }
  }

  /**
   * Checks to see if the mouse is over the tiger
   * 
   * @param tiger represents a reference to an object of type Tiger
   * @return a boolean, true if the mouse if over the tiger, false otherwise
   */
  public static boolean isMouseOver(Tiger tiger) {
    // Check if the click is in the boundaries for the tiger
    if (((tiger.getPositionY() + (tiger.getImage().height / 2)) >= processing.mouseY)
        && (tiger.getPositionY() - (tiger.getImage().height / 2) <= processing.mouseY)) {
      if ((tiger.getPositionX() + (tiger.getImage().width / 2) >= processing.mouseX)
          && (tiger.getPositionX() - (tiger.getImage().width / 2)) <= processing.mouseX) {
        return true;
      }
    }
    return false;
  }

  /**
   * Called when the mouse is pressed down. If the mouse is over a tiger it sets tiger.setDragging
   * to true
   */
  public static void mouseDown() {
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        if (isMouseOver(tigers[i]) == true) {
          System.out.println("mouse is over");
          tigers[i].setDragging(true);
          break;
        }
      }
    }
  }

  /**
   * Called when the mouse is released. It sets tiger.setDragging to false for all tigers
   */
  public static void mouseUp() {
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        tigers[i].setDragging(false);
      }
    }
  }

  /**
   * Called when a key is pressed, if the key is t or T it adds another tiger at a random position,
   * if the key is r or R it removes the lowest index tiger the mouse is over.
   */
  public static void keyPressed() {
    if (processing.key == 't' || processing.key == 'T') {
      for (int i = 0; i < tigers.length; i++) {
        if (tigers[i] == null) {
          tigers[i] = new Tiger(processing, randGen.nextInt(processing.width),
              randGen.nextInt(processing.height));
          break;
        }
      }
    }
    if (processing.key == 'r' || processing.key == 'R') {
      for (int j = 0; j < tigers.length; j++) {
        if (tigers[j] != null) {
          if (isMouseOver(tigers[j]) == true) {
            tigers[j] = null;
            break;
          }
        }
      }
    }
  }

}
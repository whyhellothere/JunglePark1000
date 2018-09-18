import java.util.Random;

public class JunglePark {

  private static PApplet processing; // PApplet object that represents the graphic 
  // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the 
  // background image
  private static Tiger[] tigers; // array storing the current tigers present 
  // in the Jungle Park

  private static Random randGen; // Generator of random numbers

  public static void main(String[] args) {
    Utility.startApplication();

  }

  /**
   * Defines the initial environment properties of the application
   * @param processingObj represents a reference to the graphical
   *  interface of the application
   */
  public static void setup(PApplet processingObj) {

    processing = processingObj;
    // Set the color used for the background of the Processing window
    

    tigers = new Tiger[8];

    randGen = new Random();
    for(int i=0; i<tigers.length-3; i++) {
      tigers[i] = new Tiger(processing, (float)randGen.nextInt(processing.width), 
          (float)randGen.nextInt(processing.height));
      tigers[i].draw();
    }

  }

  public static void update() {
    processing.background(245, 255, 250); // Mint cream color
    backgroundImage = processing.loadImage("images/background.png");
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    for(int i=0; i<tigers.length; i++) {
      if(tigers[i] != null) {
//        System.out.println(mouseOver(tigers[i]));
        tigers[i].draw();
      }
    }
  }

  public static boolean mouseOver(Tiger tiger) {
    PImage tigerImage = processing.loadImage("images/tiger.png");
    if((processing.mouseX < tiger.getPositionX() + tigerImage.width/2 && 
        processing.mouseX > tiger.getPositionX() - tigerImage.width/2) && 
        (processing.mouseY < tiger.getPositionY() + tigerImage.height/2 && 
            processing.mouseY > tiger.getPositionY() - tigerImage.height/2)) {
      return true;
    } else {
      return false;
    }
  }

  public static void mouseDown() {
    for(int i=0; i<tigers.length; i++) {
      if(tigers[i] != null) {
        if(mouseOver(tigers[i]) && tigers[i].isDragging() == false) {
          tigers[i].setDragging(true);
          return;
        }
      }
    }
  }

  public static void mouseUp() {
    for(int i=0; i<tigers.length; i++) {
      if(tigers[i] != null) {
        tigers[i].setDragging(false);
      }
    }
  }

  public static void keyPressed() {
    if(processing.key == 't') {
      System.out.println("t");
      for(int i=0; i<tigers.length; i++) {
        if(tigers[i] == null) {
          tigers[i] = new Tiger(processing, processing.mouseX, processing.mouseY);
          return;
        }
      }
    } else if(processing.key == 'r') {
      for(int i=0; i<tigers.length; i++) {
        System.out.println(i);
        if(tigers[i] != null && mouseOver(tigers[i])) {
          tigers[i] = null;
        }
      }
    }
  }



}

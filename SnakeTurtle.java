import ch.aplu.turtle.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
class SnakeTurtle extends Turtle implements KeyListener{

   static {
      DEFAULT_FRAMES_PER_SECOND = 10;
   }
   
   SnakeTurtle(){
      super();
      JPanel frame = this.getPlayground();
      frame.setFocusable(true);
      frame.requestFocus();
      frame.addKeyListener(this);
   }
   SnakeTurtle(Color color){
      super(color);
   }
   SnakeTurtle(Turtle otherTurtle){
      super(otherTurtle);
   }
   
   SnakeTurtle(Turtle otherTurtle, Color color)
   {
      super(otherTurtle, color);
   }
 
   @Override
   public void keyTyped(KeyEvent e){
   
   }
         long keyPressStartTime = 0;
   @Override
   public void keyPressed(KeyEvent e){
      long elapsedKeyPressTime = System.currentTimeMillis() - keyPressStartTime;
      keyPressStartTime = System.currentTimeMillis();
      int keyCode = e.getKeyCode();
      switch( keyCode ) { 
         case KeyEvent.VK_UP:
            if(this.heading()!=180.0&&elapsedKeyPressTime>200){
               this.heading(0.0);
            }
            break;
         case KeyEvent.VK_DOWN:
            if(this.heading()!=0&&elapsedKeyPressTime>200){
               this.heading(180);
            
            }
            break;
         case KeyEvent.VK_LEFT:
            if(this.heading()!=90&&elapsedKeyPressTime>200){
               this.heading(270);
            
            }
            break;
         case KeyEvent.VK_RIGHT :
            if(this.heading()!=270&&elapsedKeyPressTime>200){
               this.heading(90);
            }
            break;
         
      }
   }
   @Override
   public void keyReleased(KeyEvent e){
   }

   //was going to use this method, but grids don't work in turtles since the eraser turtle will just erase all the grid lines...
  /* void drawGrid(){
      for(int i=0;i<=20;i++){
      
         this.speed(999999999).setPos(i*20-200,-200);
         this.fd(400);
      }
      this.rt(90);
      for(int i=0;i<=20;i++){
      
         this.setPos(-200,i*20-200);
         this.fd(400);
      }
   
   }*/
   boolean gameOver(){
      if(this.getX()>200||this.getX()<-200||this.getY()>200||this.getY()<-200)
         return true;
      else
         return false;
   }
   
   void turtlePrep(){
      this.penWidth(20).speed(100);
   }
   void moveTurtle(){
      this.fd(20);
   }
   
   boolean hasTurtleMoved(double x, double y){
      if(x!=this.getX()||y!=this.getY()){
         return true;
      }
      else 
         return false;
   }
  
   int directionTurtleMoved(){
      if(this.heading()==90)
         return 90;
      else if(this.heading()==270)
         return 270;
         
      else if(this.heading()==180)
         return 180;
        
      else
         return 0;
   }
   SnakeTurtle setRandomX(){
      Random rand=new Random();
      super.setX((rand.nextInt(19)-9.5)*20);
      return this;
   }
   SnakeTurtle setRandomY(){
      Random rand=new Random();
      super.setY((rand.nextInt(19)-9.5)*20);
      return this;
   }
}

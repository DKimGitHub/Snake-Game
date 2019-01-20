import ch.aplu.turtle.*;
import java.awt.Color;
import java.util.*;

class SnakeGame implements Runnable{
       
   static int[][] board= new int[22][22];
   static int[] moveHistory= new int[99999];
   static double curSnakeX,curSnakeY,curTailX,curTailY;
   static int points=0;
   static SnakeTurtle st = new SnakeTurtle();
   static SnakeTurtle apple = new SnakeTurtle(st, Color.red);
   
      //asfafkjfkljfkljsfklsdf
   public static void main(String [] args) throws InterruptedException{   
      SnakeGame worker = new SnakeGame();   
      Thread thread = new Thread(worker); 
      thread.start();
      apple.setRandomX();
      apple.setRandomY();
      st.setPos(10,-10).pu().penWidth(20).speed(100).ht();
      int count=0;
      while(!st.gameOver()){
      
         st.moveTurtle();
         st.pd();
         if(checkIfAteItself()){
         break;
         }
         curSnakeX=st.getX();
         curSnakeY=st.getY();
         fillCurrentSnakeLocation(curSnakeX, curSnakeY);
         moveHistory[count]=(st.directionTurtleMoved());  
         count++;
         if(checkIfAteApple(st,apple)){
            apple.setRandomX();
            apple.setRandomY();
         }
      }
      System.out.println("GG YOU DIED! \nCONGRATULATIONS YOU GOT POINTS:" + points);   
   }
   
   public void run(){
      SnakeTurtle eraser= new SnakeTurtle(st);
      eraser.ht();
      int counter=0;
      try{
         Thread.sleep(600);
      }
      catch(InterruptedException e){
         System.out.println("main thread interrupted");
      }
      eraser.turtlePrep();
      eraser.setPos(10,10).penErase();
      while(!st.gameOver()){
         curTailX=eraser.getX();
         curTailY=eraser.getY();
         removePastSnakeLocation(curTailX, curTailY);
         if(st.hasTurtleMoved(curSnakeX,curSnakeY)){
            if(moveHistory[counter]==90){
               eraser.setH(90);
            }
            else if(moveHistory[counter]==270){
               eraser.setH(270);
            
            }
            else if(moveHistory[counter]==180){
               eraser.setH(180);
            
            }
            else {
               eraser.setH(0);
            
            }
            eraser.moveTurtle();
                     curTailX=eraser.getX();
         curTailY=eraser.getY();
         removePastSnakeLocation(curTailX, curTailY);
            counter++;
         }
        
         if(checkIfAteApple(st,apple)){
            points+=100;
            try{
               Thread.sleep(800);
            }
            catch(InterruptedException e){
               System.out.println("main thread interrupted");
            }      
         }
      }
   }

   static boolean checkIfAteApple(SnakeTurtle t1, Turtle t2){
      if (Math.abs(t2.getX()-t1.getX())<20 && Math.abs(t2.getY()-t1.getY())<20){
         return true;  
      } 
      else
         return false;
   } 
   
   static void removePastSnakeLocation(double x, double y){
      board[(210-(int)y)/20 ][((int)x+210)/20 ]=0;
   }
   static void fillCurrentSnakeLocation(double x, double y){
      board[(210-(int)y)/20 ][((int)x+210)/20 ]=1;
   }
  static boolean checkIfAteItself(){
     if(board[(210-(int)st.getY())/20 ][((int)st.getX()+210)/20 ]==1)
         return true;
      else     
        return false;
 
}
}



package fr.umlv.galaxir.testJordane;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fr.umlv.remix.Application;
import fr.umlv.remix.ApplicationRunnable;
import fr.umlv.remix.Arena;
import fr.umlv.remix.KeyHandler;
import fr.umlv.remix.KeyPress;
import fr.umlv.remix.MouseHandler;

public class MyApplicationRunnable implements ApplicationRunnable<TestItem> {
	/*
     * This will be the instructions launch by the run method of Application
     * after creating the Arena arg0
     */
    @Override
    public void run(final Arena<TestItem> arg0,
        final Collection<? extends TestItem> arg1) {

      /*
       * This is our MouseHandler that will be called by the Arena in case of
       * mouse events
       */
      MouseHandler<TestItem> mouseHandler = new MouseHandler<TestItem>() {
        ArrayList<TestItem> dragList;
        /*
         * in case of a mouse click we swap the color of the corresponding
         * TestItems
         */
        @Override
        public void mouseClicked(ArrayList<TestItem> arg0,
            KeyPress arg1) {
          System.out.println("Select " + arg0);
          for (TestItem testItem : arg0) {
            testItem.swap();
          }
        }        
        
        /*
         * in case of mouse wheel move, we just print the set of TestItems
         * covered by the mouse when it appears, the key that was eventually
         * pressed among CTL, SHIFT, ALT-GR and the direction of the wheel
         * move (-1 or +1).
         */
        @Override
        public void mouseWheelMoved(ArrayList<TestItem> arg0,
            KeyPress arg1, int arg2) {
          System.out.println(arg0 + " using " + arg1.toString()
              + " wheel rotate " + arg2);
        }

        @Override
        public void mouseDrag(ArrayList<TestItem> itemsDrag,
            KeyPress key) {
             dragList=itemsDrag;
            System.out.println("Drag :"+dragList);
          
        }

        @Override
        public void mouseDragging(ArrayList<TestItem> itemsDragging,
            KeyPress key) {
            if(!itemsDragging.isEmpty())
              System.out.println("Dragging :"+itemsDragging);
          
        }

        @Override
        public void mouseDrop(ArrayList<TestItem> itemsDrop, KeyPress key) {
          System.out.println("Drag& Drop :"+dragList+" => "+itemsDrop + " using "+key.toString());            
        }

        @Override
        public void mouseOver(ArrayList<TestItem> itemsOver, KeyPress key) {
          if(!itemsOver.isEmpty())
            System.out.println("Over :"+itemsOver);
        }
      };

      /*
       * We build the graphical interface by adding the graphical component
       * corresponding to the Arena - by calling createComponent - to a
       * JFrame.
       */
      final JFrame frame = new JFrame("Test Arena");

      /*
       * This is our KeyHandler that will be called by the Arena in case of
       * key events
       */
      final KeyHandler keyHandler = new KeyHandler() {

        @Override
        public void keyPressed(char arg0) {
          // do nothing
        }

        @Override
        public void keyReleased(char arg0) {
          // do nothing
        }

        @Override
        public void keyTyped(char arg0) {            
          switch (arg0) {
          case '+':
            System.out.println("+ has been typed");
            break;
          case '-':
            System.out.println("- has been typed");
            break;
          default:
            //do nothing
            break;
          }
        }

        @Override
        public JFrame getParentFrame() {
          return frame;
        }

      };

      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.add(arg0.createComponent(300, 200, mouseHandler, keyHandler));
      frame.pack();
      frame.setVisible(true);

      /*
       * We initially draw the component
       */
      arg0.refresh();

      /*
       * We ask the Application to call the following run function every
       * seconds. This method just refresh the component.
       */
      Application.timer(1000, new MyTimerRunnable(arg0));
    }
  }
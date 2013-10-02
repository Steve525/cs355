/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.mvc.main;

import java.awt.Color;

import cs355.CS355Controller;
import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.mvc.controller.CS355ControllerImpl;
import cs355.mvc.controller.MyListener;
import cs355.mvc.view.ViewRefresherImpl;

/**
 *
 * @author <Put your name here>
 */
public class CS355 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    	CS355Controller controller = CS355ControllerImpl.getInstance();	// singleton
    	ViewRefresher viewRefresher = new ViewRefresherImpl();
    	MyListener customListener = new MyListener();
        GUIFunctions.createCS355Frame(controller, viewRefresher, customListener , customListener);
        GUIFunctions.refresh();
        GUIFunctions.changeSelectedColor(Color.WHITE);
        GUIFunctions.setHScrollBarMin(0);
        GUIFunctions.setVScrollBarMin(0);
        GUIFunctions.setHScrollBarMax(512);
        GUIFunctions.setVScrollBarMax(512);
        GUIFunctions.setHScrollBarKnob(256);
        GUIFunctions.setVScrollBarKnob(256);
    }
}
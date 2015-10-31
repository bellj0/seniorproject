/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.ui;

import javax.swing.JFrame;

/**
 *
 * @author Stephen
 */
public class FrameHandler 
{
    private static JFrame listener;
	
    public static void launchFrame(final JFrame frame) {
            if (listener != null) {
                    if (listener.getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE)
                            listener.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    listener.dispose();
            }
            listener = frame;
    }

    public static JFrame getListener() {
            return listener;
    }

}

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
            listener = frame;
    }

    public static JFrame getListener() {
            return listener;
    }
    
    public static void exit() {
        listener.dispose();
    }

}

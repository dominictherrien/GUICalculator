import java.awt.*;
import javax.swing.*;

public class Calculator {

	public static void main(String[] args) {
		CalculatorSplashScreen splash = new CalculatorSplashScreen(5000);
		splash.showSplashWindow();
		EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            JFrame frame = new JFrame();
	            frame.setTitle("Calculator");
	            frame.setMinimumSize(new Dimension(276, 460));
	            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	            frame.setContentPane(new CalculatorView());
	            frame.setLocationByPlatform(true);
	            frame.setVisible(true);
	        }
	    });

	}

}

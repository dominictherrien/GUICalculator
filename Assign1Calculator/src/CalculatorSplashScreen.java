import javax.swing.*;
import java.awt.*;

public class CalculatorSplashScreen extends JWindow{
	
	private static final long serialVersionUID = 6248477390124803341L;
	private int duration;

	public CalculatorSplashScreen(int duration){
		this.duration = duration;
	}

	public void showSplashWindow(){
		JPanel content = new JPanel(new BorderLayout());
		content.setBackground(Color.WHITE);
		int width =  384;
		int height = 274+20;
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (scr.width-width)/2;
		int y = (scr.height-height)/2;
		setBounds(x,y,width,height);
		JLabel label = new JLabel(new ImageIcon("SPLASH.gif"));
		content.add(label, BorderLayout.NORTH);
		JLabel name = new JLabel("Dominic Therrien - 040777119");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		content.add(name, BorderLayout.SOUTH);
		Container contentPane = getContentPane();
		contentPane.add(content);
		setContentPane(content);
		setVisible(true);
		try {
			Thread.sleep(duration); }
		catch (Exception e) {
			e.printStackTrace();
			}
		dispose(); 
	}


}

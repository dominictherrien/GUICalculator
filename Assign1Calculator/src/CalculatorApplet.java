import javax.swing.*;
import java.awt.*;
public class CalculatorApplet extends JApplet{
	@Override
	public void init(){
		super.init();
		this.rootPane.setContentPane(new CalculatorView());
	}
}

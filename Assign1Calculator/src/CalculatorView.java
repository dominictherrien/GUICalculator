import javax.swing.*;
import java.awt.color.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorView extends JPanel{
	private CalculatorModel model = new CalculatorModel();
	private JTextField display;
	private JLabel error;
	private JButton dotButton;
	
	public CalculatorView(){
		OperationController opControl = new OperationController();
		NumericController numControl = new NumericController();
		PrecisionController preControl = new PrecisionController();
		JPanel top = new JPanel(new FlowLayout());
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createMatteBorder(5,5,5,5, new Color(0,0,0)));
		error = new JLabel("F");
		error.setPreferredSize(new Dimension(25,25));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setBackground(new Color(255,255,0));
		error.setOpaque(true);
		top.add(error);
		
		display = new JTextField(16);
		display.setEditable(false);
		display.setBackground(new Color(255,255,255));
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		top.add(display);
		
		JButton backspace = new JButton("\u21D0");
		backspace.setActionCommand("\u21D0");
		backspace.addActionListener(new BackspaceController());
		backspace.setToolTipText("Backspace (Alt-B)");
		backspace.setBackground(new Color(0,0,0,0));
		backspace.setPreferredSize(new Dimension(25,25));
		backspace.setOpaque(false);
		backspace.setForeground(new Color(255,0,0));
		backspace.setBorder(BorderFactory.createLineBorder(new Color(255,0,0), 1));
		top.add(backspace);
		
		top.setBackground(new Color(255,255,255));
		top.setSize(400, 300);
		top.setVisible(true);
		
		
		JPanel checks = new JPanel(new FlowLayout());
		
		
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.LINE_AXIS));
		JRadioButton one = new JRadioButton(".0");
		one.setBackground(new Color(255,255,0));
		one.setActionCommand("1");
		one.addActionListener(preControl);
		JRadioButton two = new JRadioButton(".00");
		two.setActionCommand("0");
		two.addActionListener(preControl);
		two.setBackground(new Color(255,255,0));
		JRadioButton three = new JRadioButton("Sci");
		three.setActionCommand("2");
		three.addActionListener(preControl);
		three.setBackground(new Color(255,255,0));
		ButtonGroup radioButtons = new ButtonGroup();
		radioButtons.add(one);
		radioButtons.add(two);
		radioButtons.add(three);
		box.add(one);
		box.add(two);
		box.add(three);
		box.setVisible(true);
		
		JCheckBox check = new JCheckBox("Int");
		check.setActionCommand("Int");
		check.addActionListener(preControl);
		check.setBackground(new Color(0,255,0));
		
		checks.add(check);
		checks.add(box);
		checks.setBackground(new Color(0,0,0));
		checks.setVisible(true);
		
		JPanel topHalf = new JPanel(new BorderLayout());
		topHalf.add(top, BorderLayout.NORTH);
		topHalf.add(checks, BorderLayout.SOUTH);
		add(topHalf, BorderLayout.NORTH);
		
		JPanel keys = new JPanel(new BorderLayout());
		
		JPanel cAndE = new JPanel(new GridLayout());
		JButton c = createButton("C", "C", new Color(0,0,0), new Color(255,0,0), opControl);
		c.setFont(new Font(Font.SANS_SERIF,Font.BOLD,21));
		c.setBackground(new Color(255,0,0));
		c.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(255,255,255)));
		JButton e = createButton("=", "=", new Color(0,0,0), new Color(255,255,0), opControl);
		e.setFont(new Font(Font.SANS_SERIF,Font.BOLD,21));
		e.setBackground(new Color(255,255,0));
		e.setBorder(BorderFactory.createMatteBorder(0,1,0,0, new Color(255,255,255)));
		cAndE.add(c);
		cAndE.add(e);
		cAndE.setVisible(true);
		
		keys.add(cAndE, BorderLayout.NORTH);
		
		
		JPanel numbers = new JPanel(new GridLayout(0, 4));
		
		String[] arr={"7", "8" , "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "\u2213", "0", "\u2022", "+"};
		
		for (int i = 0; i < 16; i++){
			JButton num;
			if((i+1)%4!=0)
				num = createButton(arr[i], arr[i], new Color(0,0,0), new Color(0,0,255), numControl);
			else
				num = createButton(arr[i], arr[i], new Color(255,255,0), new Color(0,0,255), opControl);
			num.setBorder(BorderFactory.createLineBorder(new Color(255,255,255), 1, true));
			num.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 21));
			if (i!=14)
				numbers.add(num);
			else{
				dotButton = num;
				numbers.add(dotButton);
			}
		}
		keys.add(numbers,BorderLayout.CENTER);
		
		JPanel eAndC = new JPanel(new GridLayout());
		JButton c2 = createButton("C", "C", new Color(0,0,0), new Color(255,0,0), opControl);
		c2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,21));
		c2.setBackground(new Color(255,0,0));
		c2.setBorder(BorderFactory.createMatteBorder(0,1,0,0, new Color(255,255,255)));
		JButton e2 = createButton("=", "=", new Color(0,0,0), new Color(255,255,0), opControl);
		e2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,21));
		e2.setBackground(new Color(255,255,0));
		e2.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(255,255,255)));
		eAndC.add(e2);
		eAndC.add(c2);
		eAndC.setVisible(true);
		
		keys.add(eAndC, BorderLayout.SOUTH);
		
		keys.setVisible(true);
		add(keys,BorderLayout.CENTER);
		
		
		
	}
	
	private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler){
		JButton newButton = new JButton(text);
		newButton.setBackground(bg);
		newButton.setForeground(fg);
		if (ac!=null)
			newButton.setActionCommand(ac);
		newButton.setFont(new Font("default", Font.PLAIN, 21));
		newButton.addActionListener(handler);
		return newButton;
	}
	
	/**
	 * The Controller that handles the precision mode buttons including the Int button.
	 * Tells the Model which precision mode to use.
	 * 
	 * @author Dominic Therrien
	 *
	 */
	private class PrecisionController implements ActionListener{
		/**/
		boolean integerMode = false;
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().charAt(0)=='I'){
				if (!integerMode){
					dotButton.setEnabled(false);
					dotButton.setBackground(Color.GRAY);
					integerMode=true;
					if (display.getText().length()!=0)
						if (display.getText().charAt(0)!='-')
							display.setText(""+(int)Float.parseFloat(display.getText()));
				}
				else{
					dotButton.setEnabled(true);
					dotButton.setBackground(Color.BLUE);
					integerMode=false;
				}
				model.switchOpMode();
			}
			else
				model.setPrecision(e.getActionCommand().charAt(0));
		}
	}
	
	/**
	 * The Controller that handles the numerical buttons. Is responsible for telling the View
	 * to add a digit to its display field, and handles the submission of operands to the Model.
	 * 
	 * @author Dominic Therrien
	 *
	 */
	private class NumericController implements ActionListener{
		
		/*Indicates whether or not the dot button has been pushed during the entry of the current operand*/
		boolean dotEnabled = true;
		
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().charAt(0)=='\u2213')
				display.setText(""+ -1 * Float.parseFloat(display.getText()));
			else {
				if (model.getLastPressed()=='+' || model.getLastPressed()=='-' || model.getLastPressed()=='*' || model.getLastPressed()=='/'){
					dotEnabled=true;
					if (model.getLastPressed()=='='){
						model.setLastPressed('C');
						model.setOperation("");
					}
					else
					model.setOperand(display.getText()+"r"+model.getLastPressed()+"r");
					display.setText("");
					display.setText(display.getText()+e.getActionCommand());
					model.setLastPressed(e.getActionCommand().charAt(0));
				}
				else if (e.getActionCommand().charAt(0)=='\u2022'){
					if (dotEnabled)
						display.setText(display.getText()+".");
					model.setLastPressed('.');
					dotEnabled=false;
				}
			
				else{
					display.setText(display.getText()+e.getActionCommand());
					model.setLastPressed(e.getActionCommand().charAt(0));
				}
			}
		}
		
	}
	
	/**
	 * The Controller that handles all operational buttons. Is capable of telling the
	 * model to process the operation, clear the operation, reset error state, and
	 * manipulates the View to indicate errors.
	 * 
	 * @author Dominic Therrien
	 *
	 */
	private class OperationController implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			if (model.getLastPressed()!='C'){
				if (e.getActionCommand().charAt(0)=='=' && model.getLastPressed()!='='){
					model.setOperand(display.getText());
					model.performCalculations();
					if (!model.getErrorState())
						display.setText(model.getResult());
					
					else{
						error.setBackground(Color.red);
						display.setText("--");
					}
				}
				else if (e.getActionCommand().charAt(0)=='C'){
					model.setErrorState(false);
					error.setBackground(Color.yellow);
					model.setOperation("");
					display.setText("");
				}
				model.setLastPressed(e.getActionCommand().charAt(0));
			}
		}
	}
	
	/**
	 * The Controller for the backspace button. Tells the view to remove a character
	 * from the display.
	 * 
	 * @author Dominic Therrien
	 *
	 */
	private class BackspaceController implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (display.getText().length()!=0)
				display.setText(display.getText().substring(0, display.getText().length()-1));
		}
	}
	
	/*
	private class Controller implements ActionListener{
		public void actionPerformed(ActionEvent e){
			display.setText(e.getActionCommand());
		}
	}
	*/
}


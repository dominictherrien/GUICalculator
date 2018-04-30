import java.util.Collections;
import java.util.LinkedList;
/**
 * A class that represents the Model portion of the Model View Controller implementation
 * of the Calculator. Handles all calculations and all other underlying functions not visible
 * to the user. Has support for various precision modes.
 * 
 * @author Dominic Therrien
 *
 */
public class CalculatorModel {

	
	/*A String representation of the operation to be performed*/
	private String operation = "";
	/*The operational mode of the calculator*/
	private boolean opMode;
	/*The precision mode of the calculator. 0 for .00, 1 for .0, 2 for Sci*/
	private char precision;
	/*The result of the performed operation*/
	private float result;
	/*The error state of the calculator. If true, an illegal operation was prevented from performing*/
	private boolean errorState;
	/*The last key pressed on the calculator*/
	private char lastPressed;
	
	/**
	 * The constructor for the object. Initializes all fields to their default state.
	 */
	public CalculatorModel(){
		operation = "";
		opMode = true;
		precision = '0';
		result = 0;
		errorState = false;
		lastPressed = 'C';
		
	}
	
	/**
	 * Tells the model the last non-checkbox key pressed by the user.
	 * 
	 * @param	lastPressed	The last key pressed on the calculator.       
	 */
	public void setLastPressed(char lastPressed){
		this.lastPressed=lastPressed;
	}
	
	/**
	 * Returns the character representation of the last non-checkbox key pressed by the user.
	 * 
	 * @return	The last key pressed on the calculator. 
	 * @see         
	 */
	public char getLastPressed(){
		return lastPressed;
	}
	
	/**
	 * Sets the precision mode of the model.
	 * 
	 * @param	precision	The new precision mode of the model. Either 0 (.00), 1 (.0), or 2 (Sci).
	 */
	public void setPrecision(char precision){
			this.precision=precision;
	}
	
	/** 
	 * Toggles the operational mode of the model between true (float mode) and false (int mode).
	 * 
	 */
	public void switchOpMode(){
		if (opMode)
			opMode = false;
		else
			opMode = true;
	}
	
	/**
	 * Appends an operand to the operation String.
	 * 
	 * @param	operand	A String representing the operand to be appended.             
	 */
	public void setOperand(String operand){
		operation=operation+operand;
		
	}
	
	/**
	 * Sets the operation String.
	 * 
	 * @param	operation	A String representing the new operation.             
	 */
	public void setOperation(String operation){
		this.operation=operation;
	}
	
	/**
	 * Sets the error state of the model.
	 * 
	 * @param	errorState The new error state of the model. True for error, false for otherwise.  
	 */
	public void setErrorState(boolean errorState){
		this.errorState=errorState;
	}
	
	/**
	 * Gets the error state of the model.
	 * 
	 * @return	The error state of the model. True for error, false for otherwise.
	 */
	public boolean getErrorState(){
		return errorState;
	}
	
	/**
	 * Gets the result stored in the model according to the precision and operational modes.
	 * 
	 * @return	A String representation of the result.
	 */
	public String getResult(){
		if (!opMode)
			return String.format("%d", (int)result);
		else if (precision=='0')
			return String.format("%.2f", result);
		else if (precision=='1')
			return String.format("%.1f", result);
		else
			return String.format("%.6e", result);
		
	}
	
	/** 
	 * Performs the String operation and sets the resulting float to result.
	 *
	 */
	public void performCalculations(){
		result=0;
		char operator='+';
		float total = 0;
		String [] operArr = this.operation.split("r");
		for (int i=0; i<operArr.length; i++){
			switch (operArr[i].charAt(0)){
			case '+':
				operator='+';
				break;
			case '-':
				operator='-';
				break;
			case '*':
				operator='*';
				break;
			case '/':
				operator='/';
				break;
			default:
				switch (operator){
				case '+':
					total = total + Float.parseFloat(operArr[i]);
					break;
				case '-':
					total = total - Float.parseFloat(operArr[i]);
					break;
				case '*':
					total = total * Float.parseFloat(operArr[i]);
					break;
				case '/':
					if (Float.parseFloat(operArr[i])==0.0f)
						errorState=true;
					total = total / Float.parseFloat(operArr[i]);
					break;
				}
			}
		}
		result = total;
		this.operation="";
	}
	
	
}

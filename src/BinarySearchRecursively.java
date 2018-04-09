/*
 * Created by: Phoebe Vermithrax
 * Created on: 09-April-2018
 * Created for: ICS4U
 * Daily Assignment – Day #26
 * This program sorts through a randomly generated array, and allows the user input a number to search for. Once it finds the number, it'll output it's index.
*/

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BinarySearchRecursively {

	protected Shell shell;
	private Text txtInput;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BinarySearchRecursively window = new BinarySearchRecursively();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Create the array.
	int[] randomArray;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	//Create a function that will sort the array of random numbers and input them into the sorted array.
	//USed this tutorial: https://www.youtube.com/watch?v=RqfWvIsYmsc
	public void BubbleSort(int[] tmpArray, List tmpList)
	{
		//Create a temporary variable so as to swap numbers in the loop.
		int tempNum;
		
		//create a for loop starting at the top, then going down.
		for (int counter = tmpArray.length - 1; counter > 0; counter--)
		{
			//create another for loop starting from the beginning, and then going up the array.
			for(int secondCount = 0; secondCount < counter; secondCount++)
			{
				//If the array at the secondCount's position is greater than the one after it,
				if(tmpArray[secondCount] > tmpArray[secondCount + 1])
				{
					//Save the first number in the temporary variable,
					tempNum = tmpArray[secondCount];
					
					//replace the first number with the second number. First part of the switch.
					tmpArray[secondCount] = tmpArray[secondCount + 1];
					
					//replace the second number's position with the first number. Second part of the switch.
					tmpArray[secondCount + 1] = tempNum;
				}
			}
		}
		
		//Create another for loop to add the now sorted numbers into a list box.
		for (int count = 0; count < tmpArray.length; count++)
		{
			//Add the numbers to the listbox.
			tmpList.add("" + tmpArray[count]);
		}
	}
	
	//Create a function that will search through the array, and find the number the user inputed.
	//Used this tutorial: https://www.youtube.com/watch?v=-bQ4UzUmWe8
	public int BinarySearch(int[] tmpArray, int value, int start, int end)
	{
		//Calculate the middle of the array, which is the start and the end added together divided by two. Also, set the index to -1.
		int midPoint = (start + end) / 2;
		int index = -1;
		
		//If the beginning is bigger than the ending,
		if (start > end)
		{
			//Then, return the index.
			index = -1;
		}
		
		else {

			//If the array at the midpoint is equal to the value given,
			if (tmpArray[midPoint] == value)
			{
				//Set the index to the midpoint.
				index = midPoint;
			}
			//If the value is less than the midpoint, 
			else if (value < tmpArray[midPoint])
			{
				//Recursively call the function, passing the value, the beginning, and the midpoint to set the index.
				index = BinarySearch(tmpArray, value, start, midPoint - 1);
			}
			//If the value is greater than the midpoint.
			else if (value > tmpArray[midPoint])
			{
				//Recursively call the function, passing the value, the midpoint and the end to the index.
				index = BinarySearch(tmpArray, value, midPoint + 1, end);
			}
		}
		
		//Return the index.
		return index;

	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(468, 517);
		shell.setText("Binary Search Recursively");
		
		txtInput = new Text(shell, SWT.BORDER);
		txtInput.setBounds(10, 403, 228, 21);
		
		Label lblInputANumber = new Label(shell, SWT.NONE);
		lblInputANumber.setBounds(10, 382, 228, 15);
		lblInputANumber.setText("Input a number to see if it's in the array:");
		
		List lstRandom = new List(shell, SWT.BORDER);
		lstRandom.setBounds(10, 85, 211, 291);
		
		List lstSorted = new List(shell, SWT.BORDER);
		lstSorted.setBounds(242, 85, 200, 291);
		
		Label lblRandomNumbers = new Label(shell, SWT.NONE);
		lblRandomNumbers.setBounds(10, 64, 100, 15);
		lblRandomNumbers.setText("Random Numbers:");
		
		Label lblSortedNumbers = new Label(shell, SWT.NONE);
		lblSortedNumbers.setBounds(244, 64, 100, 15);
		lblSortedNumbers.setText("Sorted Numbers:");
		
		Button btnGenerate = new Button(shell, SWT.NONE);
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//Create variables for the max array size, the max range, and others relating to that,
				int MAX_SIZE = 250;
				int MAX_RANDOM = 1000;
				int randomNumber = 0;
				int maxNum;
				
				//set the maxNum of array.
				randomArray = new int[MAX_SIZE];
				
				//Set up the random number generator.
				Random randomNumberGenerator = new Random();
				
				//Create a for loop to populate the array.
				for (int countOne = 0; countOne < randomArray.length; countOne++)
				{
					//Generate a random number.
					randomNumber = randomNumberGenerator.nextInt(MAX_RANDOM + 1);
					
					//Assign the random number to the counter position in the array.
					randomArray[countOne] = randomNumber;
					
					//Add the numbers to a listbox.
					lstRandom.add("" + randomNumber);
				}
				
				//Call the bubble sort function.
				BubbleSort(randomArray, lstSorted);
			}
		});
		btnGenerate.setBounds(151, 33, 129, 25);
		btnGenerate.setText("Generate");
		
		Label lblIndexOfNumber = new Label(shell, SWT.NONE);
		lblIndexOfNumber.setBounds(10, 430, 100, 15);
		lblIndexOfNumber.setText("Index of Number:");
		
		Label lblAnswer = new Label(shell, SWT.NONE);
		lblAnswer.setBounds(116, 430, 55, 15);
		lblAnswer.setText("New Label");
		
		Button btnCheck = new Button(shell, SWT.NONE);
		btnCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				// Create variable to get the user's input.
				int userInput;
				int result;
				
				//Get the data.
				userInput = Integer.parseInt(txtInput.getText());
				
				result = BinarySearch(randomArray, userInput, 0, randomArray.length-1);
				
				lblAnswer.setText("" + result);
				
				//Call the binary search function.
				lblAnswer.setText("" + BinarySearch(randomArray, userInput, 0, randomArray.length-1));
			}
		});
		btnCheck.setBounds(244, 401, 198, 25);
		btnCheck.setText("Check");

	}
}

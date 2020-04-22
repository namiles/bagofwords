package project0;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

/* Collaborated with James Rinehart to get this project completed 
 * 
 * We both did about 50%.
 * 
 */

public class BagOfWords {
    private static String fileName;
    ArrayList<String> baseArray = new ArrayList<String>();
    
	
	//default constructor method
	public BagOfWords() {
        this.fileName = "null";
    }
	
	/* Extra functions */
    
	public ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        File fileOne = new File(fileName);
        Scanner scanner = new Scanner(fileOne); 
        ArrayList<String> contentList = new ArrayList<String>();
        while (scanner.hasNext()) {
        	contentList.add(scanner.next());
        }
        scanner.close();
        return contentList;
    }
	
	
	/* Required Functions */
	
	//expand method - absorbs text into the BoW
	public void expand(String name) throws FileNotFoundException {
		ArrayList<String> inputList = new ArrayList<String>();
		
		//copy words in file being expanded to input array
		inputList = this.readFile(fileName);
		
		//copy words from second file to first file
		this.baseArray.addAll(inputList);
	}
	
	//term frequency method - prints all distinct words and their frequencies
	public void printTermFrequency() {
		
		Map<String, Integer> frequencyMap = new HashMap<>();
		for (String s: this.baseArray) {
			Integer count = frequencyMap.get(s);
			if (count == null) {
				count = 0;
			}
			frequencyMap.put(s, count + 1);
		}

		for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
			System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
		}
	}
	
	//inverse document frequency - Bonus
	public void printInverseDocumentFrequency() {
		
	}
	
	
	/* Main Function */
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Scanner fileInput = new Scanner(System.in);
		
		int selection = 0; //set initial menu selection to 0
        boolean menuOpen = true;
		
		//Create bag
		BagOfWords bag = new BagOfWords();

		System.out.println("Enter a file name to initialize the bag of words: ");
        bag.fileName = fileInput.nextLine();
        
        try {
            bag.baseArray = bag.readFile(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(); //spacing

        while(menuOpen) {
        	System.out.println("Please select an option from below:");
            System.out.println("1. Expand");
            System.out.println("2. Print term frequencies");
            System.out.println("3. Print inverse document frequencies");
            System.out.println("4. Exit");
            System.out.println(); //spacing
            selection = input.nextInt();
            
            System.out.println(); //spacing

            switch(selection) {
            
            	//expand
                case 1:
                	System.out.println("Enter the file name of the file you want to absorb into the bag: ");
                    bag.fileName = fileInput.nextLine();
                    
            		try {
            			bag.expand(fileName);
            		} catch (FileNotFoundException e) {
            			e.printStackTrace();
            		}
            		
            		System.out.println("The file has been absorbed into the bag."); //spacing

                    System.out.println(); //spacing
                    
                    break;
                    
                //print term frequencies
                case 2:
                	bag.printTermFrequency();
                	System.out.println(); //spacing
                    break;
                    
                case 3:
                    System.out.println("This function has not been implemented. :(");
                    System.out.println(); //spacing
                    break;
                    
                case 4:
                	System.out.println("Exiting program..");
                    menuOpen = false;
                    break;
             }
        }
        
        input.close();
        fileInput.close();
	}
}

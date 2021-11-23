package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashedDictionaryTest {

	public static void main(String[] args) {

		System.out.println("================================");
		System.out.println("     HashedDictionary Test");
		System.out.println("================================");
		System.out.println("");
		System.out.println("");
		
		// Create a HashedDictionary using LastNameInfo		
		System.out.println("--------------------------------");
		System.out.println("Test 1: instantiation");
		System.out.println("");
		DictionaryInterface<String,NameInterface> table = null;
		try {
			table = new HashedDictionary<>();
			System.out.printf("isEmpty:  %s, ", table.isEmpty()?"true":"false");
			if (table.isEmpty()) {
				System.out.println("correct");
			} else {
				System.out.println("incorrect");
			}
			
		} catch (Exception e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		System.out.println("");

		System.out.println("--------------------------------");
		System.out.println("Test 2: add");
		System.out.println("");
		
		// Add all items from the census file to the dictionary
		File file = new File("LastNames2000Census.txt");
		Scanner data;
		try {
			data = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to load the file.");
			return;
		}
		
		try {
			// read information from the file
			System.out.println("adding names ...");
			while (data.hasNextLine()) {
				NameInterface info = createFromString(data.nextLine());
				table.add(info.getName().toLowerCase(), info);
			}
			System.out.println("adding names finished");
			System.out.println("");
			// check the number found
			System.out.printf("getSize: %d, ", table.getSize());
			if (table.getSize() == 151671) {
				System.out.println("correct");
			} else {
				System.out.println("incorrect");
			}
		} catch (Exception e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		System.out.println("");
		
		// close the file after reading
		data.close();
		System.out.println("");
		
		System.out.println("--------------------------------");
		System.out.println("Test 3: table.contains(\"jones\")");
		System.out.println("");
		try {
			System.out.print("table.contains(\"jones\") = " + table.contains("jones") + ", ");
			if (table.contains("jones")) {
				System.out.println("correct");
			} else {
				System.out.println("incorrect");
			}
		} catch (Exception e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		System.out.println("");
		
		System.out.println("--------------------------------");
		System.out.println("Test 4: table.getValue(\"jones\")");
		System.out.println("");
		try {
			NameInterface jones = table.getValue("jones");
			System.out.print("jones.getRank() = " + jones.getRank() + ", ");
			if (jones.getRank() == 5) {
				System.out.println("correct");
			} else {
				System.out.println("incorrect");
			}

		} catch (Exception e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		System.out.println("");
		
		System.out.println("--------------------------------");
		System.out.println("Test 5: remove");
		System.out.println("");
		try {
			NameInterface jones = table.remove("jones");
			System.out.print("jones.getRank() = " + jones.getRank() + ", ");
			if (jones.getRank() == 5) {
				System.out.println("correct");
			} else {
				System.out.println("incorrect");
			}
			// check the number found
			System.out.printf("getSize: %d, ", table.getSize());
			if (table.getSize() == 151670) {
				System.out.println("correct");
			} else {
				System.out.println("incorrect");
			}
		} catch (Exception e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		System.out.println("");
		
		System.out.println("--------------------------------");
		System.out.println("Test 6: table.getValue(\"divelbiss\")");
		System.out.println("");
		try {
			NameInterface divelbiss = table.getValue("divelbiss");
			System.out.print("divelbiss.getRank() = " + divelbiss.getRank() + ", ");
			if (divelbiss.getRank() == 35721) {
				System.out.println("correct");
			} else {
				System.out.println("incorrect");
			}
		} catch (Exception e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		System.out.println("");
		
			
		System.out.println("--------------------------------");
		System.out.println("Test 7: clear");
		System.out.println("");
		try {
			table.clear();
			System.out.printf("isEmpty:  %s, ", table.isEmpty()?"true":"false");
			if (table.isEmpty()) {
				System.out.println("correct");
			} else {
				System.out.println("incorrect");
			}
		} catch (Exception e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		System.out.println("");		
		
	}
	
	public static <K,V> void displayDictionaryInfo(DictionaryInterface<K,V> dic) {
		System.out.println("Dictionary stats:");
		System.out.printf("  isEmpty:         %s\n", dic.isEmpty()?"true":"false");
		System.out.printf("  getSize:         %d\n", dic.getSize());
		System.out.println("");
	}
	
	
	/**
	 * Static factory method to create a new NameInfo object from the given 
	 * comma separated value string.
	 * 
	 * @param csv
	 * @return
	 */
	public static NameInterface createFromString(String csv) {
		NameInterface info = new NameInfo();
		String[] tokens = csv.trim().split(",");
		if (tokens.length>0) {
			info.setName(tokens[0].toLowerCase().trim());
		}
		if (tokens.length>1) {
			info.setRank(Integer.parseInt(tokens[1]));
		}
		if (tokens.length>2) {
			info.setFrequency(Integer.parseInt(tokens[2]));
		}
		if (tokens.length>3) {
			info.setProportion(Double.parseDouble(tokens[3]));
		}
		if (tokens.length>4) {
			info.setCumulativeProportion(Double.parseDouble(tokens[4]));
		}
		return info;
	}

	

}

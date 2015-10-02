/* Rungkiart Pete Thongsri
 * CS 210
 * PROGRAMMING ASSIGNMENT 4
 * Spring 2015
 * 
 * The purpose of this assignment is to 
 * create programs which will improve it's
 * performance with experience.
 */

import java.io.*;
import java.util.Scanner;

public class NearestNeighbor {

	public static void main(String[] args) throws IOException {
		//Creating the initial arrays
		double trainingValues [][] = new double [75][4];
		double testingValues [][] = new double [75][4];
		String trainingLabels [] = new String [75];
		String testingLabels [] = new String [75];
		String machine [] = new String[75];

		//The following two methods will accept the information from the two excel files
		trainingAccepter(trainingValues,trainingLabels);
		testingAccepter(testingValues,testingLabels);

		//The following will find the closest flower using the by comparing each testing value with all the training ones.
		distanceFinder(trainingValues,testingValues,trainingLabels, machine);

		//The following will calculate the score and print out the results.
		compare(machine,testingLabels);

	}

	//The following method will read the files from the training example.
	public static void trainingAccepter(double[][] training , String trainingLabels []) throws IOException{
		File testingfile = new File("iris-training-data.csv");
		Scanner testScan = new Scanner(testingfile);

		testScan.useDelimiter(",");

		for (int columns = 0; columns < training.length ; columns++){
			for (int rows = 0 ; rows < training[columns].length ; rows++){
				training [columns][rows] = testScan.nextDouble();
			}
			trainingLabels[columns] = testScan.nextLine();
			//I use the following line to get rid of the comma that appears when i used the method i used.
			trainingLabels[columns] = trainingLabels[columns].substring(1,trainingLabels[columns].length());
		}
		testScan.close();
	}

	//The following method will read the files from the testing example.
	public static void testingAccepter(double[][]  testing, String testingLabels []) throws IOException{
		File testingfile = new File("iris-testing-data.csv");
		Scanner testScan = new Scanner(testingfile);

		testScan.useDelimiter(",");

		for (int columns = 0; columns < testing.length ; columns++){
			for (int rows = 0 ; rows < testing[rows].length ; rows++){
				testing [columns][rows] = testScan.nextDouble();
			}
			testingLabels[columns] = testScan.nextLine();
			//I use the following line to get rid of the comma that appears when i used the method i used.
			testingLabels[columns] = testingLabels[columns].substring(1,testingLabels[columns].length());
		}
		testScan.close();
	}

	//The following will calculate the distance and look for the lowest one.
	public static void distanceFinder(double[][] training , double[][] test, String train[] , String machine[]){
		double distance;
		for (int testingValues = 0 ; testingValues < test.length ; testingValues++){
			//I set closest to some arbitrary value thats likely larger than any distance.
			double closest = 100; 
			for (int trainingValues = 0 ; trainingValues < training.length ; trainingValues++){
				//The following will calculate the distance between flowers.
				distance = Math.sqrt(Math.pow(training[trainingValues][0] - test[testingValues][0], 2.0) 
						+ Math.pow(training[trainingValues][1] - test[testingValues][1], 2.0) 
						+ Math.pow(training[trainingValues][2] - test[testingValues][2], 2.0) 
						+ Math.pow(training[trainingValues][3] - test[testingValues][3], 2.0));
				
				//The following will calculate the smallest value.
				if (distance <= closest){
					closest = distance;
					machine[testingValues] = train[trainingValues];
				}
			}
		}
	}

	//The following method will compare my machine results with the actual true results.
	public static void compare(String machine [] , String test []){
		double count = 0;
		//The following is header information.
		System.out.println("CS 210, Spring 2015\nNAME: Rungkiart Thongsri\nPROGRAMMING ASSIGNMENT 4\n ");
		System.out.println("EX#: TRUE LABEL, PREDICTED LABEL");
		//The following will print each result and compare them.
		for (int counter = 0 ; counter < machine.length ; counter++){
			System.out.println(counter + 1 + ": "  + test[counter] +  " " + machine[counter]);

			if (machine[counter].equalsIgnoreCase(test[counter])){
				count++;
			}
		}
		//The following will print out the accuracy of the results.
		System.out.print("Accuracy: " + count/75);
	}
}

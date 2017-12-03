import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
/*
 * THE RUNNER ASSUMES THAT THE FOLLOWING:
 * ACTUAL FILE HAS FORMAT:
 * 			id,date,storeNum,Item#,UnitSold, OnPromotion
 * PREDICTED FILE HAS FORMAT: (with title row)
 * 			id,unit_sales
 */
public class TestRunner {

	public static void main(String[] args) {
		TestMethods testMethods = new TestMethods();
		HashMap<String, Double> ave_item = new HashMap<String, Double>();
		String actualFile = "actual.txt";
		String forecastFile = "predict_me.txt";
//		Double sse = 0.0;
		try {
			// file reader and buffered for both files
			FileReader fr_actual = new FileReader(actualFile);
			FileReader fr_forecast = new FileReader(forecastFile);
			BufferedReader br_actual = new BufferedReader(fr_actual);
			BufferedReader br_forecast = new BufferedReader(fr_forecast);
			final long startTime = System.currentTimeMillis();// start timer
			String line_forecast = br_forecast.readLine();
			String line_actual;
			if (line_forecast.compareToIgnoreCase("id,unit_sales") != 0) {
				System.out.println("There is no header title ");
				System.exit(0);
			}
			double numerator = 0.0;
			double denumerator = 0.0;
			while ((line_actual = br_actual.readLine()) != null) {
				line_forecast = br_forecast.readLine();
				String[] byWords_actual = line_actual.split(",");//change forecasted value
				String[] byWords_forecast = line_forecast.split(",");
				String actualItemNum = byWords_actual[3];
				Double actual = Double.parseDouble(byWords_actual[4]);//number 4 for cross validation
				Double preticted = Double.parseDouble(byWords_forecast[1]);		
				numerator += testMethods.nwr_num(actualItemNum, preticted, actual);
				denumerator += testMethods.nwr_denum(actualItemNum);
			}
			System.out.print("Normalized Weighted Root Mean Squared Logarithmic Error: ");
			System.out.println(Math.sqrt(numerator/denumerator));
			
			
			//sum of square errors
//			while ((line_actual = br_actual.readLine()) != null) {
//				line_forecast = br_forecast.readLine();
//				String[] byWords_actual = line_actual.split(",");//change forecasted value
//				String[] byWords_forecast = line_forecast.split(",");
//				Double actual = Double.parseDouble(byWords_actual[4]);//number 4 for cross validation
//				Double forecast = Double.parseDouble(byWords_forecast[1]);
//				sse += testMethods.squareError(actual, forecast);///sume square of errors			
//
//			}

//			System.out.println("the error is " + sse);

			final long endTime = System.currentTimeMillis();

			System.out.println("\n\n\nTotal execution time: " + (endTime - startTime));
			// for(Map.Entry m:ave_item.entrySet()){
			// System.out.println(m.getKey()+" "+m.getValue());
			// }
			// System.out.println("the nummber of stores is "+ave_item.size());

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file");
		} catch (IOException ex) {
			System.out.println("Error reading file ");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
/*
 *  * Test Data format:
 *  0	1		  2			  3			 4
 * id, date, storeNumber, itemNumber, onPromotion 
 * 
 * 
 * Train Data format:
 * 0	1		2			3			4			5
 * id, date, storeNumber, itemNumber, unitsSold, onPromotion 
 */

public class RoteLearnerRunner {
	public static RoteLearner roteLearner;
	public static void main(String[] args) {
		trainModule();		
		try {
			/*
			 * Read Test File and use Model to predict value
			 */
			FileReader fr = new FileReader("test.csv");
			BufferedReader br = new BufferedReader(fr);
			//File Writer
			FileWriter fw = new FileWriter("Prediction_Rotatery.txt");
			String ln= br.readLine();//throw first line away
			System.out.println("throwing line"+ln);//do wee need to delete this
			while( (ln = br.readLine()) != null){
				String [] byWords = ln.split(",");
				String store = byWords[2];
				String item = byWords[3];
				String date = byWords[1];
				String id= byWords[0];
				String transaction = store+"-"+ item+"-"+roteLearner.getDate(date);
				fw.append(id+","+roteLearner.guessValue(transaction));
				fw.append("\n");
			}
			br.close();
			fw.close();
			
			/*
			 * Read Test
			 */
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void trainModule(){
		try {
			roteLearner = new RoteLearner ();
			/*
			 * Read Train File and Train Model
			 */
			FileReader fr = new FileReader("train.csv");
			BufferedReader br = new BufferedReader(fr);
			String ln= br.readLine();//throw first line away
			while( (ln = br.readLine()) !=null){
				String [] byWords = ln.split(",");
				String store = byWords[2];
				String item = byWords[3];
				String date = byWords[1];
				double value = Double.parseDouble(byWords[4]);
				roteLearner.insert(store, item, date, value);
			}
			br.close();		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void recordTimeSize(){
		System.out.println("total transactions, time(in miliseconds)");
		int totalTransactions = 0;
		for (int i = 20000; i < 300490000; i*=2) {
			long startTime = System.nanoTime();
			totalTransactions=runerTil(i);
			long endTime = System.nanoTime();
			System.out.println(i+","+totalTransactions+""+ (endTime-startTime)/1000000);		
		}
		countAll();
	}
	
	public static int runerTil(int cap) {
		try {
			RoteLearner roteLearner = new RoteLearner ();
			FileReader fr = new FileReader("train.csv");
			BufferedReader br = new BufferedReader(fr);
			String ln= br.readLine();//trow first line away
			int count=0;
			while( (ln = br.readLine()) !=null){
				String [] byWords = ln.split(",");
				String store = byWords[2];
				String item = byWords[3];
				String date = byWords[1];
				double value = Double.parseDouble(byWords[4]);
				roteLearner.insert(store, item, date, value);
				count++;
				if(count >cap)
					return roteLearner.getTotalTransactions();
//				System.out.println(ln);
			}
			br.close();
			return roteLearner.getTotalTransactions();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;//this is not suppost to happen
	}
	/*
	 * method runs the entire file is separated because it is hard get the exact amount of the value
	 */
	public static void countAll() {
		try {
			System.out.println("here");
			RoteLearner roteLearner = new RoteLearner ();
			FileReader fr = new FileReader("train.csv");
			BufferedReader br = new BufferedReader(fr);
			String ln= br.readLine();//throw first line away
			int count=0;
			long startTime = System.nanoTime();
			while( (ln = br.readLine()) !=null){
				String [] byWords = ln.split(",");
				String store = byWords[2];
				String item = byWords[3];
				String date = byWords[1];
				double value = Double.parseDouble(byWords[4]);
				roteLearner.insert(store, item, date, value);
				count++;
			}
			long endTime = System.nanoTime();
			System.out.println(count+","+ (endTime-startTime)/1000000);
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

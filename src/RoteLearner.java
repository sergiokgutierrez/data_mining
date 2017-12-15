import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
/*
 * 0	1		2			3			4			5
 * id, date, storeNumber, itemNumber, unitsSold, onPromotion 
 */
public class RoteLearner {
	static SimpleDateFormat ft = 
		      new SimpleDateFormat ("yyyy-MM-dd");
	HashMap <String, Double>listOfTransactions;
	String[] days= {"sunday","monday","tuesday","wednesday", "thursday","friday","saturday"};
	public RoteLearner(){
		//constructor
		listOfTransactions  = new HashMap <String, Double>();   
	}
	/*
	 * roteLearner.insert(store, item, date)
	 */	
	public void insert(String store, String item, String date, double value){
//		Transaction newTransaction = new Transaction( store,  item,  getDate(date));
		String newTransaction = store+"-"+ item+"-"+ getDate(date);//store+"-"+ item+"-"+ date
		if(value >0 ) {
			listOfTransactions.put(newTransaction, value);	
		}			
	}
	
	public double guessValue(String key){
		if(listOfTransactions.containsKey(key))
			return listOfTransactions.get(key);
		else
			return 0;
	}
	

	
	public int getDate(String date){
		Date t = null;
	      try {
	          t = ft.parse(date); 
	       } catch (ParseException e) { 
	          System.out.println("Unparseable using " + ft); 
	       }
		return t.getDay();
	}
	public int getTotalTransactions(){
		return listOfTransactions.size();
	}
	
	

}

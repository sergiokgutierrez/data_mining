import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
/*
 * 0	1		2			3			4			5
 * id, date, storeNumber, itemNumber, unitsSold, onPromotion 
 */

public class RoteLearnerRunner {

	
	public static void main(String[] args) {
	try {
			RoteLearner roteLearner = new RoteLearner ();
			FileReader fr = new FileReader("xax");
			BufferedReader br = new BufferedReader(fr);
			String ln;
			//if size is wrong then drop the 
			while( (ln = br.readLine()) !=null){
				String [] byWords = ln.split(",");
				String store = byWords[2];
				String item = byWords[3];
				String date = byWords[1];
				double value = Double.parseDouble(byWords[4]);
				roteLearner.insert(store, item, date, value); 				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}

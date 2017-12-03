import java.util.Date;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateToMilis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String myDate = "2014/10/29 18:10:45";
		//creates a formatter that parses the date in the given format
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date original = null;
//		try {
//			date = sdf.parse(myDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		long timeInMillis = date.getTime();
//		System.out.println(timeInMillis);
		
		try {
			FileReader fw = new FileReader("oil.csv");
			BufferedReader br = new BufferedReader(fw);
			FileWriter fileWriter = new FileWriter("oil_prices.txt");
			Double oilPrice = 93.14;
			String ln = br.readLine();
			while( ( ln=br.readLine()) != null){
				String [] byWords = ln.split(",");
				if(byWords.length > 1){
					oilPrice = Double.parseDouble(byWords[1] );
				}
				try {
					date = sdf.parse(byWords[0]);
					original = original == null? date: original;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(getDay(original, date)+" "+oilPrice);
				fileWriter.append(getDay(original, date)+" "+oilPrice+"\n");
				
				
			}
			
			fileWriter.close();
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static long getDay(Date startDate, Date endDate)
	{
		long diff = endDate.getTime() - startDate.getTime();
		return diff/(1000*60*60*24);
	}
	

}

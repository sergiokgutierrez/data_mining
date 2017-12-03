import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestMethods {
	public HashMap<String, Boolean>items;
	String itemsFile = "items.csv";
    public  TestMethods(){
    	items = new HashMap<String, Boolean>();
    	
    	
    }
    public void readItemsFile(){
    	try {
			FileReader fr = new FileReader(itemsFile);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while((line = br.readLine()) != null){
				String[]byWords = line.split(",");
				String key = byWords[0];
				Boolean value;
				if(byWords[3].equalsIgnoreCase("0") || byWords[3].equalsIgnoreCase("1")){
					value=byWords[3].equalsIgnoreCase("1");					
				}else
					value=byWords[5].equalsIgnoreCase("1");
				items.put(key, value);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	  for(Map.Entry m:items.entrySet()){  
//    		   System.out.println(m.getKey()+" "+m.getValue());  
//    		  } 
    	
    }
    public double nwr_num(String itemNum, double preticted, double actual){
    	double x = ln(preticted +1);
    	double y = ln(actual +2);
    	return weight(itemNum)*square(x-y);
    }
    public double nwr_denum(String itenNum){
    	return weight(itenNum);
    }
    /*
     * 
     * return the weight of an item number
     */
    public double weight(String itemNumber){
    	if(items.containsKey(itemNumber)){
    		Boolean isItPershible = items.get(itemNumber);
    		if(isItPershible)
    			return 1.25;
    		else return 1.00;
    	}
    	System.out.println("key not found on the hashmap of items");	
    	return 1.00;
    }
    public double ln(double x){
    	double num = Math.log(x);
    	double denum = Math.log(Math.E);
    	return num/denum;
    }
    public double square(double x){
    	return x*x;
    }
    /*
     * Sum of Square Errors
     */


    public double squareError( double actual, double forecast){
    	double error = actual - forecast;
        return error * error;
    }
    

}
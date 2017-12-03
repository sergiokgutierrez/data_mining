
public class Transaction {
	String store;
	String itemNumber;
	int day;
	boolean onPromotion;
	String[] days= {"sunday","monday","tuesday","wednesday", "thursday","friday","saturday"};
	public Transaction(String store, String item, int day) {
		super();
		this.store = store;
		this.itemNumber = item;
		this.day = day;
	}
	public Transaction(String itemNumber, int day) {
		this.itemNumber = itemNumber;
		this.day = day;
	}
	public boolean isOnPromotion() {
		return onPromotion;
	}
	public void setOnPromotion(boolean onPromotion) {
		this.onPromotion = onPromotion;
	}
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "Store="+store+" itemNumber=" + itemNumber + " day="+days[day];
	}
//	public Boolean equals(Transaction t){
//		if(itemNumber.equals(t.getItemNumber() ))
//				return true;
//		return false;				
//	}
	public Boolean equals(String store, String itemNumber, int day){
		if(this.itemNumber.equals(itemNumber ) && this.day == day && this.store.equalsIgnoreCase(store))
				return true;
		return false;				
	}

}

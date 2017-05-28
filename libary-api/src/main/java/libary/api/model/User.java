package libary.api.model;



public class User {
	
	private int id;
	private String name;
	private String start_of_rental;
	private String end_of_rental;
	private long number_of_days_paid;
	private long pay;


public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart_of_rental() {
		return start_of_rental;
	}

	public void setStart_of_rental(String start_of_rental) {
		this.start_of_rental = start_of_rental;
	}

	public String getEnd_of_rental() {
		return end_of_rental;
	}

	public void setEnd_of_rental(String end_of_rental) {
		this.end_of_rental = end_of_rental;
	}


public User(int id, String name, String start_of_rental, String end_of_rental, long number_of_days_paid, long pay) {
	
		this.id = id;
		this.name = name;
		this.start_of_rental = start_of_rental;
		this.end_of_rental = end_of_rental;
		this.number_of_days_paid =  number_of_days_paid;
		this.pay = pay;
	}

public long getPay() {
	return pay;
}

public void setPay(long pay) {
	this.pay = pay;
}

public long getNumber_of_days_paid() {
	return number_of_days_paid;
}

public void setNumber_of_days_paid(long number_of_days_paid) {
	this.number_of_days_paid = number_of_days_paid;
}



public User(){
	super();
}
}
package spring_mvc.employee_management.model.entity;

public class IntegerData {
	private String dataName;
	private Integer number;
	
	public IntegerData() {
		super();
	}
	
	public IntegerData(String dataName, Integer number) {
		super();
		this.dataName = dataName;
		this.number = number;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "IntegerData [dataName=" + dataName + ", number=" + number + "]";
	}
	
	

}

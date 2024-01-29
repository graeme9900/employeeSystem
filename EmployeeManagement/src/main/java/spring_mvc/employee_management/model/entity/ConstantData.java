package spring_mvc.employee_management.model.entity;

public class ConstantData {
	private String dataName;
	private String constant;
	
	public ConstantData() {
		super();
	}
	
	public ConstantData(String dataName, String constant) {
		super();
		this.dataName = dataName;
		this.constant = constant;
	}
	
	public String getDataName() {
		return dataName;
	}
	
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	public String getConstant() {
		return constant;
	}
	
	public void setConstant(String constant) {
		this.constant = constant;
	}

	@Override
	public String toString() {
		return "ConstantData [dataName=" + dataName + ", constant=" + constant + "]";
	}
	
	
}

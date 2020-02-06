package interview.domain;

public class Client {
	private final String name;
	private final String licenseNumber;
//	private final String cd;//Removed
//	private final List<HireRecord> records = new ArrayList<>(); //Removed

	public Client(String name, String licenseNumber, String cd) {
		this.name = name;
		this.licenseNumber = licenseNumber;
	}

	public String getName() {
		return name;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	@Override
	public String toString() {
		return "Client{" +
				"name='" + name + '\'' +
				", licenseNumber='" + licenseNumber + '\'' +
				'}';
	}
}

package com.vemark.dlparser.domain;

public class PersonData 
{
    private long id;
    private String driversLicense;
    private String firstName;
    private String lastName;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String version;


    public PersonData() 
    {
		
	}

    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getDriversLicense() {
		return driversLicense;
	}
	public void setDriversLicense(String driversLicense) {
		this.driversLicense = driversLicense;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
    public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getMasked(String input, int pos)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Math.min(pos, input.length()); i++) {
        	sb.append(input.substring(i, i+1));
        }
        for(int i = Math.min(pos, input.length()); i < input.length(); i++) {
        	if (input.substring(i, i+1).equalsIgnoreCase(" "))
				sb.append(" ");
			else
				sb.append('*');
        }
        return sb.toString();
    }

	@Override
	public String toString() 
	{
		return String.format("[ver.%s] %s Driver License: %s, %s %s, Address: %s, %s, %s %s", 
				this.version, this.state, getMasked(this.driversLicense,1), 
				getMasked(this.firstName,1), getMasked(this.lastName,1),
				getMasked(this.address,1), getMasked(this.city,1), getMasked(this.state,1), getMasked(this.zip,1));
	}
}

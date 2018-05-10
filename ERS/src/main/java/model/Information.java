package model;

public class Information {
	private int informationid;
	private int employeeid;
	private String firstname;
	private String middlename;
	private String lastname;
	public Information(int informationid, int employeeid, String firstname, 
			String middlename, String lastname) {
		super();
		this.informationid = informationid;
		this.employeeid = employeeid;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
	}
	public Information() {
		super();
		this.informationid = -1;
		this.employeeid = -1;
		this.firstname = "";
		this.middlename = "";
		this.lastname = "";
	}
	public int getInformationid() {
		return informationid;
	}
	public void setInformationid(int informationid) {
		this.informationid = informationid;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeid;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + informationid;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((middlename == null) ? 0 : middlename.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Information other = (Information) obj;
		if (employeeid != other.employeeid)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (informationid != other.informationid)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (middlename == null) {
			if (other.middlename != null)
				return false;
		} else if (!middlename.equals(other.middlename))
			return false;
		return true;
	}
}

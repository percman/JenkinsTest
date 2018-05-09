package com.revature.model;

import java.sql.Timestamp;
import java.util.Date;

public class LodgingReimbursement extends Reimbursement{

	private static final long serialVersionUID = 1545882745333732523L;
	
	private String streetaddress;
	private String city;
	private String state;
	private String international;
	private String locationcontact;
	private Date startdate;
	private Date enddate;
	
	public LodgingReimbursement() {}
	
	public LodgingReimbursement(int id, double amount, int requestor_id, boolean status, String streetaddress,
			String city, String state, String international, String locationcontact, Date startdate, Date enddate) {
		super(id, amount, requestor_id, status);
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.international = international;
		this.locationcontact = locationcontact;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public LodgingReimbursement(int id, double amount, int requestor_id, int approver_id, boolean status,
			Timestamp timemade, Timestamp timeapproved, String reason, String streetaddress,
			String city, String state, String international, String locationcontact, Date startdate, Date enddate) {
		super(id, amount, requestor_id, approver_id, status, timemade, timeapproved, reason);
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.international = international;
		this.locationcontact = locationcontact;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
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

	public String getInternational() {
		return international;
	}

	public void setInternational(String international) {
		this.international = international;
	}

	public String getLocationcontact() {
		return locationcontact;
	}

	public void setLocationcontact(String locationcontact) {
		this.locationcontact = locationcontact;
	}
	
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((enddate == null) ? 0 : enddate.hashCode());
		result = prime * result + ((international == null) ? 0 : international.hashCode());
		result = prime * result + ((locationcontact == null) ? 0 : locationcontact.hashCode());
		result = prime * result + ((startdate == null) ? 0 : startdate.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetaddress == null) ? 0 : streetaddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LodgingReimbursement other = (LodgingReimbursement) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (enddate == null) {
			if (other.enddate != null)
				return false;
		} else if (!enddate.equals(other.enddate))
			return false;
		if (international == null) {
			if (other.international != null)
				return false;
		} else if (!international.equals(other.international))
			return false;
		if (locationcontact == null) {
			if (other.locationcontact != null)
				return false;
		} else if (!locationcontact.equals(other.locationcontact))
			return false;
		if (startdate == null) {
			if (other.startdate != null)
				return false;
		} else if (!startdate.equals(other.startdate))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetaddress == null) {
			if (other.streetaddress != null)
				return false;
		} else if (!streetaddress.equals(other.streetaddress))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return "LodgingReimbursement [streetaddress=" + streetaddress + ", city=" + city + ", state=" + state
				+ ", international=" + international + ", locationcontact=" + locationcontact + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", id=" + id + ", amount=" + amount + ", requestor_id=" + requestor_id
				+ ", approver_id=" + approver_id + ", status=" + status + ", timemade=" + timemade + ", timeapproved="
				+ timeapproved + ", reason=" + reason + "]";
	}

	

	
	
	
	
}

package model;

public class Reimbursement {
	private int reimbursementid;
	private int employeeid;
	private int managerid;
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	private String status;
	private String image;
	private String category;
	
	public Reimbursement(int reimbursementid, int employeeid, int managerid, String status, String image, String category) {
		super();
		this.reimbursementid = reimbursementid;
		this.employeeid = employeeid;
		this.managerid = managerid;
		this.status = status;
		this.image = image;
		this.category = category;
	}
	public int getReimbursementid() {
		return reimbursementid;
	}
	public void setReimbursementid(int reimbursementid) {
		this.reimbursementid = reimbursementid;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbursementid=" + reimbursementid + ", employeeid=" + employeeid + ", status=" + status
				+ ", image=" + image + ", category=" + category + "]";
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getStatus() {
		return status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + employeeid;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + reimbursementid;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (employeeid != other.employeeid)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (reimbursementid != other.reimbursementid)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}

//String[] statuses = {"pending", "approved", "denied"};
//String[] categories = {"lodging", "travel", "food", "other"};

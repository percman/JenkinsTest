package com.revature.model;

public class Reimbursement {
	private int id;
	private int e_id;
	private int fm_id;
	private String category;
	private String status;
	
	/*
	 * Wasnt really sure if I would need a reimbursement class, but after thinking about it realized it would make things so
	 * much easier when it came time to change things in the reimbursement and reimbursement_info table. Note:Check database
	 * and you will know what I am talking about
	 */
	public Reimbursement() {}
	
	public Reimbursement(int id, int e_id) {
		this.id = id;
		this.e_id = e_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public Integer getFm_id() {
		return fm_id;
	}

	public void setFm_id(Integer fm_id) {
		this.fm_id = fm_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + e_id;
		result = prime * result + fm_id;
		result = prime * result + id;
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
		if (e_id != other.e_id)
			return false;
		if (fm_id != other.fm_id)
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", requester id=" + e_id + ", Financial Manager id=" + fm_id + ", category=" + category
				+ ", status=" + status + "]";
	}
	
	
	
	
}

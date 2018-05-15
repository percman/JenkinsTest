package com.revature.model;

import java.io.Serializable;

public class Receipt implements Serializable {

	private static final long serialVersionUID = -7423479758733956167L;
	
	private int receiptId;
	private int reimbursementId;
	private String image;
	
	public Receipt () {}
	
	
	
	public Receipt(String image) {
		super();
		this.image = image;
	}



	public Receipt(int reimbursementId, String image) {
		super();
		this.reimbursementId = reimbursementId;
		this.image = image;
	}



	public Receipt(int receiptId, int reimbursementId, String image) {
		super();
		this.receiptId = receiptId;
		this.reimbursementId = reimbursementId;
		this.image = image;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + receiptId;
		result = prime * result + reimbursementId;
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
		Receipt other = (Receipt) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (receiptId != other.receiptId)
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Receipt [receiptId=" + receiptId + ", reimbursementId=" + reimbursementId + ", image=" + image + "]";
	}
	
	

}

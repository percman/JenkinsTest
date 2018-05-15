package com.revature.image;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class Image implements Serializable {

	private static final long serialVersionUID = 3089775396099839956L;
	private int id;
	private String base64;

	public Image() {}
	
	public Image(int id, Blob blob) {
		this.id = id;
		byte[] encoded;
		try {
			encoded = Base64.getEncoder().encode(blob.getBytes(1, (int)blob.length()));
			base64 = new String(encoded);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base64 == null) ? 0 : base64.hashCode());
		result = prime * result + id;
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
		Image other = (Image) obj;
		if (base64 == null) {
			if (other.base64 != null)
				return false;
		} else if (!base64.equals(other.base64))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", base64=" + base64 + "]";
	}
	
	
}


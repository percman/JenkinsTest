package com.revature.image;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

public class Image {

	Blob blob;

	public Blob getBlob() {
		return blob;
	}

	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	
	public String getBlobBytes() {
		try {
			Arrays.toString(blob.getBytes(1, (int)blob.length()));
					
			byte[] encoded = Base64.getEncoder().encode(blob.getBytes(1, (int)blob.length()));;
			return (new String(encoded));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "Image [blob=" + blob + "]";
	}
	
	
}

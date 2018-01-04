package com.vemark.dlparser.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BarcodeDTO {
	@JsonProperty("BarcodeString")
    private String barcodeString ;

    public BarcodeDTO() 
    {
		
	}
    
	public String getBarcodeString() {
		return barcodeString;
	}

	public void setBarcodeString(String barcodeString) {
		this.barcodeString = barcodeString;
	}

	@Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }    
}

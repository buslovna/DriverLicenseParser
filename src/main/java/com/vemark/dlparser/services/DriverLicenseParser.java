package com.vemark.dlparser.services;

import com.vemark.dlparser.domain.PersonData;

public interface DriverLicenseParser 
{
	PersonData parse(String barcode); 
}

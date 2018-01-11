package com.vemark.dlparser.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vemark.dlparser.domain.PersonData;


@Service
public class DriverLicenseParserImpl implements DriverLicenseParser 
{
	private static final Logger logger = LoggerFactory.getLogger(DriverLicenseParserImpl.class);
	
	@Override
	public PersonData parse(String barcode) 
	{
		logger.debug("Parsing the barcoded string: " + getMasked(barcode));
		PersonData person = null;
		if (barcode != null && !barcode.trim().isEmpty() && barcode.trim().startsWith("@")) {
			
			List<String> basicInfo = findBasicInfo(barcode.trim());
			
			if (basicInfo != null && basicInfo.size() > 7) {
				person = new PersonData();
				person.setLastName		(basicInfo.get(0));
				person.setFirstName		(basicInfo.get(1));
				person.setAddress		(basicInfo.get(2));
				person.setCity			(basicInfo.get(3));
				person.setState			(basicInfo.get(4));
				person.setDriversLicense(basicInfo.get(5));
				person.setVersion		(basicInfo.get(6));
				person.setZip			(basicInfo.get(7));

				logger.debug("Person Data extracted: " + person.toString());
			}
		}
		
		return person;
	}
	
	public String getMasked(String input)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
        	if (input.substring(i, i+1).equalsIgnoreCase(" "))
				sb.append(" ");
			else
				sb.append('*');
        }
        return sb.toString();
    }
	
	private List<String> findBasicInfo(String barcode) 
	{
		List<String> list = new ArrayList<String>();
		list.add(findFirstName		(barcode));		//First name 	pos: 0
		list.add(findLastName		(barcode));     //Last name 	pos: 1
		list.add(findAddress		(barcode));     //Address 		pos: 2
		list.add(findCity			(barcode));     //City 			pos: 3
		list.add(findState			(barcode));     //State			pos: 4
		list.add(findLicenseNumber	(barcode)); 	//License 		pos: 5
		list.add(findVersion		(barcode));	  	//Version 		pos: 6
		list.add(findZipcode		(barcode)); 	//Zipcode 		pos: 7
		return list;
	}
	
	private String findFullName(String barcode) 
	{
		return (barcode.toUpperCase().substring(barcode.indexOf("DAA")+3, barcode.indexOf("DAG")).trim());
	}
	
	private String findAddress(String barcode) 
	{
		return (barcode.toUpperCase().substring(barcode.indexOf("DAG")+3, barcode.indexOf("DAI")).trim());
	}
	
	private String findCity(String barcode)
	{
		return (barcode.toUpperCase().substring(barcode.indexOf("DAI")+3, barcode.indexOf("DAJ")).trim());
	}
	
	private String findState(String barcode)
	{
		return (barcode.toUpperCase().substring(barcode.indexOf("DAJ")+3, barcode.indexOf("DAK")).trim());
	}
	
	private String findZipcode(String barcode) 
	{
		return (barcode.toUpperCase().substring(barcode.indexOf("DAK")+3, barcode.indexOf("DAQ")).trim());
	}

	private String findLicenseNumber(String barcode) 
	{
		return (barcode.toUpperCase().substring(barcode.indexOf("DAQ")+3, barcode.indexOf("DAR")).trim());
	}

	
	private String findFirstName(String barcode) 
	{
		String result = null;
		List<String> strList = Arrays.asList(findFullName(barcode).split(",",-1));
		if (strList != null && strList.size() > 0)
			result = strList.get(0);
		return result;
	}
	
	private String findLastName(String barcode) 
	{
		String result = null;
		List<String> strList = Arrays.asList(findFullName(barcode).split(",",-1));
		if (strList != null && strList.size() > 1)
			result = strList.get(1);
		return result;
	}
	
	private String findVersion(String barcode) 
	{
		String regexBlock = "\\d{6}(\\d{2})\\w+(.*)";
		Pattern pattern = Pattern.compile(regexBlock);
		Matcher matcher= pattern.matcher(barcode);
		StringBuilder str = new StringBuilder();
		if (matcher.find()) {
			str.append(matcher.group(1));
		}
		return str.toString();
	}
}

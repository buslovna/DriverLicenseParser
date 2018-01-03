package com.vemark.dlparser.services;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.vemark.dlparser.domain.PersonData;


@Service
public class DriverLicenseParserImpl implements DriverLicenseParser {

	@Override
	public PersonData parse(String barcode) {
				
		List<String> basicInfo = findBasicInfo(barcode);
		PersonData person = new PersonData();
		person.setLastName(basicInfo.get(0));
		person.setFirstName(basicInfo.get(1));
		person.setAddress(basicInfo.get(2));
		person.setCity(basicInfo.get(3));
		person.setDriversLicense(basicInfo.get(4));
		
		return person;
	}
	
	List<String> findBasicInfo(String barcode) {
		List<String> list = new Vector<String>();
		list.add(findFirstName(barcode));     //First name pos: 0
		list.add(findLastName(barcode));      //Last name pos: 1
		list.add(findAddress(barcode));       //Address pos: 2
		list.add(findCity(barcode));      	  //City pos: 3
		list.add(findLicenseNumber(barcode)); //License pos: 4
		//list.add(versionParser(barcode));	  //Version pos: 5
		
		return list;
	}
	
	String findFullName(String barcode) {
		return (barcode.toUpperCase().substring(barcode.indexOf("DAA") + 3, barcode.indexOf("DAG")).trim());
	}
	String findAddress(String barcode) {
		return (barcode.toUpperCase().substring(barcode.indexOf("DAG") + 3, barcode.indexOf("DAI")).trim());
	}
	String findCity(String barcode){
		return (barcode.toUpperCase().substring(barcode.indexOf("DAI") + 3, barcode.indexOf("DAJ")).trim());
	}
	String findLicenseNumber(String barcode) {
		return (barcode.toUpperCase().substring(barcode.indexOf("DAQ") + 3, barcode.indexOf("DAR")).trim());
	}
	String findFirstName(String barcode) {
		List<String> strList = Arrays.asList(findFullName(barcode).split(","));
		return strList.get(0);
	}
	String findLastName(String barcode) {
		List<String> strList = Arrays.asList(findFullName(barcode).split(","));
		return strList.get(1);
	}
	String versionParser(String barcode) {
		
		String regexBlock = "\\d{6}[(\\d{2})]\\w+";
		Pattern pattern = Pattern.compile(regexBlock);
		Matcher matcher= pattern.matcher(barcode);
		
		String version = matcher.group();
		return version;
	}
}

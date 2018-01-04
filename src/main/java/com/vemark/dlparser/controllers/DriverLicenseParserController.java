package com.vemark.dlparser.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vemark.dlparser.domain.PersonData;
import com.vemark.dlparser.domain.dto.BarcodeDTO;
import com.vemark.dlparser.services.DriverLicenseParser;

@RestController
public class DriverLicenseParserController 
{
	private static final Logger logger = LoggerFactory.getLogger(DriverLicenseParserController.class);
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private DriverLicenseParser driverLicenseParser; 

    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public PersonData postParsePersonData(@RequestBody BarcodeDTO barcode) 
    {
    	logger.info("Controller for /PersonData [POST] is fired...");
    	PersonData person = driverLicenseParser.parse(barcode.getBarcodeString());
        return person;
    }

}

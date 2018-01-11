package com.vemark.dlparser.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vemark.dlparser.domain.PersonData;
import com.vemark.dlparser.domain.dto.BarcodeDTO;
import com.vemark.dlparser.services.DriverLicenseParser;

@RestController
public class DriverLicenseParserPing 
{
	private static final Logger logger = LoggerFactory.getLogger(DriverLicenseParserPing.class);
	private static final java.text.DateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @Autowired
    private DriverLicenseParser driverLicenseParser; 

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    public String postPing() 
    {
    	logger.info("Controller for /ping [POST] is fired...");
    	java.util.Date date = new java.util.Date();
        return sdf.format(date);
    }
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String getPing() 
    {
    	logger.info("Controller for /ping [GET] is fired...");
    	java.util.Date date = new java.util.Date();
        return sdf.format(date);
    }

}

package com.vemark.dlparser.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DriverLicenseParserPing 
{
	private static final Logger logger = LoggerFactory.getLogger(DriverLicenseParserPing.class);
	
	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    public String postPing() 
    {
    	logger.info("Controller for /ping [POST] is fired...");
        return sdf.format(new Date());
    }
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String getPing() 
    {
    	logger.info("Controller for /ping [GET] is fired...");
        return sdf.format(new Date());
    }
}

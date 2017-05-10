package com.dkd.emms.web.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JacksonObjectMapper extends ObjectMapper {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JacksonObjectMapper() {
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		/*this.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);*/
	}

}

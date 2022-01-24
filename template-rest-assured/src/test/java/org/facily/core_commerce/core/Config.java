package org.facily.core_commerce.core;

import io.restassured.http.ContentType;

public interface Config {
	String  APP_BASE_URL = "http://localhost";
	Integer APP_PORT = 8000;
	String  APP_BASE_PATH = "v1";
	
	ContentType APP_CONTENT_TYPE = ContentType.JSON;
	
	Long MAX_TIMEOUT = 4000L;
}

package com.vtiger.genericFileUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {
	public String getDataFromJsonFile(String key) throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		Object obj = parser.parse(new FileReader("D:\\Nikita\\TekP\\practice_testcases_vtiger\\src\\test\\resources\\jsonfileCommonData.json"));
		JSONObject map=(JSONObject)obj;
		String value = map.get(key).toString();
		return value;
	}
}

package com.sh.app;

import java.util.Map;

import com.google.gson.Gson;

public class App {

	public static void main(String[] args) {
		Map<String, Object> map = Map.of(
				"id", 13243298713232L,
				"name", "홍길동",
				"gender", 'M',
				"married", true);
		
		String jsonValue = new Gson().toJson(map);
		System.out.println(jsonValue);
		
	}

}

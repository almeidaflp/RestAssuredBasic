package files;

public class payload {
	
	public static String AddPlace() {
		
		return "{\n"
				+ "    \"location\": {\n"
				+ "        \"lat\": -38.383494,\n"
				+ "        \"lng\":33.427362\n"
				+ "    },\n"
				+ "    \"accuracy\": 50,\n"
				+ "    \"name\": \"Felipe Almeida\",\n"
				+ "    \"phone_number\": \"(22) 2222 2222\",\n"
				+ "    \"address\": \"22, Test Street, Test\",\n"
				+ "    \"types\": [\n"
				+ "        \"shoe park\",\n"
				+ "        \"shop\"\n"
				+ "    ],\n"
				+ "    \"website\": \"https://google.com\",\n"
				+ "    \"language\": \"PT-BR\"\n"
				+ "}";
		
	}
	
	
	public static String CoursePrice() {
		return "{\n"
		+ "  \"dashboard\": {\n"
		+ "    \"purchaseAmount\": 1162,\n"
		+ "    \"website\": \"rahulshettyacademy.com\"\n"
		+ "  },\n"
		+ "  \"courses\": [\n"
		+ "    {\n"
		+ "      \"title\": \"Selenium Python\",\n"
		+ "      \"price\": 50,\n"
		+ "      \"copies\": 6\n"
		+ "    },\n"
		+ "    { \"title\": \"Cypress\",\n"
		+ "      \"price\": 40,\n"
		+ "      \"copies\": 4\n"
		+ "    },\n"
		+ "    {\n"
		+ "      \"title\": \"RPA\",\n"
		+ "      \"price\": 45,\n"
		+ "      \"copies\": 10\n"
		+ "    },\n"
		+ "    {\n"
		+ "      \"title\": \"Appium\",\n"
		+ "      \"price\": 36,\n"
		+ "      \"copies\": 7\n"
		+ "    }\n"
		+ "  ]\n"
		+ "}";
	}
	
	public static String AddBook(String isbn, String aisle) {
		String payload = "{\n"
		+ "    \"name\": \"Felipe Almeida\",\n"
		+ "    \"isbn\": \""+isbn+"\",\n"
		+ "    \"aisle\": \""+aisle+"\",\n"
		+ "    \"author\": \"Felipe\"\n"
		+ "}";
		return payload;
	}

}

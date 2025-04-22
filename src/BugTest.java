import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://felipealmeidaw3.atlassian.net/";
		
		String createIssueResponse = given()
		.header("Content-Type", "application/json")
		.header("Authorization", "Basic ZmVsaXBlYWxtZWlkYXczQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBzWTR0SC1oM2NXSHR2Sl8wZ0pSWUtXUEdKbmZwLThTSk5objFQTExHNEpIQThJYmtDQ1dNUTJRSzFad084eERXa2lUSzdhSENid1R1bkFsUDlOcHJDSkV4eEd5TTZQMXlmODhaNVhzLVMyYjVMdmVqR1ItOEQ4QS1OcmE1QU9leWN6aGM0WWFuMHp3M01MYWl2bFYyUFNKaXFEdGFPYUcwUWFGOGxLNlBYdVE9REQ2OTQ1MDQ=")
		.body("{\n"
				+ "    \"fields\": {\n"
				+ "       \"project\":\n"
				+ "       {\n"
				+ "          \"key\": \"SCRUM\"\n"
				+ "       },\n"
				+ "       \"summary\": \"Jira Items are not working - Automation Rest Assured\",\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n"
				+ "       \"issuetype\": {\n"
				+ "          \"name\": \"Bug\"\n"
				+ "       }\n"
				+ "   }\n"
				+ "}")
		.log().all()
		.post("rest/api/2/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		System.out.println(issueId);
		
		// Add Attachment
		
		given()
		.pathParam("key", issueId)
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization", "Basic ZmVsaXBlYWxtZWlkYXczQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBzWTR0SC1oM2NXSHR2Sl8wZ0pSWUtXUEdKbmZwLThTSk5objFQTExHNEpIQThJYmtDQ1dNUTJRSzFad084eERXa2lUSzdhSENid1R1bkFsUDlOcHJDSkV4eEd5TTZQMXlmODhaNVhzLVMyYjVMdmVqR1ItOEQ4QS1OcmE1QU9leWN6aGM0WWFuMHp3M01MYWl2bFYyUFNKaXFEdGFPYUcwUWFGOGxLNlBYdVE9REQ2OTQ1MDQ=")
		.multiPart("file", new File("/Users/felipealmeida/Downloads/linktothepastcover.jpg")).log().all()
		.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}

}

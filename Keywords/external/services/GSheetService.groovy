package external.services

import com.kms.katalon.core.testobject.FormDataBodyParameter

import internal.GlobalVariable
import katalon.enums.BodyType
import katalon.fw.lib.BaseService
import katalon.model.GSheetValueRange
import katalon.model.WSRequest
import katalon.utility.DateTimeUtility

public class GSheetService extends BaseService<GSheetService> {
	private GSheetValueRange valueRange
	
	public GSheetService refreshGSheetToken() {
		initRequestObject().create(new WSRequest().with {
			url = 'https://accounts.google.com/o/oauth2/token'
			payload = [
				new FormDataBodyParameter('grant_type', 'refresh_token', FormDataBodyParameter.PARAM_TYPE_TEXT),
				new FormDataBodyParameter('client_id', GlobalVariable.gshClientID, FormDataBodyParameter.PARAM_TYPE_TEXT),
				new FormDataBodyParameter('client_secret', GlobalVariable.gshClientSecret, FormDataBodyParameter.PARAM_TYPE_TEXT),
				new FormDataBodyParameter('refresh_token', GlobalVariable.gshRefreshToken, FormDataBodyParameter.PARAM_TYPE_TEXT)
			]
			bodyType = BodyType.FORM_DATA
			it
		})
		return this
	}

	public GSheetService getToken() {
		String token = parseResponseBodyToJsonObject().access_token
		GlobalVariable.gshToken = token
		return this
	}

	public GSheetService addNewRowToGSheet(String spreadSheetId, String sheetName, List<String> rowData) {
		String url = "https://sheets.googleapis.com/v4/spreadsheets/${spreadSheetId}/values/${sheetName}!A2:append"
		Map<String, String> params = ['valueInputOption': 'RAW', 'insertDataOption': 'INSERT_ROWS']
		Map<String, String> headers = ['Authorization': "Bearer ${GlobalVariable.gshToken}"]
		GSheetValueRange body = new GSheetValueRange('ROWS', [rowData])
		initRequestObject().create(new WSRequest().with {
			it.url = url
			it.params = params
			it.headers = headers
			it.payload = body
			it
		})
		return this
	}

	public GSheetService saveTestExecToGSheet(String tcSubId) {
		refreshGSheetToken()
		getToken()
		addNewRowToGSheet(GlobalVariable.testExecSPRId, GlobalVariable.testExecSheetName, [
			GlobalVariable.tsName,
			// testSuiteName
			GlobalVariable.tsStartTime,
			// testSuiteStartTime
			GlobalVariable.tcName,
			// testCaseName
			tcSubId,
			// testCaseSubId
			GlobalVariable.tcStartTime,
			// testCaseStartTime
			new DateTimeUtility().getCurrentDateTime('dd/MM/yyyy HH:mm:ss'),
			// testCaseEndTime
			GlobalVariable.testExecUrl
		])
		return this
	}
	
	public GSheetService getRowsFromGSheet(String spreadSheetId, String sheetName, String range) {
		String url = "https://sheets.googleapis.com/v4/spreadsheets/${spreadSheetId}/values/${sheetName}!${range}"
		Map<String, String> headers = ['Authorization': "Bearer ${GlobalVariable.gshToken}"]
		Map<String, String> params = ['majorDimension': 'ROWS', 'valueRenderOption': 'UNFORMATTED_VALUE', 'dateTimeRenderOption': 'FORMATTED_STRING']
		initRequestObject().get(new WSRequest().with {
			it.url = url
			it.params = params
			it.headers = headers
			it
		})
		return this
	}

	public GSheetService getGetRowsResponseBody() {
		this.valueRange = parseResponseBodyToClassObject(GSheetValueRange.class)
		return this
	}

	public String getTestExecURLFromGSheet(String tcSubId) {
		refreshGSheetToken()
		getToken()
		getRowsFromGSheet(GlobalVariable.testExecSPRId, GlobalVariable.testExecSheetName, 'A2:G1000')
		getGetRowsResponseBody()
		return this.valueRange.values.find { it.get(3) == tcSubId }.get(6)
		// Get the testRunsUrl of the first (the latest) test execution that belongs to the expected tcSubId
	}
}

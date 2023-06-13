package katalon.testops.services

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpFileBodyContent

import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class JUnitReportService extends BaseService<JUnitReportService>{
	public static String junitReportUrl= "$GlobalVariable.testOpsApiUrl$GlobalVariable.version/junit/test-reports"
	public static String getSignedUrl = "$GlobalVariable.testOpsApiUrl$GlobalVariable.version/files/upload-urls?projectId=%s&numberUrl=%s"

	public JUnitReportService uploadJUnitReport(String projectId, String batch, String folderPath, String isEnd, String fileName, String uploadedPath) {
		initRequestObject()
				.createWithParam(junitReportUrl, [
					[
						'Authorization',
						"Basic $GlobalVariable.basicToken"
					]
				],
				setParamsForUploadJUnitReport(projectId, batch, folderPath, isEnd, fileName, uploadedPath))
				.verifyStatusCode(204)

		return this
	}

	// Send GET request to AWS to get Singed URLs
	public JUnitReportService getSignedUrlFromAWS(Number projectId, Number signedUrlNumber=1) {
		initRequestObject()
				.setUrl(String.format(getSignedUrl, projectId, signedUrlNumber))
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.sendGetRequest()
		return this

	}

	// Send PUT request upload JUnit report to AWS cloud
	public JUnitReportService putReportToAWS(String signedUrl, String filePath) {
		initRequestObject()
				.setPayLoadWithFile(filePath)
				.update(null, signedUrl, [
					[
						'content-type',
						'application/octet-stream'
					]
				])

		return this
	}

	public List<TestObjectProperty> setParamsForUploadJUnitReport(String projectId, String batch, String folderPath
			, String isEnd, String fileName, String uploadedPath) {
		List<TestObjectProperty> parameters = new ArrayList<>()
		parameters.add(new TestObjectProperty('projectId', ConditionType.EQUALS, projectId))
		parameters.add(new TestObjectProperty('batch', ConditionType.EQUALS, batch))
		parameters.add(new TestObjectProperty('folderPath', ConditionType.EQUALS, folderPath))
		parameters.add(new TestObjectProperty('isEnd', ConditionType.EQUALS, isEnd))
		parameters.add(new TestObjectProperty('fileName', ConditionType.EQUALS, fileName))
		parameters.add(new TestObjectProperty('uploadedPath', ConditionType.EQUALS, uploadedPath))
		return parameters
	}
}

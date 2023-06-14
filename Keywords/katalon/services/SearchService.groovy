package katalon.services

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.model.Pagination

public class SearchService extends BaseService<SearchService> {
	static String searchUrl = GlobalVariable.testOpsApiUrl + GlobalVariable.version +  "/search"

	public SearchService search (Object body) {
		initRequestObject()
				.setUrl(searchUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public SearchService searchTagInfo () {
		BodySearch body = new BodySearch('Tag', [
			new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)
		], true)

		initRequestObject()
				.setUrl(searchUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public SearchService verfiyTestRunBrowserNameFilter(Object searchResponse, String browserName) {
		if(browserName) {
			int count = 0;
			searchResponse.content.each{ content ->
				content.executionTestSuiteResources.each{ execution ->
					if(execution.platform.browserName == browserName.trim().split(',').last()) count++
				}
			}
			WS.verifyGreaterThan(count, 0)
		}
		return this
	}

	public SearchService verfiyTestRunOsNameFilter(Object searchResponse, String osName) {
		if(osName) {
			int count = 0;
			searchResponse.content.each{ content ->
				content.executionTestSuiteResources.each{ execution ->
					if(execution.platform.osName == osName.trim().split(',').last()) count++
				}
			}
			WS.verifyGreaterThan(count, 0)
		}
		return this
	}

	public SearchService verfiyTestRunStatusFilter(Object searchResponse, String status) {
		if(status) {
			searchResponse.content.each{ content ->
				WebUI.verifyEqual(content.status, status.trim().split(',').last())
			}
		}
		return this
	}

	public boolean testRunsExisted (Object searchResponse) {
		return searchResponse.content != []
	}

	public SearchService searchCustomField(String projectId, String searchValue, String searchKeyValue = 'id') {
		ConditionSearch project = new ConditionSearch("Project.id", "=", projectId)
		ConditionSearch customField = new ConditionSearch(searchKeyValue, "=", searchValue)
		BodySearch body = new BodySearch("CustomFieldDefinition", [project, customField])

		initRequestObject()
				.setUrl(searchUrl)
				.setJsonContentTypeHeader()
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}

	public SearchService searchAllTestCase(String testCaseName, long projectId) {
		BodySearch searchTCBody = new BodySearch('TestCase',
	[new ConditionSearch('name', '=', GlobalVariable.currentTestCaseName),
		new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)])

			search(searchTCBody)
									.parseResponseBodyToJsonObject()
									.content[0].id.toString()
		return this
	}
	
	public SearchService searchTestCaseByName(String testCaseName, long projectId) {
		search(new BodySearch('TestCase',
				[
					new ConditionSearch('name', 'contains', testCaseName),
					new ConditionSearch('Project.id', '=', projectId)
				],
				new Pagination(0, 15, [
					"ExecutionStatistics.startTime, desc NULLS LAST"
				])))
		return this
	}

	public SearchService searchTestRunByName(String testRunName, long projectId) {
		search(new BodySearch('Execution',
				[
					new ConditionSearch('TestSuite.name', '=', testRunName),
					new ConditionSearch('Project.id', '=', projectId)
				],
				new Pagination(0, 30, ["order,desc"])))

		return this
	}
	
	public SearchService searchTestResultsOfTestRun(long testRunId, long projectId) {
		search(new BodySearch('ExecutionTestResult',
				[
					new ConditionSearch('Execution.id', '=', testRunId),
					new ConditionSearch('status', '!=', 'PASSED'),
					new ConditionSearch('status', '!=', 'SKIPPED'),
					new ConditionSearch('Project.id', '=', projectId)
				],
				new Pagination(0, 30, ["id,asc"])))

		return this
	}
	
	public Object searchTestRunWithWaiting(String testRunName, Number projectId) {
		long current = 0
		long end = 35
		while (current < end) {
			WebUI.delay(5)
			def searchTestRun = searchTestRunByName(testRunName, projectId).parseResponseBodyToJsonObject().content[0]
			if(searchTestRun != null) return searchTestRun
			current = (current + 5)
		}
		
		KeywordUtil.markFailedAndStop("Timeout")
	}
	
}
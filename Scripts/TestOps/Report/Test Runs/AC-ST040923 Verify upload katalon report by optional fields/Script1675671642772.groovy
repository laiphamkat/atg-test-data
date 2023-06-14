import java.util.stream.Collectors

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.services.SearchService
import katalon.testops.dashboard.DashboardPage
import katalon.testops.report.ReportPage
import katalon.testops.report.UploadReportPage
import katalon.testops.services.CustomFieldDefinitionService 

Credential user = Page.nav(Credential)
					.getCredentials()
					.withRole('user')
					.inProject(GlobalVariable.platformGeneralProject)
					.getFirst()
					
'PRE-CONDITON: Get total test run before run test'
int before = Page.nav(SearchService)
				.search(new BodySearch('Execution', 
						[new ConditionSearch('Project.id', '=', user.projectId)]))
				.parseResponseBodyToJsonObject().totalElements

'PRE-CONDITON: Create new custom field'
Object customFieldData = Page.nav(CustomFieldDefinitionService).generateData()
Object customField = Page.nav(CustomFieldDefinitionService)
							.create(customFieldData)
							.parseResponseBodyToJsonObject()
customFieldId = customField.id.toString()

'Admin login to TO success'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()

'Select the project and navigate to Report page'
Page.nav(DashboardPage).selectOrg(user.orgName).selectProject(user.projectName)

Page.nav(HeaderBar).clickReports()

'Navigate Upload report page'
Page.nav(ReportPage).navigateUploadReport()

'Upload JUnit report report'
Page.nav(UploadReportPage).uploadKatalonReport()

'Add Custom Field'
Page.nav(UploadReportPage)
	.clickAddNewCustomFieldKatalon()
	.inputCustomFieldDisplayName(customFieldData.key)
	.inputCustomFieldDisplayValue(customFieldData.customFieldOptions[0].value)
	.clickAddCustomField()

'Add Tag and upload report'
Page.nav(UploadReportPage).inputTagKatalon(customFieldData.key).clickImportKatalonBtn()

'Verify upload report successfully with optional fields'
Page.nav(UploadReportPage).verifyUploadReportSuccess()

'Verify the total of test run is counted'
WebUI.delay(10) 
int after = Page.nav(SearchService)
				.search(new BodySearch('Execution',
						[new ConditionSearch('Project.id', '=', user.projectId)]))
				.parseResponseBodyToJsonObject().totalElements

WS.verifyGreaterThan(after, before)

'Verify show exactly test run when filter with optional fields'
String tagId = Page.nav(SearchService)
					.searchTagInfo()
					.parseResponseBodyToJsonObject()
					.content.find{ tag -> tag.name == customFieldData.key }.id
String valueId = customField.customFieldOptions[0].id
def searchResponseBody = Page.nav(SearchService)
	.search(new BodySearch('Execution', 
			[new ConditionSearch('Project.id', '=', user.projectId), 
				new ConditionSearch('Tag.id', '=', tagId)], 
			[new ConditionSearch(String.format("${customFieldData.key}.opt_id" ), '=', valueId)]))
	.parseResponseBodyToJsonObject()

List<String> actualResult = searchResponseBody.content.stream()
				   .map{r -> r.id.toString()}
				   .collect(Collectors.toList())

WS.verifyEqual(actualResult.size(), 1)


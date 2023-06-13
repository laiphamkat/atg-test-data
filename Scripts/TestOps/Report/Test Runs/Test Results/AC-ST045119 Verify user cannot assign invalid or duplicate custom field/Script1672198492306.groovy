import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.CustomFieldOptions
import katalon.services.SearchService
import katalon.testops.report.TestResultPage
import katalon.testops.report.TestRunsPage
import katalon.testops.services.CustomFieldDefinitionService

'Pre-condition: Create custom fields'
Object customFieldInfo = Page.nav(SearchService).searchCustomField(GlobalVariable.platformGenaralProjectId, key, 'key')
															   .parseResponseBodyToJsonObject()
if(customFieldInfo.content != []) {
	Page.nav(CustomFieldDefinitionService).delete(customFieldInfo.content.get(0).id)
}

Object requestBody = [
	"key": key, "displayName" : "${key} Display Name",
	"projectId": GlobalVariable.platformGenaralProjectId,
	"customFieldOptions": [
			new CustomFieldOptions(value: "${key} Value 01"),
			new CustomFieldOptions(value: "${key} Value 02"),
			new CustomFieldOptions(value: "${key} Value 03")
		]
]
customFieldInfo = Page.nav(CustomFieldDefinitionService).create(requestBody).parseResponseBodyToJsonObject()

'Login into TestOps'
Page.nav(SignInPage).enterCredential()
					.clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'Navigate to Katalon on Katalon Org > select Project'
Page.nav(HeaderBar).clickOrganization()
				   .selectOrg(GlobalVariable.defaultOrgName)
				   
'At Home page, user select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)

'Go to the Report > Test Run > Test Runs page'
Page.nav(HeaderBar).clickReports()
Page.nav(TestRunsPage).navigateTestRuns()

'Navigate to test run detail page'
Page.nav(TestRunsPage).filterTestSuite(testSuite)
					  .clickOnTestRun(testRun)
					  
'Click on Test Results tab'
Page.nav(TestResultPage).clickOnTestResultTab()

'Click on Test Result detail'
Page.nav(TestResultPage).clickOnTestResultDetail(testResultName)

'Verify custom field is emplty information'
Page.nav(TestResultPage).verifyCustomFieldInfoIsEmpty()

'Verify custom field value field and assign button are disabled'
Page.nav(TestResultPage).clickAddNewCustomField()
						.verifyCustomFieldValueFieldIsDisabled()
						.verifyAssignCustomFieldButtonIsDisabled()
						
'Click cancle button'
Page.nav(TestResultPage).clickCancelAddCustomField()
  
'Click Add custom fields button'
Page.nav(TestResultPage).clickAddNewCustomField()
  
'Input valid a custom field value => click assign button'
Page.nav(TestResultPage).selectCustomFieldDisplayName(customFieldInfo.displayName)
						.selectCustomFieldDisplayValue(customFieldInfo.customFieldOptions.get(0).value)
						.clickAssignCustomField()
							  
'Verify the newly added custom field cannot searchable'
Page.nav(TestResultPage).clickAddNewCustomField()
						.inputCustomFieldDisplayName(customFieldInfo.displayName)
						.verifyDisplayNameNotPresent(customFieldInfo.displayName)
						.verifyCustomFieldValueFieldIsDisabled()
						.verifyAssignCustomFieldButtonIsDisabled()

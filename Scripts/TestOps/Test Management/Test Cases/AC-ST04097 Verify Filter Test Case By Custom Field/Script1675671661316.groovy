import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.services.SearchService
import katalon.testops.dashboard.DashboardPage
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.services.TestCaseService
import katalon.testops.testmanagement.MultiSearchPopup
import katalon.testops.testmanagement.TestCasesPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole("user")
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()

'PRE-CONDITON: Create new custom field'
testCaseId = Page.nav(SearchService).searchTestCaseByName(GlobalVariable.currentTestCaseName, Long.parseLong(user.projectId))
									.parseResponseBodyToJsonObject()
									.content[0].id
									
Object createCFData = Page.nav(CustomFieldDefinitionService).generateData()

Object createCFResponseBody = Page.nav(CustomFieldDefinitionService)
									.create(createCFData)
									.parseResponseBodyToJsonObject()

'PRE-CONDITION: Assgin CF to test case'
customFieldId = createCFResponseBody.id
Page.nav(TestCaseService).update(["id": testCaseId,
								  "customFieldOptions": [[ "id": createCFResponseBody.customFieldOptions[0].id,
															"definitionId": customFieldId]]])

'Login to TO as admin account'
Page.nav(SignInPage)
		.enterCredential(user.email, user.pwd)
		.clickSignIn()

'Filter by CF on Tag Management'
Page.nav(DashboardPage).selectOrg(user.orgName).selectProject(user.projectName)
Page.nav(HeaderBar).clickTests()
Page.nav(TestCasesPage).openMultiSearchPopup()

Page.nav(MultiSearchPopup)
		.clickAddNewCustomField()
		.inputCustomFieldDisplayName(createCFData.displayName)
		.inputCustomFieldDisplayValue(createCFData.customFieldOptions[0].value)
		.clickAddCustomField()
		.clickApplyAndSearch()

'VERIFY: The test case has added CF is shown in the result'
Page.nav(TestCasesPage)
		.verifyTestCaseIsVisible(GlobalVariable.currentTestCaseName)
		.verifyTheTotalOfTestCase('1 - 1 of 1 test case')



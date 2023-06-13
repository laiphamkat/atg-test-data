import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.CustomFieldOptions
import katalon.services.SearchService
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testmanagement.TestCaseDetailPage

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

'User click on Execution module'
Page.nav(HeaderBar).clickExecutions()

'User click on Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User click on Advanced settings hyperlink'
Page.nav(NewUIScheduleTestRunPopUp).clickAdvancedSettings()

'Click Add custom fields'
Page.nav(NewUIScheduleTestRunPopUp).clickAddNewCustomField()

'Verify custom field value field and assign button are disabled'
Page.nav(NewUIScheduleTestRunPopUp).verifyCustomFieldValueFieldIsDisabled()
								   .verifyAssignCustomFieldButtonIsDisabled()
								   
'Click cancle button'
Page.nav(NewUIScheduleTestRunPopUp).clickCancelAddCustomField()

'Click Add custom fields button'
Page.nav(NewUIScheduleTestRunPopUp).clickAddNewCustomField()

'Input valid a custom field value => click assign button'
Page.nav(NewUIScheduleTestRunPopUp).selectCustomFieldDisplayName(customFieldInfo.displayName)
								   .selectCustomFieldDisplayValue(customFieldInfo.customFieldOptions.get(0).value)
								   .clickAssignCustomField()
							
'Verify the newly added custom field cannot searchable'
Page.nav(NewUIScheduleTestRunPopUp).clickAddNewCustomField()
								   .inputCustomFieldDisplayName(customFieldInfo.displayName)
								   .verifyDisplayNameNotPresent(customFieldInfo.displayName)
								   .verifyCustomFieldValueFieldIsDisabled()
								   .verifyAssignCustomFieldButtonIsDisabled()
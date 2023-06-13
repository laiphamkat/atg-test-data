import groovy.json.JsonSlurper
import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.CustomFieldOptions
import katalon.services.SearchService
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.testmanagement.TestCaseDetailPage
import katalon.testops.testmanagement.TestCasesPage

'Pre-condition: Create custom fields'
def lstCustomFields = new ArrayList<Object>()

JsonSlurper slurper = new JsonSlurper()
listCustomFieldKeys = slurper.parseText(listCustomFieldKeys)

for (String key: listCustomFieldKeys) {
	Object customFieldInfo = Page.nav(SearchService).searchCustomField(GlobalVariable.platformGenaralProjectId, key, 'key')
																   .parseResponseBodyToJsonObject()
	if(customFieldInfo.content != []) {
		Page.nav(CustomFieldDefinitionService).delete(customFieldInfo.content.get(0).id)
	}
	
	Object requestBody = [
				"key": key, "displayName" : "${key} Display Name",
				"projectId": GlobalVariable.platformGenaralProjectId,
				"customFieldOptions": [
					new CustomFieldOptions(value: "${key} Value")]
			]
	customFieldInfo = Page.nav(CustomFieldDefinitionService).create(requestBody).parseResponseBodyToJsonObject()
	lstCustomFields.add(customFieldInfo)
}

'Login into TestOps'
Page.nav(SignInPage).enterCredential()
					.clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'Navigate to Katalon on Katalon Org > select Project'
Page.nav(HeaderBar).clickOrganization()
				   .selectOrg(GlobalVariable.defaultOrgName)
				   
'At Home page, user select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)
					
'Go to the Test Case detail from the Test Management module'
Page.nav(HeaderBar).clickTests()

'Go to Test Case Detail Page'
Page.nav(TestCasesPage).clickUploadedDataFolder()
					   .clickSubFolder('PearTest')
					   .clickTestCaseName(testcase)

'Add custom fields'
for (customField in lstCustomFields) {
	Page.nav(TestCaseDetailPage).clickAddNewCustomField()
								.selectCustomFieldDisplayName(customField.displayName)
								.selectCustomFieldDisplayValue(customField.customFieldOptions.value)
								.clickAssignCustomField()
}

'Verify custom field are assigned to test cases succesffuly'
for (int i = 0; i < lstCustomFields.size(); i++) {
	Page.nav(TestCaseDetailPage).verifyCustomFieldDisplay(lstCustomFields[i].displayName, lstCustomFields[i].customFieldOptions.get(0).value, i+1)
}
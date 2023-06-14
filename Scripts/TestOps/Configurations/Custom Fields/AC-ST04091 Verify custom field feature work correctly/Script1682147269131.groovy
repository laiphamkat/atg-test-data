import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.configurations.ConfigurationPage
import katalon.testops.configurations.CustomFieldCreatePopUp
import katalon.testops.configurations.CustomFieldEditPopUp
import katalon.testops.configurations.CustomFieldPage
import katalon.testops.dashboard.DashboardPage
import katalon.testops.services.CustomFieldDefinitionService
import katalon.utility.DateTimeUtility

'Pre-condtion: Get credential'
Credential user = Page.nav(Credential)
						.getCredentials()
						.withRole(role)
						.inProject(GlobalVariable.platformGeneralProject)
						.getFirst()
						
'Pre-condition: Prepare create CF data'
key = 'auto-' + new DateTimeUtility().getCurrentDateTime('ddMMyyHHmmss')

'1. Login to TO as admin account'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()

'2. Go to Custom Field page and create new custom field'
Page.nav(DashboardPage)
	.selectOrg(user.orgName).selectProject(user.projectName)
	.selectConfigurationTab()
Page.nav(ConfigurationPage).selectCustomFieldsTab()
	
Page.nav(CustomFieldPage).clickCreateBtn()

'3. User inputs general info [Display name, Key, Values] on Create New Custom Field Popup'
Page.nav(CustomFieldCreatePopUp)
	.inputKey(key)
	.inputDisplayName(displayName)
	.inputValue(value)
	.clickCreateButton()

'4. Verify create Custom Field successfully'
Page.nav(CustomFieldPage)
	.verifyKeyIsVisible(key)
	.verifyDisplayNameIsVisible(displayName)
	.verifyValuesIsVisible(value)


'5. Update Custom Field has created'
Page.nav(CustomFieldPage).clickEditIcon(key)

Page.nav(CustomFieldEditPopUp)
	.updateDisplayName(displayNameUpdated)
	.updateValue(valueUpdated)
	.clickSaveChangesButton()

'6. Verify edit Custom Field successfully'
Page.nav(CustomFieldPage)
	.verifyKeyIsVisible(key)
	.verifyDisplayNameIsVisible(displayNameUpdated)
	.verifyValuesIsVisible(valueUpdated)
	
'7. Verify delete custom field successfully'
Page.nav(CustomFieldPage)
	.clickDeleteIcon(key)
	.clickDeleteButton()
	.verifyCustomFieldIsNotDisplay(key)
	
customFieldId = Page.nav(CustomFieldDefinitionService)
	.searchCustomFieldBy(Long.parseLong(user.projectId), ["key": key])
	.verifyStatusCode(200)
	.verifyContentIsNull()

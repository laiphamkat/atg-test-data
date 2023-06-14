import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.configurations.CustomFieldDeletePopUp
import katalon.testops.configurations.CustomFieldEditPopUp
import katalon.testops.configurations.CustomFieldPage

Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('Platform General')
	.getFirst()

'Login to TO success'
String url = "${url}team/${user.teamId}/project/${user.projectId}/custom-fields"
Page.nav(SignInPage).navigateToUrl(url).login(user.email, user.pwd).clickSignIn()

'Verify the Configuration - custom field page'
if(user.role.toLowerCase() != 'user') {
	Page.nav(CustomFieldPage)
	.verifyCreateBtnIsClickable()
	.verifyAllCFValuesAreNotNull()
	.verifyEditIconIsShown()
	.verifyDeleteIconIsShown()
	
	Page.nav(CustomFieldPage).clickEditIcon()
	
	Page.nav(CustomFieldEditPopUp)
		.verifyKeyInputIsVisible()
		.verifyDisplayNameInputIsVisible()
		.verifyValueInputIsVisible()
		.verifyEditCFLabelIsVisible()
		.verifyCancelButtonIsClickable()
		.verifySaveChangesButtonIsClickable()
		.clickCancelButton()
	
	Page.nav(CustomFieldPage).clickDeleteIcon()
	
	Page.nav(CustomFieldDeletePopUp)
		.verifyDeleteCFLabelIsVisible()
		.verifyDeleteButtonIsClickable()
		.verifyCancelButtonIsClickable()
	
	
} else {
	Page.nav(CustomFieldPage)
	.verifyCreateBtnIsNotVisable()
	.verifyAllCFValuesAreNotNull()
	.verifyEditIconIsNotShown()
	.verifyDeleteIconIsNotShown()
}







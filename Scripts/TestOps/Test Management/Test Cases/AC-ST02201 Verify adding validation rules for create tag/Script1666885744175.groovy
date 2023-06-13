import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.testmanagement.TestCaseDetailPage
import katalon.testops.testmanagement.TestCasesPage


'Login into TestOps'
Page.nav(SignInPage).enterCredential()
					.doCheckRemberMe()
					.clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'Navigate to Katalon on Katalon Org > select Project'
Page.nav(HeaderBar).clickOrganization()
				   .selectOrg(GlobalVariable.defaultOrgName)
					
'At Home page, user select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)
					
'Go to the Test Case detail from the Tests module'
Page.nav(HeaderBar).clickTests()

'Go to Test Case Detail Page'
Page.nav(TestCasesPage).clickUploadedDataFolder()
					   .clickSubFolder('PearTest')
					   .clickTestCaseName('AC-ST02201')

'Click on the Tags input field.'
Page.nav(TestCaseDetailPage).clickTagsInputField()

'Input general info [tag name] in the Tags input field.'
Page.nav(TestCaseDetailPage).createNewTag(tagName)
							.verifyCreateNewTag(tagName)
							.assignTag(tagName)

'Verify throw the message Tags must be within 1-50 characters long.'
'Verify throw the message Tags only allow the following characters: a-z, A-Z, 0-9, -, _.'
Page.nav(TestCaseDetailPage).verifyErrorMessage(errorMessage)




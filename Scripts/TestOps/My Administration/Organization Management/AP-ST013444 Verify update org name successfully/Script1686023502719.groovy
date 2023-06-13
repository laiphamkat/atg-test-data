import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.organizationmanagement.OrganizationManagementPage
import katalon.utility.DateTimeUtility

"""
Steps:
1. Login to Admin page
2. Click Setting icon
3. Select 'Org Management' item
4. Input Org Name
5. Click Update button

Validation points:
Show toast message new org name is updated successfully

"""


'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Owner")
					.getFirst()
					
'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Click Settings on Header Navigator > Go to Organization Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickOrganizationManagement()
					
'User updates new org name'
String date = new DateTimeUtility().getCurrentDateTime()
Page.nav(OrganizationManagementPage).inputOrgName("${user.orgName}_$date")
									.clickUpdateNameBtn()
									.verifyUpdateOrgNameSuccessfully()

'Clean up data'
Page.nav(OrganizationManagementPage).inputOrgName(user.orgName).clickUpdateNameBtn()
					

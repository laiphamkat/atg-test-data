
import katalon.common.AdminHeaderBar
import katalon.common.AdminSideBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Admin")
					.getFirst()

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Verify permision in Account Homepage'
Page.nav(AccountHomePage).verifyManageMyLicensesPresent()
						.verifyUpdateMyProfilePresent()
						.verifyGetAccountIdPresent()
						.verifyInviteUsersToAccountPresent()
						.verifySubscribeToKatalonProductsNotPresent()
						
'Verify permision on setting dropdown'
Page.nav(AdminHeaderBar).clickSettingIcon()
						.verifyProductUtilizationPresent()
						.verifyLicenseManagementPresent()
						.verifyUserManagementPresent()
						.verifySupportManagementPresent()
						.verifyOrganizationManagementPresent()
						.verifyTeamManagementPresent()
						.verifyProjectManagementPresent()
						.verifyTestOpsHomepagePresent()
						.verifyPaymentMethodNotPresent()
						.verifySubscriptionManagementNotPresent()
						.clickProductUtilization()
						
'Verify permision on side bar'
Page.nav(AdminSideBar).verifyProductUtilizationPresent()
					.verifyLicenseManagementPresent()
					.verifyUserManagementPresent()
					.verifyOrganizationManagementPresent()
					.verifySupportManagementPresent()
					.verifyAccountSettingsPresent()
					.verifyTeamManagementPresent()
					.verifyProjectManagementPresent()
					.verifyIdleTimeoutPresent()
					.verifyPaymentMethodNotPresent()
					.verifySubscriptionManagementNotPresent()






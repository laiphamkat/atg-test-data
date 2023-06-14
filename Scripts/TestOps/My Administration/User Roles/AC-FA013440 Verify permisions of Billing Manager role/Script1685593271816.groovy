
import katalon.common.AdminHeaderBar
import katalon.common.AdminSideBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.licensemanagement.LicenseManagementPage

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Billing")
					.getFirst()

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Verify permision in Account Homepage'
Page.nav(AccountHomePage).verifyViewMyLicensesPresent()
						.verifyUpdateMyProfilePresent()
						.verifyGetAccountIdPresent()
						.verifySubscribeToKatalonProductsPresent()
						.verifyInviteUsersToAccountNotPresent()
						
'Verify permision on setting dropdown'
Page.nav(AdminHeaderBar).clickSettingIcon()
						.verifyProductUtilizationPresent()
						.verifyLicenseManagementPresent()
						.verifyPaymentMethodPresent()
						.verifySubscriptionManagementPresent()
						.verifyTeamManagementPresent()
						.verifyProjectManagementPresent()
						.verifyTestOpsHomepagePresent()
						.verifyUserManagementNotPresent()
						.verifySupportManagementNotPresent()
						.verifyOrganizationManagementNotPresent()
						.clickProductUtilization()
						
'Verify permision on side bar'
Page.nav(AdminSideBar).verifyProductUtilizationPresent(true)
					.verifyLicenseManagementPresent()
					.verifyPaymentMethodPresent()
					.verifyAccountSettingsPresent()
					.verifyTeamManagementPresent()
					.verifyProjectManagementPresent()
					.verifySubscriptionManagementPresent()
					.verifyIdleTimeoutNotPresent()
					.verifyUserManagementNotPresent()
					.verifyOrganizationManagementNotPresent()
					.verifySupportManagementNotPresent()
					.clickLicenseManagement()
				
'Verify user only view License Management page'					
Page.nav(LicenseManagementPage).verifySubscriptionDetailstPresent()
								.verifyRegisteredMachinesNotPresent()
								.verifyOfflineLicensedNotPresent()
								.verifyLicensedUserNotPresent()
								.verifyOnlineLicensedNotPresent()
					
	






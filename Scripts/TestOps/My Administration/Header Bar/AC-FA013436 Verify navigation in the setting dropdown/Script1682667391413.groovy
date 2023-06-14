import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.licensemanagement.LicenseManagementPage
import katalon.my.organizationmanagement.OrganizationManagementPage
import katalon.my.paymentmethod.PaymentMethodPage
import katalon.my.productutilization.KatalonStudioUtilizationPage
import katalon.my.projectmanagement.ProjectManagementPage
import katalon.my.supportmanagement.SupportManagementPage
import katalon.my.teammanagement.TeamManagementPage
import katalon.my.usermanagement.UserManagementPage
import katalon.testops.dashboard.DashboardPage

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

'Verify navigation to Product Utilization page when select Product Utilization item'
Page.nav(AdminHeaderBar).clickSettingIcon().clickProductUtilization()
Page.nav(KatalonStudioUtilizationPage).verifyUIKatalonStudioUtilizationPage()

'Verify navigation to User Management page when select User Management item'
Page.nav(AdminHeaderBar).clickSettingIcon().clickUserManagement()
Page.nav(UserManagementPage).verifyUserManagementPresent()

'Verify navigation to Payment Method page when select Payment Method item'
Page.nav(AdminHeaderBar).clickSettingIcon().clickPaymentMethod()
Page.nav(PaymentMethodPage).verifyPaymentMethodElementsPresent()

'Verify navigation to Support Management page when select Support Management item'
Page.nav(AdminHeaderBar).clickSettingIcon().clickSupportManagement()
Page.nav(SupportManagementPage).verifyUISupportManagement()

'Verify navigation to License Management page when select License Management item'
Page.nav(AdminHeaderBar).clickSettingIcon().clickLicenseManagement()
Page.nav(LicenseManagementPage).verifyTitleLicenseManagement()

'Verify navigation to Org Management page when select Org Management item'
Page.nav(AdminHeaderBar).clickSettingIcon(true).clickOrganizationManagement()
Page.nav(OrganizationManagementPage).verifyTitleOrgManagement()

'Verify navigation to Team Management page when select Team Management item'
Page.nav(AdminHeaderBar).clickSettingIcon(true).clickTeamManagement(true)
Page.nav(TeamManagementPage).verifyCreateBtnClickable()

'Verify navigation to Project Management page when select Project Management item'
Page.nav(AdminHeaderBar).clickSettingIcon(true).clickProjectManagement(true)
Page.nav(ProjectManagementPage).verifyCreateProjectBtnClickable()

'Verify navigation to TestOps Homepage when select TestOps Homepage item'
Page.nav(AdminHeaderBar).clickSettingIcon(true).clickTestOpsHomepage(true)
Page.nav(DashboardPage).verifyDashboardTitleDisplayed()


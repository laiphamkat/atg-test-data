import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.CommandPrompt
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.configurations.TestCloudTunnelsPage
import katalon.testops.testexecution.TestRunListPage
import katalon.utility.DateTimeUtility
import katalon.utility.FileHelper

// This test case is not supported on macOS and Linux yet. Please execute it on Windows

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to TestCloud Tunnels page'
Page.nav(TestCloudTunnelsPage).navigateTo(user.teamId, user.projectId)

'User navigates to Setup page and should be able to download and configure TestCloud tunnel'
Page.nav(TestCloudTunnelsPage)
	.clickSetup()
	.clickDownload()
	.clickAPIKeyDropdown()
	.selectAPIKey('default')
	.clickSharedTunnel()
	.clickCopyConfig()

'User opens command prompt and paste the config command to configure TestCloud tunnel'
Page.nav(CommandPrompt)
	.open()
	.startInputCommand()
	.cd(downloadLocation)
	.createFolder(folderName)
	.moveDownloadedTunnelFileTo(folderName)
	.cd(folderName)
	.unzipDownloadedTunnelFile()
	.pasteFromClipboard()

'User copies the start command'
Page.nav(TestCloudTunnelsPage).clickCopyStart()

'User starts the tunnel in command prompt'
Page.nav(CommandPrompt)
	.pasteFromClipboard()
	.executeCommand()
	
'User clicks Test Run Types to navigate to Test Run List page'
Page.nav(TestCloudTunnelsPage).clickTestRunTypesLink()
Page.nav(TestRunListPage).waitUntilPageDisplayed().back()
	
'User can see the tunnel active in Tunnels tab'
Page.nav(TestCloudTunnelsPage)
	.clickTunnels()
	.verifyActiveTunnelExist([
		'Status': 'Active',
		'Version': '1.3.0',
		'Owner': user.email,
		'TunnelType': 'Private'
	])

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	folderName = new DateTimeUtility().getCurrentDateTime()
	downloadLocation = FileHelper.getDownloadLocationPath()
}
	
@com.kms.katalon.core.annotation.TearDown
def tearDown() {
	'Stop the tunnel in command prompt'
	Page.nav(CommandPrompt).closeTestCloudTunnels().close().sleep(3)
	
	'Delete downloaded file'
	Page.nav(CommandPrompt)
		.open()
		.startInputCommand()
		.cd(downloadLocation)
		.removeFolder(folderName)
		.executeCommand()
		.printLogs()
		.close()
}
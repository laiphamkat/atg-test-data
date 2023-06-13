import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.configurations.CreateCircleCITestEnvironmentPage
import katalon.testops.configurations.CreateK8STestEnvironmentPage
import katalon.testops.configurations.CreateLocalTestEnvironmentPage
import katalon.testops.configurations.LocalAgentDetailsPage
import katalon.testops.configurations.TestCloudEnvDetailsPage
import katalon.testops.configurations.TestEnvironmentsPage
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.user.KatalonAPIKeyPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Configurations page'
Page.nav(TestEnvironmentsPage).navigateTo(user.teamId, user.projectId)

'Verify user can see the local agent on the page and can work with delete agent'
Page.nav(TestEnvironmentsPage)
	.verifyPageDisplayed()
	.verifyTotalLabelDisplayed()
	.verifyOnlineLabelDisplayed()
	.verifyOfflineLabelDisplayed()
	.verifyLocalAgentExist(['Name': 'cloudian-local-agent-1'])
	.clickDeleteLocalAgent(1)
	.clickCancelDelete()
	
'Verify Agent Details page displayed when clicking on the agent name'
Page.nav(TestEnvironmentsPage).clickLocalAgentName('cloudian-local-agent-1')
Page.nav(LocalAgentDetailsPage).verifyPageDisplayed().back()
	
'User navigates to Create Local Environment page'
Page.nav(TestEnvironmentsPage).clickCreateLocalTestEnvironment()

'Verify users can navigate to the Katalon API key to copy the api key'
Page.nav(CreateLocalTestEnvironmentPage).clickAPIKeyLink()
String apiKey = Page.nav(KatalonAPIKeyPage).clickCopy('default').back().getAPIKeyFromClipboard()

'Verify users can work with the Docker environmnent'
Page.nav(CreateLocalTestEnvironmentPage)
	.clickDockerEnvironmentTab()
	.inputAgentName('Docker agent 1')
	.clickAPIKeyDropdown()
	.selectAPIKey('default')
	.clickStartDockerWithDropdown()
	.selectStartDockerWith('Docker Compose ')
	.clickCopyConfig()
	.verifyConfigDockerComposeCommandFromClipboard(user.email, apiKey, user.teamId, 'Docker agent 1')
	.clickCopyStart()
	.verifyStartDockerComposeCommandFromClipboard()
	.clickStartDockerWithDropdown()
	.selectStartDockerWith('Docker run command')
	.clickCopyStartDockerRunCommand()
	.verifyStartDockerRunCommandFromClipboard(user.email, apiKey, user.teamId, 'Docker agent 1')
	
'Verify users can work with the local agent environmnent'
Page.nav(CreateLocalTestEnvironmentPage)
	.clickLocalEnvironmentTab()
	.sleep(2) // Wait a little bit for the dropdown to be loaded
	.clickOSDropdown()
	.selectOS('Windows x64')
	.clickDownloadAgent()
	.inputAgentName('Local agent 1')
	.clickAPIKeyDropdown()
	.selectAPIKey('default')
	.clickCopyConfig()
	.verifyWin64ConfigLocalAgentCommandFromClipboard(user.email, apiKey, user.teamId, 'Local agent 1')
	.clickCopyStart()
	.verifyWin64StartLocalAgentCommandFromClipboard()
	
'Verify clicking on Test Environments link will navigate back to the Test Environments page'
Page.nav(CreateLocalTestEnvironmentPage).clickTestEnvironmentsLink()
Page.nav(TestEnvironmentsPage).verifyPageDisplayed()

'User navigates to the K8s tab and should see the K8s environment'
Page.nav(TestEnvironmentsPage)
	.clickKubernetesTab()
	.waitUntilKubernetesLoaded()
	.verifyKubernetesExist([
		'Name': 'cloudian-k8s-1', 
		'URL': 'https://01dffd01d4441e5e48b138909fbfddfd.yl4.us-east-1.eks.amazonaws.com/', 
		'Namespace': 'private-test'
	])
	
if (isNotUserRole) {
	'Verify users click on the k8s agent name will navigate to the Edit page'
	Page.nav(TestEnvironmentsPage).clickK8SName('cloudian-k8s-1')
	Page.nav(CreateK8STestEnvironmentPage).verifyUpdateDisplayed().back()
}

'Verify a new tab will be opened when clicking on here link'
Page.nav(TestEnvironmentsPage)
	.clickK8sDocLink()
	.switchTab(1)
	.verifyCurrentURL('https://docs.katalon.com/docs/execute/cloud-based-test-execution/test-execution-with-testops/set-up-kubernetes-test-environments-for-testops')
	.switchTab(0)

if (isNotUserRole) {
	'Verify user can create a new k8s environment'
	Page.nav(TestEnvironmentsPage).clickCreateKubernetesTestEnvironment()
	Page.nav(CreateK8STestEnvironmentPage)
		.setAgentName('K8S env')
		.setURL('https://01dffd01d4441e5e48b138909fbfddfd.yl4.us-east-1.eks.amazonaws.com/')
		.setCertAuthority('LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJd01UQXhOREEyTlRnek1Gb1hEVE13TVRBeE1qQTJOVGd6TUZvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTFpxCkdRcWFVU3pVeXV3eE5pRE5PZ3ordHljaFMrNlMxQjdadjlPcFhENzE4S3ZsbmVYc1p4SG03ZzZGTjhWanF4UzEKNDhnWWFzdjhkUS9UR2RiYUVzbDh5NTBuSkd4SnFQdkluKzNmWjdyNHNqczMrMTdQSUV2eGlOUGVUdEh2SHR1awpabUJuakhzNitTVWNtWGdzNHhXOFhTSHV4TmlLbkx1NEdBUzlmR1FrR05CWnErbGo5dHlxOEI2LzAwS0JDWE1MCjg5M29ueGc5eElES1FKckJseEFMWWZIQzdNMndFL2lvQzVoVjhKMGt0VFpmSDdzalJDa2c1YVgvVVVEMG0yUHEKMEZVK0VGaFNrY3UvdmhIaVpXcmlkbjVzL2p6VDRZTGhQaHRURDFsdlBrNGJEL2h3aVY0N3Rtd05vUVJIbi9KYgprSG9GYkphbzY5THBFS09MNGw4Q0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFKWFpkSFd6TVJpTk8rbTE4TXNiaE80Z2tMTXoKeUpRL1UvdWw4V0MwMmhHYUZ5ODI2b1dVSnFpRkZEUlovOCtldmRvM0NLU1Evb1lsVC8wWHFwSVdjZldDTzEvegpMajJvNkJ1V3Y2VFNPdG5MVlUyY01iZy9IdnJRa1hoeGsyNjZmYVpwaFgyLzVNMDZRQTdHOXV5VW43NVZzRmVYCnlBOHBmSkVPY1FMSEQ0RlBUeXBZS0w0dGtOSS85SEtxd09Od21ncGU5VkRRL3VTcDhIaHNPWlVpc3NYNU95MGQKTWd0MWx6NDhwVVlNRzk1b1ZCUVNmdVdkMUZ0clg1TmV4bk05K2pxSHNPVHRPS3hVeElVWkdYVStjdmhkckpmcQpZSWFvdjNLamV0UXF3UDJxaHFEUnczOWI1dUpqT2FnSk5sdHdLQ3M1N01kd0FlNUZsMEZBckIyTTVVUT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=')
		.setNameSpace('private-test')
		.setAPIKey(apiKey)
		.clickAuthenticationTypeDropdown()
		.selectAuthenticationType('Username / Password')
		.setUsername('username')
		.setPwd('test')
		.clickAuthenticationTypeDropdown()
		.selectAuthenticationType('Token')
		.setToken('test')
		.clickAuthenticationTypeDropdown()
		.selectAuthenticationType('AWS EKS')
		.setRegion('us-east-1')
		.setCluster('TestOps-1PE1IF6t')
		.setAccessKey('e6gEx1nf/ObKYmD6jfZuZUmJ+8X+TC3k')
		.setPrivateAccessKey('S2jx2aMegwynIRWTnJo5ri8NGXaIj1w0VKd21zZgWCna+ecH7Ne4MiWuvR4jsdbe')
		.clickTestConnection()
		.verifySuccessMessageDisplayed()
		.verifyCreateDisplayed()
		.back()
}
	
'User navigates to the CircleCI tab and should see the CircleCI environment'
Page.nav(TestEnvironmentsPage)
	.verifyPageDisplayed()
	.clickCircleCITab()
	.waitUntilCircleCILoaded()
	.verifyCircleCIExist([
		'Name': 'cloudian-CircleCI-1',
		'URL': 'https://circleci.com',
		'FollowedProject': 'cloudian-automation-1',
		'Branch': 'main',
		'VersionControlType': 'github'
	])
		
if (isNotUserRole) {
	'Verify users click on the CircleCI name will navigate to the Edit page'
	Page.nav(TestEnvironmentsPage).clickCircleCIName('cloudian-CircleCI-1')
	Page.nav(CreateCircleCITestEnvironmentPage).verifyUpdateDisplayed().back()
}

'Verify a new tab will be opened when clicking on here link'
Page.nav(TestEnvironmentsPage)
	.clickCircleCIDocLink()
	.switchTab(2)
	.verifyCurrentURL('https://docs.katalon.com/docs/execute/cloud-based-test-execution/test-execution-with-testops/set-up-circleci-test-environments-for-testops')
	.switchTab(0)
		
if (isNotUserRole) {	
	'Verify user can create a new CirecleCI environment'
	Page.nav(TestEnvironmentsPage).clickCreateCircleCITestEnvironment()
	Page.nav(CreateCircleCITestEnvironmentPage)
		.setAgentName('CircleCI env')
		.setURL('https://circleci.com')
		.setPersonalAPIToken('HUsNM9v5G68ycifCwTf1fJa4b8hJPPe/uHQagj/MSEqrjpAD5BiCYOJ3s6m55jcV')
		.setAPIKey(apiKey)
		.clickConnect()
		.clickFollowedProjectDropdown()
		.selectFollowedProject('phu-vuong-katalon / cloudian-automation-1')
		.setBranch('main')
		.verifyCreateDisplayed()
		.back()
}
		
'Verify user can navigate to TestCloud tab and view TestCloud env details page'
Page.nav(TestEnvironmentsPage).verifyPageDisplayed().clickTestCloudTab().clickTCName(1)
Page.nav(TestCloudEnvDetailsPage).verifyPageDisplayed()

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	isNotUserRole = role != 'User'
}
	
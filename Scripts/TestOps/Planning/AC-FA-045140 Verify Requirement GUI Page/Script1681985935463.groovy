import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.planning.RequirementsPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('Platform General')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Planning > Requirement page'
Page.nav(RequirementsPage).navigateTo(user.teamId, user.projectId)

'Verify Requirements title is displayed'
Page.nav(RequirementsPage).verifyRequirementTitleDisplayed()

'Verify statistic bar and status chart of issues are displayed'
Page.nav(RequirementsPage).verifyStatisticBarDisplayed()
						  .verifyStatusChartDisplayed()
						  
'Verify Report session'
   ' Resport session title displayed'
   ' Test Run Covergage and Traceability Matrix display information correctly'
 
String testRunCoverageUrl = "${GlobalVariable.testOpsUrl}team/${user.teamId}/project/${user.projectId}/test-reports/test-design/requirement-test-run-coverage"
String traceabilityMatrixUrl = "${GlobalVariable.testOpsUrl}team/${user.teamId}/project/${user.projectId}/test-reports/test-design/traceability-report"


Page.nav(RequirementsPage).VerifyReportSessionTileDisplayed()
                          .clickTestRunCoverageLink()
						  .sleep(1) // need wait in 1 second here to avoid flaxky test
						  .verifyCurrentUrl(testRunCoverageUrl)
						  .back()
						  .clickTraceabilityMatrixLink()
						  .sleep(1) // need wait in 1 second here to avoid flaxky test
						  .verifyCurrentUrl(traceabilityMatrixUrl)
						  .back()
					  
'Verify can click on all the filters and coppy in the page'
Page.nav(RequirementsPage).verifySearchInputDisplayed()
					      .clickKeyFilter()
						  .clickFeatureFilter()
						  .clickCoppyAsRestApiIcon()
						  
'Verify requirement tabled displayed with full columns: Type, Key, Name, Test Case'
Page.nav(RequirementsPage).verifyRequirementsTableDisplayed()
	

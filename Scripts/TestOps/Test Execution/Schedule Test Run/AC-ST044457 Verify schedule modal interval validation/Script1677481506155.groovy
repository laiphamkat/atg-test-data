import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.getFirst()
	
'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'Select a git repo that has no TSC'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickScriptRepo()
	.selectScriptRepo('API Tests Project')

'Verify if user setup repeat with range 1 x <= 999 at new UI with TestSuite'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputIntervalTime('9').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 10 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('10').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 10 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 999 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Minute(s)').clickSchedule().verifyInvalidIntervalTimeMinutes()
	.inputIntervalTime('9').clickSchedule().verifyInvalidIntervalTimeMinutes()
	.inputIntervalTime('1').inputIntervalUnit('Hour(s)').verifyIntervalTime("every 1 Hour(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Hour(s)').verifyIntervalTime("every 999 Hour(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('1').inputIntervalUnit('Day(s)').verifyIntervalTime("every 1 Day(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Day(s)').verifyIntervalTime("every 999 Day(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('1').inputIntervalUnit('Week(s)').verifyIntervalTime("every 1 Week(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Week(s)').verifyIntervalTime("every 999 Week(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.clearIntervalTime().verifyRequiredMessageDisplayed()

'Verify if user setup repeat with range 1 x <= 999 at new UI with TestSuiteCollection'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickTSCTab()
	.inputIntervalTime('9').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 10 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('10').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 10 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 999 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Minute(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTimeMinutes()
	.inputIntervalTime('9').clearStartTime().clickSchedule().verifyInvalidIntervalTimeMinutes()
	.inputIntervalTime('1').inputIntervalUnit('Hour(s)').verifyIntervalTime("every 1 Hour(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Hour(s)').verifyIntervalTime("every 999 Hour(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Hour(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Hour(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Hour(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('1').inputIntervalUnit('Day(s)').verifyIntervalTime("every 1 Day(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Day(s)').verifyIntervalTime("every 999 Day(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Day(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Day(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Day(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('1').inputIntervalUnit('Week(s)').verifyIntervalTime("every 1 Week(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Week(s)').verifyIntervalTime("every 999 Week(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Week(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Week(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Week(s)').clearStartTime().clickSchedule().verifyInvalidIntervalTime()
	.clearIntervalTime().verifyRequiredMessageDisplayed()

'Verify if user setup repeat with range 1 x <= 999 at new UI with KatalonCommand'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickKatalonCmdTab()
	.inputIntervalTime('9').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 10 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('10').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 10 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 999 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Minute(s)').clickSchedule().verifyInvalidIntervalTimeMinutes()
	.inputIntervalTime('9').clickSchedule().verifyInvalidIntervalTimeMinutes()
	.inputIntervalTime('1').inputIntervalUnit('Hour(s)').verifyIntervalTime("every 1 Hour(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Hour(s)').verifyIntervalTime("every 999 Hour(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('1').inputIntervalUnit('Day(s)').verifyIntervalTime("every 1 Day(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Day(s)').verifyIntervalTime("every 999 Day(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('1').inputIntervalUnit('Week(s)').verifyIntervalTime("every 1 Week(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Week(s)').verifyIntervalTime("every 999 Week(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.clearIntervalTime().verifyRequiredMessageDisplayed()


'Verify if user setup repeat with range 1 x <= 999 at new UI with GenericCommand'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickGenericCommandTab()
	.inputIntervalTime('9').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 10 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('10').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 10 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Minute(s)').verifyIntervalTime("every 999 Minute(s)").clickSchedule().verifyInvalidIntervalTimeMinutesNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Minute(s)').clickSchedule().verifyInvalidIntervalTimeMinutes()
	.inputIntervalTime('9').clickSchedule().verifyInvalidIntervalTimeMinutes()
	.inputIntervalTime('1').inputIntervalUnit('Hour(s)').verifyIntervalTime("every 1 Hour(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Hour(s)').verifyIntervalTime("every 999 Hour(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Hour(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('1').inputIntervalUnit('Day(s)').verifyIntervalTime("every 1 Day(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Day(s)').verifyIntervalTime("every 999 Day(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Day(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('1').inputIntervalUnit('Week(s)').verifyIntervalTime("every 1 Week(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('999').inputIntervalUnit('Week(s)').verifyIntervalTime("every 999 Week(s)").clickSchedule().verifyInvalidIntervalTimeNotPresent()
	.inputIntervalTime('10000').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('0').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.inputIntervalTime('-1').inputIntervalUnit('Week(s)').clickSchedule().verifyInvalidIntervalTime()
	.clearIntervalTime().verifyRequiredMessageDisplayed()
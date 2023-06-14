import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This automation plan test case should be executed manually')

'PRE-CONDITION DATA'
' Having Project/Board in Jira'
' Log in TestOps with Admin role'
' Has integrate with Jira project'
' Create Xray test plan on Jira'
' Test Case is linked to Xray TC'
' Test Schedule is linked to Xray Test Plan'
' Has release version on Jira and polulated to TO'

'STEPS'
'Notes:'
'Verify 3 cases for Xray Push Mechanism:'
' Manual push only'
' Push on all runs'
' Push on passing runs only'
'Besides, make sure select release version'
'Include step to'
' Verify display linked to latest Xray TE on TR when pushed TR to Xray'
' Verify the screenshots will be pushed as evidence when the test results are pushed to xray'
' Verify Test Result URL added to the comment section of the corresponding Xray TR'
' Verify test schedule jira release push as fix version to xray test execution'
' Verify envs from TO are pushed as linked Test Envs under the Test Execution on XRay'
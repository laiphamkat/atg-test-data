import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This test case should be executed manually')

'PRE-CONDITION DATA'
' Having Project/Board in Jira'
' Log in TestOps with Admin role'
' Has integrate with Jira project'
' Create Xray test plan on Jira'
' Test Case is linked to Xray TC'

'STEPS'
'Include a step to '
' Verify error message Invalid Xray Test Plan ID. Please try again when linking Katalon Test Schedule with Issue Type which is not an Xray Test Plan'
' Verify TestOps will get all test and xray test issues when user search xray test issue'
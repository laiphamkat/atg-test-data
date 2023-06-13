import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This automation plan test case should be executed manually')

'PRE-CONDITION DATA'
' Having Project/Board in Jira'
' Log in TestOps with Admin role'
' Has integrate with at least 2 Jira projects'
' Create Xray test plan on Jira'
' Test Case is linked to Xray TC'
' Test Schedule is linked to Xray Test Plan'

'STEPS'
'Verify the TR that exec on TO will be pushed to Xray for both 2 Jira projects'
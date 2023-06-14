import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This automation plan test case should be executed manually')

'PRE-CONDITION DATA'
'Having Project/Board in Jira'
'Log in TestOps with Admin role'
'Has integrate with Jira project'
'Create Xray test plan on Jira'

'STEPS'
'Notes: Verify 2 cases'
' Test Case is linked to Xray, Test Schedule is NOT linked to Xray Test Plan'
' Test Case is NOT linked to Xray Test Schedule is linked to Xray Test Plan'
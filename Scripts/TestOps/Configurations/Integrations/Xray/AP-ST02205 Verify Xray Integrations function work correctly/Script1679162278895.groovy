import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This automation plan test case should be executed manually')

'PRE-CONDITION DATA'
' Having Project/Board in Jira'
' Log in TestOps with Admin role'

'STEPS'
'1. Go to Configurations page'
'2. Click on Integrations tab '
'3. Select Jira/Xray at Intergrations dropdown'
'4. At "Set up Jira" step, Input:'
'+ URL'
'+ Username'
'+ Password'
'+ Enable Xray intergration = ON'
'+ Input Client ID'
'+ Client Secret'
'5. Click Test Connection button'
'6. At "Link Project" step, select Jira Project that you want to integrate'
'7. Click Save button'
'Verify Connect Successful message show correctly'
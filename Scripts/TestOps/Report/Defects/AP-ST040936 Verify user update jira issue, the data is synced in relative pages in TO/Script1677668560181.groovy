import com.kms.katalon.core.util.KeywordUtil

'''
Pre-condtion:
- Having Project/Board in Jira
- Log in TestOps with Admin role
- Has integrate with Jira project
- Create tickets on Jira (bug, task, epic, xray)
- Create BDD test run

Steps:
1. Login to TO
2. Go to Report > Defect
Verify ticket name show correctly with Jira
3. Update ticket in Jira
4. In TO > Report > Defect page:
Verify ticket name show correctly with Jira
Verify the refresh button is enable
'''

KeywordUtil.markFailed('This test case should be executed manually and add automation test case later.')

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
3. In Configuration > Integration, change to another workspace
Verify jira issues are not shown on related pages
4.  In Configuration > Integration, go back to the workspace
Verify jira issues are shown on related pages
'''

KeywordUtil.markFailedAndStop('This test case should be executed manually')
import com.kms.katalon.core.util.KeywordUtil


'''
Precondition:
- Having Project/Board in Jira
- Log in TestOps with Admin role
- Has integrate with Jira project
- Create tickets on Jira (bug, task, epic, xray)

Steps:
1. Login to TO with admin role
2. Go to Reports > Defects
3. In the chart, check info:
Verify the summary: Created, Opened, Resolved
Verify the chart: 3 line show the correct total of issues
Verify the tooltip on chart show correctly
'''

KeywordUtil.markFailedAndStop('This test case should be executed manually')
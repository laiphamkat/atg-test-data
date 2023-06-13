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
3. In the board, click on 1 row
Verify showing the defect detail of ticket.
Verify the test case sections show.
Verify the test run sections show.
Verify the requirement sections show.
'''

KeywordUtil.markFailedAndStop('This test case should be executed manually')
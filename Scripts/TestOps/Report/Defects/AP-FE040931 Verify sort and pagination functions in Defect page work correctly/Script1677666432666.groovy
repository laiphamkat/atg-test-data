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
3. In the board, select sort option
Verify the data is sorted
4. Move to next page
Verify the data in the next page is loaded
'''

KeywordUtil.markFailedAndStop('This test case should be executed manually')
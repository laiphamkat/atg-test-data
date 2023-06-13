import com.kms.katalon.core.util.KeywordUtil


'''
Precondition:
- Project has added jira tickets before 
- Deactive integrate with Jira

Steps:
1. Login to TO with user role
2. Verify some pages:
- Show empty page in: 
 - defect
 - requirement: planning, report.
- Still show jira tickets have added before
 - test case detail
 - tets run detail
3. Verfy include step to verify Push to Xray button is hidden if Xray integration has not been set up
'''

KeywordUtil.markFailedAndStop('This test case should be executed manually')
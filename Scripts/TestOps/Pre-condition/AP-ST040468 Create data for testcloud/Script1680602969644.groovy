import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailedAndStop('This test case should be executed manually')

"""
- Create 2 project 'Cloud-based Test Execution' and 'Cloud-based Test Execution without Tunnel'
- Connect git repo 'cloudian-automation-1' for the 2 projects above
- Assign 2 users to the 'Cloud-based Test Execution' project with role User, Admin
- Create local agent/circle ci/k9s for 'Cloud-based Test Execution' project with role User, Admin
- Connect sample git repo 'API Test' for 'Cloud-based Test Execution' project
- Upload apps for 'Cloud-based Test Execution' project

- Create org 'KOK Org w. testCloudLambdaTestEnvironment ON'
- Create team 'Cloudian'
- Create project 'Cloud-Based Test Execution'
- Buy TestCloud subscriptions
- Connect git repo 'cloudian-automation-1'
- Turn on flag for org
"""

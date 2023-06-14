import com.kms.katalon.core.util.KeywordUtil

'''
Pre-condition: 
- User created on 30 days
- 3 roles: Owner, Admin, User

Steps:
1. User login to TO with roles (User, Admin, Owner).
2. Goto Dashboard page, verify the Get started! section is shown.
3. Verify that there are 11 steps to finish the Katalon Platform onboarding.
4. (user) Veirfy the 1st step - "Download & log in on Katalon Studio" and the content are correct
5. (user) Veirfy the 2nd step - "Sync data from Studio Project to TestOps" and the content are correct
6. (user) Veirfy the 3rd step - "Analyze test result" and the content are correct 
7. (user) Veirfy the 4th step - "Setup Script Repository" and the content are correct 
8. (user) Veirfy the 5th step - "Create Test Suite on TestOps" and the content are correct
9. (user) Veirfy the 6th step - "Execute your test remotely using TestOps" and the content are correct 
10. Veirfy the 7th step - "Invite your team members" and the content are correct 
11. Veirfy the 8th step - "Integrate with a JIRA Project" and the content are correct 
12. (user) Veirfy the 9th step - "Upload Test Results manually" and the content are correct 
13. Veirfy the 10th step - "Setup Local Agent" and the content are correct  
14. Veirfy the 11th step - "Create your own project" and the content are correct 
15. Verify user can collapse the onboarding.
16. Verify after finishing step: 
- The step is strikethrough, button and link are disable.
- Auto move to next step
17. Verify user clicks on step, system show the pre-requisites (if have)
'''

KeywordUtil.markFailedAndStop('This test case should be executed manually')
import com.kms.katalon.core.util.KeywordUtil

'''
Pre-condition:
- User created on 30 days
- 3 roles: Owner, Admin, User

Steps:
1. User login to TO with roles (User, Admin, Owner).
2. Goto Dashboard page, verify the Get started! section is shown.
3. Verify that there are 6 steps to finish the Katalon Platform onboarding.
4. Veirfy the 1st step - "Create your first project" and the content are correct
5. Veirfy the 2nd step - "Write your first test case" and the content are correct
6. Veirfy the 3rd step - "Plan your tests with a Test Suite" and the content are correct 
7. Veirfy the 4th step - "Analyze your test activities with Cloud Platform" and the content are correct 
8. Veirfy the 5th step - "Try cross-browser cloud testing" and the content are correct
9. Veirfy the 6th step - "Execute test via CI/CD pipeline with Runtime Engine" and the content are correct 
10. Verify user can collapse the onboarding.
11. Verify after finishing step: 
- The step is strikethrough, button and link are disable.
- Auto move to next step
12. Verify user clicks on step, system show the pre-requisites (if have)
'''

KeywordUtil.markFailedAndStop('This test case should be executed manually')
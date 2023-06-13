import com.kms.katalon.core.util.KeywordUtil

'''
Pre-condition: User created less than 30 days

Steps:
1. User login to TO with roles (User, Admin, Owner).
2. Verify the onboarding section is shown on Dashboard page.
3. User click to dismiss the onboarding
4. Verify when user login again, the onboarding section is hide
'''

KeywordUtil.markFailedAndStop('This test case should be executed manually')
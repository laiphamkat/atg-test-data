import com.kms.katalon.core.util.KeywordUtil


KeywordUtil.markFailedAndStop('This test case should be executed manually')

"""
Pre-conditions: Account have KRE subscription

Steps:
1. Login to Admin
2. Click Setting icon > Support Managment
3. Check quota support management

Expected result:
For KRE Node-locked/Floating
Quota = 1 * 1 purchased KRE Node-locked + 3 * 1 KRE purchased Floating
"""
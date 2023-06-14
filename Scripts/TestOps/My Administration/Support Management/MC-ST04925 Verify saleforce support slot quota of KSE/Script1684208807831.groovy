import com.kms.katalon.core.util.KeywordUtil


KeywordUtil.markFailedAndStop('This test case should be executed manually')

"""
Pre-conditions: Account have KSE subscription

Steps:
1. Login to Admin
2. Click Setting icon > Support Managment
3. Check quota support management

Expected result:
For KSE Node-locked/Floating
Quota = 1 * 1 purchased KSE Node-locked + 3 * 1 KSE purchased Floating
For KSE per-User
Quota = 1 * 1 purchased KSE per-User
"""
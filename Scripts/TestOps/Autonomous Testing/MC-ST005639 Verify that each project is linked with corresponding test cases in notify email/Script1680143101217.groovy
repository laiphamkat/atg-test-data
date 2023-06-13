import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("Manual test case")
'''Pre-condition:

Multiple projects are linked to a newly created AUT.
50 Test case are generated 

Step:
1. Open personal mailbox (e.g:QEAutomation@gmail.com)
2. Open mail from Autonomous test gen 
2. Click to hyperlink ' <<Project name>> - View Test case'

Expected result:
1. The content of email same with mockup 
2. When click to <<Project name>> - View Test case, user will be navigated to Test Management > Test case page

'''
// note: in Minimal 1, we support link only 1 project

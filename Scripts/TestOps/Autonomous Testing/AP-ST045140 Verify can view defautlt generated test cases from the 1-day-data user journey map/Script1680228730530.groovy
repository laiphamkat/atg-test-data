import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This test case should be executed manually')

'1. Create an AUT in the Admin page'
'2. Config all information: Application Domain(s) *, test environment, link one project with reporsitory'
'3. Congig code snippet to client test site'
'4. Wait for generated test case successfully in one day'
'5. Navigate to configured project > tests > test cases > configured git repository'
    'Verify under Test Case folder have "AI Test Generation" folder is created'
    'While test case in generating process => Verify test case page is instatus running and show message for notify user'
        'know that we will to nitfiy ' 
'6. After generated test case successfully > go to email of owner of AUT'
    ' Verify an email with Subject is updated to: Test Case Generation is completed is sent to and contain a link of test case'
'7. Click the link at email'
     ' Verify will navigate to test case list page at folder AI Test Generation'
	 ' Verify list of test case is displayed as normail testops list page'
	 ' Verify an additional column named “Creator” be added to match with the autonomous test case'
	 ' Verify maintainer and creator is Katalon AI'
'8. Go to a test case detail page'
     ' Verify test case detail page displayed correctly as normal test ops test case'
	 ' Verify maintainer is Katalon AI'
	 ' Verify view test step button is enabled'
'9. Click to view test step button'
     'Veriy test step popup is displayed and show correct information'
import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("Manual test case")
'''Pre-condition:
User have an AUT.
Step:
1. Log into TO.
2. Nav. to the Module Autonomous Testing.
3. Select an AUT.
4. In the detail page of AUT, click button "Add Environment".
   Expected result:
	The toggle on/off is displayed and the “Set up private tunnel“ button is disabled.
	When the toggle is on, the "Set up private tunnel" button is enabled.
	The "Save" button is disabled until the private tunnel setup is completed.
5. Click the “Set up private tunnel“ button.
   Expected result:
	Dialog show steps to set up the private tunnel, the "Back" icon is displayed. 
6. Click to the "Back" icon.
   Expected result:
	Back to the "Add test env" UI, the “Save“ button is enabled.
7. Click “Save“ button to finish adding test env.
   Expected result:
	The "View tunnel" link at the newly added test en
8. Click the "View tunnel" link.
   Expected result:
	Dialog is shown with the tunnel configuration tunnel.
'''
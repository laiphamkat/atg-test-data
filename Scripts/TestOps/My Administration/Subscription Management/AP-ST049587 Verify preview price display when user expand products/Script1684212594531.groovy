import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailedAndStop("This test should be performed by manual testing")

'''
Steps:
1. Navigate to the Subscription management page as a new customer
2. Select a starter package (Platform or Standalone)

Expected result:
2. Validate point:
+ Verify that licenses/quota of each product within the package are filled automatically.
+ Verify that the discount is applied for starter package correctly 
+ Verify total amount is calculated correctly
'''
import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("This test should be performed by manual testing")

'''
I. Verify that subscription expired change on DB should be run correctly
Pre-condition:
 - The 'Subscription Management' page is opening.
 - Account expiry date closely current date

Steps:
1. Insert card pass on the Payment method 
2. Purchased new product on Subscription Management
3. On Recurly try to update the expiry date close to the current date
4. Waiting until the expiration date expired
5. Over the time of dunning cycle

Note: Can replace step 4 and 5 with:
6. Terminate subscription (or products add-on in volumn base discount subscription)

Expected result:
Subscription expired: change status to Inactive
+ Sync Recurly Subscription status on Commerce db: commerce > commerce_objs > recurly_susbcriptions
+ Sync Recurly Subscription status on KatOne db: katone > billing > recurly_susbcriptions
+ Sync Recurly Subscription status on KIT db: kit > katone_objs > recurly_susbcriptions
'''
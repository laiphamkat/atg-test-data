import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("This test should be performed by manual testing")

'''
I. Verify that subscription dunning cycle change on DB should be run correctly
Pre-condition:
 - The 'Subscription Management' page is opening.
 - Had subscription active in Account

Steps:
1. Insert card pass on the Payment method 
2. Purchased new product on Subscription Management
3. On Recurly try to update the expiry date close to the current date
4. Insert card failure on the Payment method
5. Waiting until the expiration date expired > After that the subscription return status to Dunning Cycle
6. On DB observe the data

Expected result:
Subscription expired: change status to Inactive
+ Sync Recurly Subscription status on Commerce db: commerce > commerce_objs > recurly_susbcriptions
+ Sync Recurly Subscription status on KatOne db: katone > billing > recurly_susbcriptions
+ Sync Recurly Subscription status on KIT db: kit > katone_objs > recurly_susbcriptions
'''
import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("This test should be performed by manual testing")

'''
I. Verify after turn off auto-renew subscription, the product still has license to work

Pre-condition:
- Subscription Management opening
- Org purchased volume base plan with: KSE per-User, KRE Floating, TO Platform, TestCloud, Visual Testing

1. Turn off auto renew KSE per-User
2. Turn off auto renew KRE Floating
3. Turn off auto renew TO Platform
4. Turn off auto renew TestCloud
5. Turn off auto renew Visual Testing

1,2,3,4,5. Users still use quota product without any problem

II. Verify after turn off auto-renewal and waiting until renewed will not count on discount price with products turn off auto-renewal

Pre-condition:
- Subscription Management opening
- Org purchased volume base plan with: KSE per-User, KRE Floating, TO Platform, TestCloud, Visual Testing

Steps:
1. Turn off auto renew KSE per-User
2. Update next billing date close to current date
3. Waiting until renewed new billing cycle

Continues with other products:
4. Turn off auto renew KRE Floating
5. Turn off auto renew TO Platform
6. Turn off auto renew TestCloud
7. Turn off auto renew Visual Testing

Expected result:
1. Create new pending change subscription
3,4,5,6,7. After renewed when products canceled will not count on discount price and recalculate the price of product still activate
'''

# User Guide

# Introduction

This repository contains all automated tests for Katalon platform product and owned by Quality Engineering team

- Automation Tool: Katalon Studio (Platform Or Edition)
- Language: Groovy, Java
- Design Pattern: Fluent Page Object Model
- Support: API and UI application
- SUT/AUT: Katalon TestOps (Web Application, Rest APIs)

# **Prerequisites**

- Java 8+ installed already
- Katalon Studio installed already (latest version)

# **High-Level Design**

![APPROACH](Images/Readme/automation_approach.png)

# **Usage**

## **UI Automation - Framework Structure**


### <ins> **Profile** </ins>
**Objectives**: Consists of test environment configuration/ global variables for running tests
![PROFILE](Images/Readme/profile.png)

### <ins> **Keyword** </ins>
![KEYWORD](Images/Readme/keyword_fw.png)
- `katalon.fw.*`: ***Using the keywords for building core framework function***
  - `BaseElement.groovy`: Define wrap-up methods of findTestObject from Object Repository to locate element on Page Object Classes (eg. class, id, link, name, css ...)
    ![BASE ELEMENT](Images/Readme/base_ele.png)
  #####
  - `Page.groovy`: Generate Page Object Classes leverage singleton design pattern in 2 forms (Lazy - *creation of instance at load time* & Early Instantiation - *creation of instance when required*) to help to save memory because object is not created at each request
    ![PAGE](Images/Readme/page.png)
  #####
  - `BasePage.groovy`: Parent page for other page objects classes, contains common functions on many pages
    ![BASE PAGE](Images/Readme/base_page.png)
  #####
  - `KEventHandler.groovy`: Custom driver event handler listener to listen driver event log during running test
    ![K EVENT](Images/Readme/k_event.png)
  #####
  - `Credential.groovy`: To manage and get credentials based on the test environments to log in and execute test scenarios. 
    ![CREDENTIAL](Images/Readme/credential.png)
    - `How it works`:
      - First, we will have 3 CSV files that represent for credentials in 3 test environments:
      - ![CSV FILES](Images/Readme/csv_files.png)
      - ![QA CSV](Images/Readme/qa_csv.png)
      - Then, we have 3 Katalon data files to link to the CSV files:
      - ![KATALON DATA FILES](Images/Readme/katalon_data_files.png)
      - Next, on the profiles, we will define which data file should be used for the environments:
      - ![DATA FILES PROFILE](Images/Readme/data_files_profile.png)
      - Then the Credential class will automatically use the correct data file for the enivonment that you run the test scripts.
    - `Pros and Cons`:
      - `Pros`:
        - Centralize all the credential test data in one place.
        - Give us the awareness of what attributes that credentials have. For example, by looking at the CSV file, we will know which feature flags that the organizations have.
        - Reuse credentails across team. If your team's test cases do not require any special or specific test data, you can reuse credentials from other teams.
      - `Cons`: There may be some issues retrieving wrong credentials. Solution: Sort and organize data in a way so that data can be retrieved correctly. Keep track and update the framework if needed. 
    - `How to use`:
      - `Basic usage`:
        - You only need to call the Credential class as below and then use the data for the later steps of the test cases:
        - ![BASIC USAGE](Images/Readme/basic_usage.png)
      - `Credentials only for specific testcases`: In case you want a credential data to be only used by specific test cases, for example, test case AC-ST044472 requires an organization that always has 2 parallel sessions when the test is being executed, so this credential should not be accessed by other testcases, then you can do the steps below:
        - Input test case ID in the forTestCases column for the credential in the CSV file:
        - ![FOR TESTCASES](Images/Readme/for_testcases.png)
        - By doing the step above, the credential will be excluded from other test cases.
        - In your test case that needs the credential above, just call like below:
        - ![FOR THIS TESTCASE](Images/Readme/for_this_testcase.png)
    - `How to order the credential`
      - Credentials that can be used by many teams should be placed in the first rows.
      - Credentials that are used only for specific needs or specific test cases should be placed in later rows.
  #####
- `katalon.*<feature>.groovy`: ***Implement the Page Object Model (POM Pattern) to make the Test Objects (UI Objects) attached to their pages (Page Objects) to do the Web actions such as clicking a button or filling in a textbox***
![KEYWORD](Images/Readme/keyword_page.png)
  - Those are organized based on the feature site maps and each class will be inherited from `BasePage` class
  - Must not contain utility functions, it should be implement as web element-action-based design
  ![POM](Images/Readme/page_design.png)

### <ins> **Object Repository** </ins>

![OBJECT REPOSITORY](Images/Readme/object_repository.png)
- **Objective**: To avoid object duplication during development time and help to reduce performance issue, as well as simply the way to define test objects
- **Recommendation**: The Scripter should try to use dynamic element to avoid creating too many test objects.
- **Usages**:  
  * btn: To locate Button element, only need input label of button which want to locate to
![BUTTON](Images/Readme/btn.png)
  * class: To locate element by class attribute, only need input class name of element which want to locate to
![CLASS](Images/Readme/class.png)
  * name: To locate element by name attribute, only need input name attribute value of element which want to locate to                                                                            
![NAME](Images/Readme/name.png)                                       
  * id: To locate element by id attribute, only need input id of element which want to locate to   
![Id](Images/Readme/id.png)
  * link: To locate a link element with tag name 'a', only need input link text of element which want to locate to   
![LINK](Images/Readme/link.png)
  * text: To locate element by text, only need input text value of element which want to locate to   
![TEXT](Images/Readme/text.png)
  * txt: To locate checkbox element by checkbox label, only need input checkbox label value of element which want to locate to   
![TXT](Images/Readme/txt.png)
  * title: To locate element by title attribute, only need input title value of element which want to locate to   
![TITLE](Images/Readme/title.png)
  * css: To locate element by css                                                                                         
![CSS](Images/Readme/css.png)

### <ins> **Test Cases** </ins>
![TEST CASE](Images/Readme/test_case.png)
- Test scripts are organized based on the site maps. For examples, test cases for Application Repository feature should be stored under Testops > Test Execution > Application Repository folder.
- **Usages**: 
  - Required follow naming convention as [automation practices](https://katalon.atlassian.net/wiki/spaces/ENG/pages/2354249760/WIP+Automation+Practices)
  - Test case design follow Fluent Page Object Model
  ![TEST CASE EG](Images/Readme/test_case_eg.png)

### <ins> **Test Data | Data Files** </ins>
- **Objectives**: Contains all of data-driven test for automated tests, leveraging excel file to store data, and organized follow site map of AUT
- **Usages**:
  - Each WorkBook represent for a feature (eg. TestManagement, Configurations, etc.)
  - Each WorkSheet represent for a test script, sheet name is required the same name as test case id (eg. AC-FA38401)
- Screenshot: TBD

### <ins> **Test Listener** </ins>
![TEST LISTENER](Images/Readme/test_listener.png)
- Handle test hook actions(@BeforeTestCase, @AfterTestCase, @BeforeTestSuite, @AfterTestSuite...)
### <ins> **Test Suites: TBD** </ins>

### <ins> **UI Test - Data Preparation Way** </ins>
- **Objective**: 
  - Being able to use APIs as the primary method of preparing test data can lead to faster and more accurate results.”
  - The implementation of API call methods can be reduced by removing unnecessary code lines.
  - It is important for teams to use service classes consistently in order to align with the principal.

- **CRUD methods**: 
  -  There are Create/Update/Get/Delete (CRUD) methods in BaseServices which includes common set methods already

  ![BASE SERVICE](Images/Readme/PrepareData_baseService.png)

  - With 2 types for implementation now:
    - Basic usage: Using param in case of basic API without many specific params or headers
    ![BASIC USAGE](Images/Readme/usage2.png)
    
    - Dymanic usage: Build request fully with WSRequest model then input to CRUD method
    ![DYNAMIC USAGE](Images/Readme/usage1.png)

- **To Implement**: 
  - Using CRUD methods from BaseService to implement API methods in service classes
  - Feel free to get and handle data on demand by using parseResponseBodyToJsonObject to get json body
  - Call and use in test cases following fluent style

**Recommendations**: 
  - The implementing API methods to call to endpoint should be separated with other data hanlding actions for more clear and dynamic in usage
  
  For example: Call API to login -> get token
  ![SERVICE CLASS](Images/Readme/PrepareData_ServiceClass.png)

  ![SCRIPT IMPLEMENT](Images/Readme/PrepareData_script.png)

## **API Automation** 
### <ins> **Structure** </ins>
- Leverage UI automation structure, applying Fluent Page Object Model and organizing structure as site maps of UAT
- **BaseService.groovy**: 
  - Objectives: contains common functions for API endpoint testing (eg. set baseUrl, set Auth, setPayload, setMethod, ect.), it will be inhered from services pages
![BASE SERVICE ](Images/Readme/base_service.png)
- `katalon.service.*.groovy`: 
  - Objectives: contains all api service pages classes, those are also organized based on the feature site maps
  - Usages: Design test follow fluent pattern, **remember to `.initRequestObject()` to initialize request instance**
![PAGE SERVICE ](Images/Readme/service_page_eg.png)
- **Testcases**: The same UI, test cases are organized follow feature site maps or UAT
  - Usages: 
    - Test case design follow Fluent Page Object Model and test case convention as UI test case mention
    - Leverage **Page** class to generate Page Service class 
![API TESTCASE ](Images/Readme/api_test_case.png)
# Report: *(TBD)*
# Reference Sources
- [Automation practices](https://katalon.atlassian.net/wiki/spaces/ENG/pages/2354249760/WIP+Automation+Practices)


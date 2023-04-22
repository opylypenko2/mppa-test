mppa-test project

HOW TO RUN THE PROJECT

--------------------------------------------------------------------------------------------
RUN FROM CUKES RUNNER
--------------------------------------------------------------------------------------------
- To run the whole feature file (all scenarios) --> place tags `@wip @ui` on top of Feature:
   `@wip @ui
    Feature: Login function`

- @wip --> work-in-progress
- @ui --> for using @Before and @After methods from Hooks.java class for UI testing --> 
- @Before method for setting up the driver
- @After method for closing the Driver + taking a screenshot for failed scenarios

- To run a single Scenario --> place tags `@wip @ui` on top of Scenario you'd like to run
- Trigger test execution from CukesRunner.java class --> make sure `dryRun = false, tags = "@wip and @ui"`, then run from CukesRunner
-----------------------------------------------------------------------------------------------------
RUN FROM COMMAND LINE
----------------------------------------------------------------------------------------------------- 
mvn test   --> all tests will be executed
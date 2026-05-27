package com.codingsrv.SpringBootTestingWithJUnitAssertJ;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
//@SpringBootTest
class ApplicationTests {


    @Test    // to mark a method as a test method
    void testNumberOne(){
        log.info("test one is run");
        // test logic
    }



    @Test
    @DisplayName("displayTestNumberTwo")  //  to assign a custom name to a method or a class.
    void testNumberTwo() {
        log.info("test two is run");
        // test logic
    }


    @Disabled  // to disable a test method or a test class
    @Test
    void contextLoads() {
        log.info("context test method");
        // test logic
    }



    @BeforeEach  // to execute this method before executing each test method, commonly used to initialize test data or reset test conditions before each test runs.
    void setUp(){
        log.info("Setup before each test");
    }



    @AfterEach  // to execute this method after executing each test method, commonly used for cleanup operations such as releasing resources.
    void tearDown() {
        log.info("Cleanup after each test");
    }


    @BeforeAll  //  indicates that, this method should be executed once before all test methods in the class.
    static void setUpOnce(){
        log.info("setting up once...");
    }


    @AfterAll  //  indicates that, this method should be executed once after all test methods in the class have finished running.
    static void tearDownOnce(){
        log.info("tearing down all....");
    }


    // write test cases for this method by using both JUnit and AssertJ
    int addTwoNumbers(int a, int b){
        return a+b;   // actual result.
    }

 // testing with JUnit
    @Test
    void additionTestOne(){
        int a =3;
        int b =2;  // we know that addition of 3 and 2 will be 5, so "5" is our expected result.

        int result = addTwoNumbers(a,b);  // to test a method, we call it inside a test method and validating its output using assertions.

        // now validate with the "Assertions" class(from JUnit) to compare actual result and expected result.
        org.junit.jupiter.api.Assertions.assertEquals(5,result); // if these two are equals then only test case will pass.
    }

    // testing with AssertJ
    @Test
    void additionTestTwo() {
        int a = 3;
        int b = 2;  // we know that addition of 3 and 2 will be 5, so "5" is our expected result.

        int result = addTwoNumbers(a, b);  // to test a method, we call it inside a test method and validating its output using assertions.

        // now validate with the "Assertions" class(from JUnit) to compare actual result and expected result.
        Assertions.assertThat(result).isEqualTo(5).isCloseTo(6, Offset.offset(1));
    }




    // write test cases for this method using AssertJ for both non-zero and zero denominator.
        double divideTwoNumbers(int a, int b){
           try {
               return a /b;
           }catch (ArithmeticException e){
            log.info("Arithmetic exception occurred " + e.getLocalizedMessage());
            throw new ArithmeticException(e.getLocalizedMessage());
           }
        }


    @Test
    void testDivideTwoNumbers(){
        int a = 6;
        int b = 5;

        divideTwoNumbers(a, b);  // calling the method inside test method
    }

    @Test
    void testDivideTwoNumbers_whenDenominatorIsZero_ThenArithmeticException(){
        int a = 6;
        int b = 0;

        Assertions.assertThatThrownBy(()-> divideTwoNumbers(a, b))
                                          .isInstanceOf(ArithmeticException.class);
// This test verifies that,When the denominator is 0, the method divideTwoNumbers(a, b) throws an ArithmeticException
    }

}
/*
() -> divideTwoNumbers(a, b) → calling the method

assertThatThrownBy(...) → expecting an exception

.isInstanceOf(...) → checking exception type
 */
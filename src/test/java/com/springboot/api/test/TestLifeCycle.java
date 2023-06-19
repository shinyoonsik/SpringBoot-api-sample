package com.springboot.api.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll(){
        System.out.println();
        System.out.println("## BeforeAll Annotation ##");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("## AfterAll Annotation ##");
        System.out.println();
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("## BeforeEach Annotation ##");
    }

    @AfterEach
    void afterEach(){
        System.out.println("## AfterEach Annotation ##");
    }

    @Test
    @DisplayName("Test case 1")
    void test1(){
        System.out.println();
        System.out.println("테스트1 시작~");
        System.out.println();
    }

    @Test
    @DisplayName("Test case 2")
    void test2(){
        System.out.println();
        System.out.println("테스트2 시작~");
        System.out.println();
    }

    @Test
    @Disabled
    void test3(){
        System.out.println("for disabled");
    }

}

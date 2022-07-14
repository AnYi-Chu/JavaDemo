package com.atguigu.spring.aop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import javax.annotation.Resource;

@SpringBootTest
public class TestAop {
    @Resource
    private CalcService calcService;

    /*
     * 正常执行：@Before->@After->@AfterReturning
     * 异常执行：@Before->@After->@AfterThrowing
     * */
    @Test
    public void testAop4() {
        System.out.println("spring版本：" + SpringVersion.getVersion() + "\t" + "springboot版本：" + SpringBootVersion.getVersion());
        System.out.println();
        calcService.div(10, 0);
    }

    /*
     * 正常执行：@Before->@AfterReturning->@After
     * 异常执行：@Before->@AfterThrowing->@After
     * */
    @Test
    public void testAop5() {
        System.out.println("spring版本：" + SpringVersion.getVersion() + "\t" + "springboot版本：" + SpringBootVersion.getVersion());
        System.out.println();
        calcService.div(10, 2);
    }
}

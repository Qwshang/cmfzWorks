package com.baizhi.test;

import io.goeasy.GoEasy;
import org.junit.Test;

public class TestGoEasy {

    @Test
    public void go(){
        GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-798823e7d33c4c098f87da28c613fd78");
        goEasy.publish("qwshang", "你好 傻蛋!");




    }

}

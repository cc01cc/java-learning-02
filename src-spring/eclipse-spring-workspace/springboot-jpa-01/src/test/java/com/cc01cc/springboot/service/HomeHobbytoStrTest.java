/**  
 *   Copyright 2021 cc01cc
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

/**
*   @Title: HomeHobbytoStrTest.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-12-18 
*/

package com.cc01cc.springboot.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cco1cc.springboot.SpringJpaInitialApplication;
import com.cco1cc.springboot.service.HomeServiceImpl;

/**
 * @author       cc01cc
 * @date         2021-12-18
 * @Description: TODO
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringJpaInitialApplication.class)
//@Transactional
public class HomeHobbytoStrTest {
//    public HomeHobbytoStrTest() {
//        System.out.println("This is HomeHobbytoStrTest.class");
//    }
    String result;
     @Autowired
    private HomeServiceImpl homeServiceImpl;

    // @MockBean
    // private CreditService creditService;
    @Test
    public void testHomeHobbytoStr() {
        System.out.println("test start");
        String expectedResult = "篮球, 游戏";
        byte   b              = 9;
//        System.out.println("byte b = "+b);
        result         = homeServiceImpl.hobbyToStr(b);
        System.out.println(result);
        assertEquals(expectedResult, result);
    }
//    @After(value = "")
//    public void printResult() {
//        System.out.println("输出result"+result);
//    }
}

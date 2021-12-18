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

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cco1cc.springboot.service.HomeService;

/**
 * @author cc01cc
 * @date 2021-12-18 
 * @Description: TODO
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HomeHobbytoStrTest {
    
    @Autowired
    private HomeService homeService;
//    @MockBean
//    private CreditService creditService;
    public void testHomeHobbytoStr() {
    String expectedResult = "游戏";
    String result = homeService.
    }
    

}

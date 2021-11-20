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
*   @Title: LoginController.java
*   @Description: TODO 
*   @author cc01cc
*   @date 2021-11-20 
*/

package com.cc01cc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cc01cc
 * @Description: 登录功能
 * 
 */

@Controller
public class LoginController {

    /**
     * @Title: returnLogin
     * @Description: 返回 login.html 视图
     *      @GetMapping 仅接收 Get 请求，
     *      相当于 @RequestMapping(method = RequestMethod.GET)
     * @param @return
     * @return String
     * @throws
     */
    @GetMapping({"/login"})
    public String returnLogin() {
        System.out.println("This is returnLogin()");
        return "login";
    }
    
    @GetMapping("/")
    public String returnIndex() {
        return "index";
    }
    
}

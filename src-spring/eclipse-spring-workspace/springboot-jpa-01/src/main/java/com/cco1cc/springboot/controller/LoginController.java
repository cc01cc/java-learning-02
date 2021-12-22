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
*   @date 2021-12-17 
*/

package com.cco1cc.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cco1cc.springboot.entity.UserInfo;
import com.cco1cc.springboot.service.LoginService;

/**
 * @author       cc01cc
 * @date         2021-12-17
 * @Description: TODO
 * 
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user_info", new UserInfo());
        return "login";
    }

    @Autowired
    LoginService loginService;
    
    @RequestMapping("/login_userInfo")
    public String login(
            HttpSession session,
            @ModelAttribute("user_info") UserInfo userInfo,
            Model model
            ) {
        String loginInfo = "登录失败";
        if(loginService.checkLoginInfo(userInfo)) {
            session.setAttribute("user_account", userInfo.getUserAccount());
            return "forward:/home";
        }
        model.addAttribute("login_info", loginInfo);
//        System.out.println(model);
        return "login";
    }
}

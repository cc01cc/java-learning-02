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
*   @Title: RegisterController.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-12-17 
*/

package com.cco1cc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cco1cc.springboot.entity.UserInfo;
import com.cco1cc.springboot.service.HomeService;

/**
 * @author       cc01cc
 * @date         2021-12-17
 * @Description: TODO
 * 
 */
@Controller
public class RegisterController {

    @RequestMapping("/register")
    public String getRegister(Model model) {
        // 必须对 user_info 进行初始化
        model.addAttribute("user_info", new UserInfo());
        System.out.println("This is getRegister()");
        return "register";
    }

    // TODO: @Autowired 注解无法在函数内部使用 why?
    @Autowired
    HomeService homeService;

    @RequestMapping("/register_userInfo")
    public String register(@ModelAttribute("user_info") UserInfo userInfo, Model model) {
        String registerInfo;

        if(homeService.addUserInfo(userInfo)) registerInfo = "账户注册成功";
        else registerInfo = "账户注册失败";
        model.addAttribute("register_info", registerInfo);
        return "register";
    }
}

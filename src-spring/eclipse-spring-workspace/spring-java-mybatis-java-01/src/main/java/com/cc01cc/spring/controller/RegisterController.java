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
*   @date 2021-11-21 
*/

package com.cc01cc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cc01cc.spring.pojo.User;
import com.cc01cc.spring.service.RegisterService;

/**
 * @author cc01cc
 * @date 2021-11-21
 * @Description: TODO
 * 
 */
@Controller
public class RegisterController {

    @GetMapping("/register")
    public String returnRegister(Model model) {
        model.addAttribute("user_view", new User());
        System.out.println("This is returnRegister()");
        return "register";
    }

    @Autowired
    RegisterService registerService;

    @PostMapping("/register_context_post")
    public String register(
            @ModelAttribute("user_context") User user,
            Model model) {
        String registerInfo;
        // TODO 此处传参只有账户密码，不能自定义用户容量，之后需要调整
        if (registerService.register(user)) {
            registerInfo = "注册成功，返回";
            // System.out.println(registerInfo);
            model.addAttribute("register_info", registerInfo);
            model.addAttribute("register_info_login","登录");
        } else {
            registerInfo = "注册失败，请重新注册";
            model.addAttribute("register_info", registerInfo);
        }
        model.addAttribute("user_view", new User());
        return "register";
    }
}

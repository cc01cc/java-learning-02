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
*   @Title: BaseController.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-11-22 
*/

package com.cc01cc.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author cc01cc
 * @date 2021-11-22
 * @Description: TODO
 * 
 */
@Controller
public class BaseController {

    // 实测 @ModelAttirbute 不能返回视图等操作
    // @ModelAttribute
    // public String isLogin(HttpSession session) {
    // if (session.getAttribute("userId") == null) {
    // System.out.println("BaseController : " + session.getAttribute("userId"));
    // return "redirect:/login";
    // }
    // return "redirect:/home";
    // }

    // 暂时使用 ModelAttribute 做拦截器，使用 userId 验证
    // 之后可以改用 Token
    @ModelAttribute
    public void isLogin(HttpSession session) throws Exception {
        if (session.getAttribute("user_id") == null) {
            throw new Exception("权限不足");
        }
    }
}

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

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cc01cc.spring.pojo.User;
import com.cc01cc.spring.service.LoginService;

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
     * @GetMapping 仅接收 Get 请求， 相当于 @RequestMapping(method = RequestMethod.GET)
     * @param @return
     * @return String
     * @throws
     */
    @GetMapping({ "/login" })
    public String returnLogin(Model model) {
        /*
         * Model 的实例通过 addAttribute 绑定数据， 将 User 实例传入到视图 "user_view" 中 确保
         * "user_view" 和 视图中的 object 等类型一致，
         */
        model.addAttribute("user_view", new User());
        System.out.println("This is returnLogin()");
        return "login";
    }

    @Autowired
    LoginService loginService;

    @PostMapping("/login_context_post")
    public String login(

            // @ModelAttribute("file_key") File file01,
            /*
             * 
             * @ModelAttribute 将所有的请求内容进行封装并按类型匹配传入 user01 中， 以键值对的形式存入 Model
             * 实例中，user_key 为键，user01 为值
             * 
             * TODO ?@ModelAttribute 可以将键值对存入 Model 实例中， 和我们自己创建的 Model
             * 实例是同一个对象吗？ 为什么我们手动创建的 Model 实例可以引用 @ModelAttribute 自动创建的实例 TODO
             * TODO 猜测@ModelAttribute 接收请求，并将其封装入 Model 实例中，然后 spring 框架再将 Model
             * 实例注入到参数中
             * 
             * 相似效果的有
             * 
             * @ModelAttribute User user01 (以 user 为键，即 User类 首字母小写，user01 为值)
             * Model model 无法接收请求
             * 
             * TODO ?model.addAttribute() 可以向视图层传入参数，那如果既想给 model 添加键值对，
             * 又想向视图层传递参数，是否会造成冲突
             */
            HttpSession session,
            @ModelAttribute("user_key") User user01,
            Model model) {
        // System.out.println("file_key : " + model.getAttribute("file_key"));
        // User user01 = new User();
        // model.addAttribute("user_view", user01);
        // System.out.println("user_key : " + model.getAttribute("user_key"));

        if (loginService.checkIdentity(user01)) {
            // 将userId添加到session中
            session.setAttribute("user_id", user01.getUserId());
            // 设置当前目录为 user01.getUserId()
            session.setAttribute("parent_dir_id", user01.getUserId());
            return "redirect:/home";
        } else {
            model.addAttribute("login_error", "账户或密码错误，请重新输入");
            /*
             * 返回 "login" 时会将 model 对象传入，Spring 框架并不会自动注入 Model 实例
             * 所以此处需要手动插入 "user_view" 键值对
             */
            model.addAttribute("user_view", new User());
            return "forward:/login";
        }
    }
}

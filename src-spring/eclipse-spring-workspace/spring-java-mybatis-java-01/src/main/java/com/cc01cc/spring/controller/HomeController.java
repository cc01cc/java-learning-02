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
*   @Title: HomeController.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-11-22 
*/

package com.cc01cc.spring.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc01cc.spring.pojo.File;

/**
 * @author cc01cc
 * @date 2021-11-22
 * @Description: TODO
 * 
 */
// TODO 本来想要将 Mapping 写在类上，但这样要如何返回 home 视图
// TODO 再类上使用 GetMapping 报错，这是为什么
// @RequestMapping("/home")
@Controller
public class HomeController extends BaseController{

    // 定义从前端传回，后端计算的md5码
    private String md5FromFront = null;
    private String md5FromEnd = null;

    // 定义需要处理的文件
    File fileTodo = new File();

    
    
    @GetMapping("/home")
    public String returnHome() {
        return "home";
    }
    
    @ResponseBody
    @PostMapping("/upload-md5-front")
    public String uploadMd5Front(@RequestBody Map<String,String> md5) {
        md5FromFront = md5.get("md5");
        System.out.println(md5FromFront);
        
//        System.out.println(md5.get("md5"));
        return "true";
    }

}

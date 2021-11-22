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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
public class HomeController extends BaseController {

    // 定义从前端传回，后端计算的md5码
    private String md5FromFront = null;
    private String md5FromEnd = null;

    // 定义需要处理的文件
    File fileTodo = new File();

    @GetMapping("/home")
    public String returnHome() {
        return "home";
    }

    // 血泪史：一定要导入 jackson-databind 包，否则无法解析 json 数据
    // 没有导入，并不会有任何报错和提示，只是无法接收参数
    @ResponseBody
    @PostMapping("/upload-md5-front")
    public String uploadMd5Front(@RequestBody Map<String, String> md5) {
        md5FromFront = md5.get("md5");
        System.out.println(md5FromFront);

        // System.out.println(md5.get("md5"));
        return "true";
    }

      
    // 吸取上边的经验，乖乖导入 commons-fileupload 包（不想试不导入的情况了，累了）
    @PostMapping("/file_upload")
    public String fileUpload(
            HttpServletRequest request,
            HttpSession session,
            // @RequestParam 内的值与 前端标签的 name 属性一致
            @RequestParam("file-context") MultipartFile fileContext,
            Model model
            ) {
                if(!fileContext.isEmpty()) {
                    String md5FromEnd = DigestUtils.md5DigestAsHex(fileContext.getBytes());
                    if(md5FromEnd!=md5FromFront) {
                        model.addAttribute("file_upload_info", "文件传输失败");
                        return "home";
                    }
                    String path = "T:\\zeolab\temp";
                    fileTodo.setFileName(fileContext.getOriginalFilename());
//                    fileTodo.setFileId(0);
                    fileTodo.setFileMD5(path)
                    
                }
            return "home";
    }
}

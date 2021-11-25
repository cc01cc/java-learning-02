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

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cc01cc.spring.pojo.Dir;
import com.cc01cc.spring.pojo.File;
import com.cc01cc.spring.pojo.User;
import com.cc01cc.spring.service.ProcessDirService;
import com.cc01cc.spring.service.ProcessFileService;
import com.cc01cc.spring.service.ProcessUserService;
import com.cc01cc.spring.util.IdMakerUtil;

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

    @Autowired
    ProcessFileService processFileService;

    @Autowired
    ProcessDirService processDirService;
    
    @Autowired
    ProcessUserService processUserService;

    // TODO 此处的变量作用域跨方法，可以想办法继续降低耦合
    // 定义从前端传回，后端计算的md5码
    private String md5FromFront = null;
    private String md5FromEnd = null;
    private int fileSizeFromFront = 0;

    // 定义需要处理的文件
    File fileTodo = new File();
    Dir dir = new Dir();
    List<Dir> pwdList = new ArrayList<>();
    User user = new User();

    
    // TODO 对于一些不需要的输出语句进行处理
    // TODO 将部分语句转化为切面类进行日志记录等转变
    @RequestMapping("/home")
    public String returnHome(

            HttpSession session,
            Model model

    ) {
        
        // 显示用户空间
        String userId = session.getAttribute("user_id").toString();
        
        User user = processUserService.findUserByUserId(userId);
        model.addAttribute("user_room_used", user.getUserRoomUsed());
        model.addAttribute("user_room_total", user.getUserRoomTotal());
        
        // 当前目录路径获取
        System.out.println(session.getAttribute("parent_dir_id").toString());
        // pwdList.add(processDirService.findDirById(session.getAttribute("parent_dir_id").toString(),session.getAttribute("user_id").toString()));
        // pwdList =
        // processDirService.listDirPath(session.getAttribute("parent_dir_id").toString());
        pwdList = processDirService
                .getDirPathList(session.getAttribute("parent_dir_id").toString());

        System.out.println("pwd_list : " + pwdList);
        model.addAttribute("pwd_list", pwdList);

        
        // 显示文件列表，目录列表
        List<File> fileList = processFileService
                .listFileByParentId(session.getAttribute("parent_dir_id").toString());

        List<Dir> dirList = processDirService
                .listDirByParentId(session.getAttribute("parent_dir_id").toString());

        model.addAttribute("file_list", fileList);
        model.addAttribute("dir_list", dirList);
        // model.addAttribute("dir_list_separator", " > ");
        return "home";
    }

    // 血泪史：一定要导入 jackson-databind 包，否则无法解析 json 数据
    // 没有导入，并不会有任何报错和提示，只是无法接收参数
    @ResponseBody
    @PostMapping("/home/upload-md5-front")
    public String uploadMd5Front(
            @RequestBody Map<String, String> map,
            HttpSession session
            ) {
        // TODO 此处 代码有重复可优化
        String userId = session.getAttribute("user_id").toString();
        User user = processUserService.findUserByUserId(userId);
        md5FromFront = map.get("md5");
        System.out.println("md5FromFront : " + md5FromFront);
        fileSizeFromFront = Integer.parseInt(map.get("file_size"));
        System.out.println("fileSizeFromFront = " + fileSizeFromFront);
        if(processUserService.checkUserRoomUsed(userId, fileSizeFromFront)) {
         // System.out.println(md5.get("md5"));
//            return "{result:true}";
            return "true";
        }
        // 前端 json 数据处理还没学会，就先返回null吧
        return null;
        
    }

    // 吸取上边的经验，乖乖导入 commons-fileupload 包（不想试不导入的情况了，累了）
    // TODO 此处 file_upload 的处理过于复杂，需要尝试使用 service，构造函数等方法进行分解
    // TODO 虽然 md5 码的计算，相同文件不会重复占用空间，但是文件仍然重复上传，这个改改挺简单的，但这儿代码已经如此冗杂，下次集中调整
    @PostMapping("/file_upload")
    public String fileUpload(

            HttpServletRequest request,
            HttpSession session,
            // @RequestParam 内的值与 前端标签的 name 属性一致
            @RequestParam("file-context") MultipartFile fileContext,
            Model model

    ) throws IOException {

        if (!fileContext.isEmpty()) {
            md5FromEnd = DigestUtils.md5DigestAsHex(fileContext.getBytes());
            System.out.println("md5FromEnd : " + md5FromEnd);

            if (!md5FromEnd.equals(md5FromFront)) {
                model.addAttribute("file_upload_info", "文件传输失败");
                return "home";
            }
            // TODO 此处 代码有重复可优化
            String userId = session.getAttribute("user_id").toString();
            String path = "T:" + java.io.File.separator + "zeolab" + java.io.File.separator
                    + "temp";

            fileTodo.setFileName(fileContext.getOriginalFilename());
            fileTodo.setFileId(IdMakerUtil.makeId(session.getAttribute("user_id").toString()));
            fileTodo.setFileMD5(md5FromEnd);

            // TODO addFileUserLink(fileTodo.getFileMD5)
            fileTodo.setFileSharePassword(null);
            fileTodo.setFileParentId(session.getAttribute("parent_dir_id").toString());
            // TODO 这儿 fileuserid 类型冲突了，设计时失误
            fileTodo.setFileUserId(Integer.parseInt(session.getAttribute("user_id").toString()));
            System.out.println("Todo" + fileTodo);

            java.io.File filePath = new java.io.File(
                    path + java.io.File.separator + fileTodo.getFileMD5());
            // 之前还考虑是不是需要添加文件后缀，但是感觉不用，下载文件时，会将文件名重新赋值
            fileTodo.setFileLocalStore(filePath.getPath());

            fileContext.transferTo(filePath);
            fileTodo.setFileSize(fileSizeFromFront);
            processFileService.saveFile(fileTodo);
            processUserService.updateUserRoomUsed(userId, fileSizeFromFront);
            
            // TODO 相同文件应该避免重复上传
            model.addAttribute("file_upload_info", "文件传输成功");

        } else {
            model.addAttribute("file_upload_info", "请选择文件");
        }
        return "forward:/home";
    }

    @PostMapping("/dir_make")
    public String makeDir(

            HttpSession session,
            HttpServletRequest servletRequest,
            @RequestParam("dir-name") String dirNameFromFront,
            Model model

    ) throws Exception {

        if (dirNameFromFront != null) {
            dir.setDirId(IdMakerUtil.makeId(session.getAttribute("user_id").toString()));
            // servletRequest.getCharacterEncoding();
            servletRequest.setCharacterEncoding("UTF-8");
            System.out.println("dirNameFromFront : " + dirNameFromFront);
            dir.setDirName(dirNameFromFront);
            dir.setDirParentId(session.getAttribute("parent_dir_id").toString());

            if (processDirService.saveDir(dir)) {
                model.addAttribute("dir_make_info", "目录创建成功");
            }
        } else {
            model.addAttribute("dir_make_info", "目录创建失败");
        }

        return "forward:/home";
    }

    @RequestMapping("/file-download")
    public ResponseEntity<byte[]> downloadFile(

            HttpServletRequest request,
            @RequestParam("file_context_id") String fileId,
            @RequestHeader("User-Agent") String userAgent

    ) throws IOException {

        File   file     = processFileService.findFileById(fileId);
        String filePath = file.getFileLocalStore();

        java.io.File               fileDownload = new java.io.File(filePath);
        ResponseEntity.BodyBuilder builder      = ResponseEntity.ok();
        builder.contentLength(fileDownload.length());
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);

        // 书上说 IE 不兼容，网上说 FIREFOX 不兼容
        // 实测 IE, EDGE, CHROME, FIREFOX 都兼容
        // 反而是 加了 UTF-8 后无法显示中文了
        // 代码也都留着吧，做备用
        String fileName = URLEncoder.encode(file.getFileName(), "UTF-8");
        // String fileName = URLEncoder.encode(file.getFileName(), "UTF-8");
        // if (userAgent.indexOf("MSIE") > 0) {
        // builder.header("Content-Disposition", "attachment; filename=" +
        // file.getFileName());
        // } else {
        // builder.header("Content-Disposition",
        // "attachment; filename* = UTF-8''" + file.getFileName());
        // }
        // builder.header("Content-Disposition",
        // "attachment; filename*=UTF-8''" + file.getFileName());
        // builder.header("Content-Disposition",
        // "attachment; filename=" + file.getFileName());
        builder.header("Content-Disposition",
                "attachment; filename=" + fileName);
        return builder.body(FileUtils.readFileToByteArray(fileDownload));
    }

    @RequestMapping("/file-delete")
    public String deleteFile(
            HttpServletRequest request,
            @RequestParam("file_context_id") String fileId

    ) {
        processFileService.deleteFileById(fileId);
        return "forward:/home";
    }

    @RequestMapping("/dir-delete")
    public String deleteDir(

            HttpServletRequest request,
            @RequestParam("dir_context_id") String dirId

    ) {
        processDirService.deleteDirById(dirId);
        return "forward:/home";
    }

    @RequestMapping("/dir_forward")
    public String forwrdDir(

            HttpSession session,
            @RequestParam("dir_context_id") String dirId

    ) {
        session.setAttribute("parent_dir_id", dirId);
        return "forward:/home";
    }

    @RequestMapping("/file_name_update")
    public String updateFileName(

            HttpSession session,
            @RequestParam("new_file_name") String newFileName,
            @RequestParam("file_context_id") String fileId

    ) {
        processFileService.updateFileName(fileId, newFileName);
        return "forward:/home";
    }

    @RequestMapping("/dir_name_update")
    public String updateDirName(

            HttpSession session,
            @RequestParam("new_dir_name") String newDirName,
            @RequestParam("dir_context_id") String dirId) {

        processDirService.updateDirName(dirId, newDirName);
        return "forward:/home";
    }

    @RequestMapping("/file-share")
    public String shareFile(

            HttpSession session,
            @RequestParam("file_context_id") String fileId,
            // @RequestParam("file_share_password") String fileSharePassword,
            // @RequestParam("file_context") File file,
            Model model

    ) {
        // TODO 此处有一个逻辑错误，假设所有创建失败的原因是已经拥有分享码
        // 已经拥有分享码将自动跳转分享页面，并自动填充分享码
        // String fileId = file.getFileId();
        // String fileSharePassword = file.getFileSharePassword();
        if (processFileService.makeFileSharePassword(fileId)) {
            return "forward:/home";
        } else {
            String fileSharePassword = processFileService.findFileById(fileId)
                    .getFileSharePassword();
            model.addAttribute("share_password_auto", fileSharePassword);
            return "forward:/share";
        }

    }
}

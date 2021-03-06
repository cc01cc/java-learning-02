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
// TODO ??????????????? Mapping ??????????????????????????????????????? home ??????
// TODO ??????????????? GetMapping ????????????????????????
// @RequestMapping("/home")
@Controller
public class HomeController extends BaseController {

    @Autowired
    ProcessFileService processFileService;

    @Autowired
    ProcessDirService processDirService;
    
    @Autowired
    ProcessUserService processUserService;

    // TODO ?????????????????????????????????????????????????????????????????????
    // ???????????????????????????????????????md5???
    private String md5FromFront = null;
    private String md5FromEnd = null;
    private int fileSizeFromFront = 0;

    // ???????????????????????????
    File fileTodo = new File();
    Dir dir = new Dir();
    List<Dir> pwdList = new ArrayList<>();
    User user = new User();

    
    // TODO ????????????????????????????????????????????????
    // TODO ????????????????????????????????????????????????????????????
    @RequestMapping("/home")
    public String returnHome(

            HttpSession session,
            Model model

    ) {
        
        // ??????????????????
        String userId = session.getAttribute("user_id").toString();
        
        User user = processUserService.findUserByUserId(userId);
        model.addAttribute("user_room_used", user.getUserRoomUsed());
        model.addAttribute("user_room_total", user.getUserRoomTotal());
        
        // ????????????????????????
        System.out.println(session.getAttribute("parent_dir_id").toString());
        // pwdList.add(processDirService.findDirById(session.getAttribute("parent_dir_id").toString(),session.getAttribute("user_id").toString()));
        // pwdList =
        // processDirService.listDirPath(session.getAttribute("parent_dir_id").toString());
        pwdList = processDirService
                .getDirPathList(session.getAttribute("parent_dir_id").toString());

        System.out.println("pwd_list : " + pwdList);
        model.addAttribute("pwd_list", pwdList);

        
        // ?????????????????????????????????
        List<File> fileList = processFileService
                .listFileByParentId(session.getAttribute("parent_dir_id").toString());

        List<Dir> dirList = processDirService
                .listDirByParentId(session.getAttribute("parent_dir_id").toString());

        model.addAttribute("file_list", fileList);
        model.addAttribute("dir_list", dirList);
        // model.addAttribute("dir_list_separator", " > ");
        return "home";
    }

    // ??????????????????????????? jackson-databind ???????????????????????? json ??????
    // ???????????????????????????????????????????????????????????????????????????
    @ResponseBody
    @PostMapping("/home/upload-md5-front")
    public String uploadMd5Front(
            @RequestBody Map<String, String> map,
            HttpSession session
            ) {
        // TODO ?????? ????????????????????????
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
        // ?????? json ???????????????????????????????????????null???
        return null;
        
    }

    // ???????????????????????????????????? commons-fileupload ????????????????????????????????????????????????
    // TODO ?????? file_upload ?????????????????????????????????????????? service????????????????????????????????????
    // TODO ?????? md5 ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    @PostMapping("/file_upload")
    public String fileUpload(

            HttpServletRequest request,
            HttpSession session,
            // @RequestParam ???????????? ??????????????? name ????????????
            @RequestParam("file-context") MultipartFile fileContext,
            Model model

    ) throws IOException {

        if (!fileContext.isEmpty()) {
            md5FromEnd = DigestUtils.md5DigestAsHex(fileContext.getBytes());
            System.out.println("md5FromEnd : " + md5FromEnd);

            if (!md5FromEnd.equals(md5FromFront)) {
                model.addAttribute("file_upload_info", "??????????????????");
                return "home";
            }
            // TODO ?????? ????????????????????????
            String userId = session.getAttribute("user_id").toString();
            String path = "T:" + java.io.File.separator + "zeolab" + java.io.File.separator
                    + "temp";

            fileTodo.setFileName(fileContext.getOriginalFilename());
            fileTodo.setFileId(IdMakerUtil.makeId(session.getAttribute("user_id").toString()));
            fileTodo.setFileMD5(md5FromEnd);

            // TODO addFileUserLink(fileTodo.getFileMD5)
            fileTodo.setFileSharePassword(null);
            fileTodo.setFileParentId(session.getAttribute("parent_dir_id").toString());
            // TODO ?????? fileuserid ?????????????????????????????????
            fileTodo.setFileUserId(Integer.parseInt(session.getAttribute("user_id").toString()));
            System.out.println("Todo" + fileTodo);

            java.io.File filePath = new java.io.File(
                    path + java.io.File.separator + fileTodo.getFileMD5());
            // ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            fileTodo.setFileLocalStore(filePath.getPath());

            fileContext.transferTo(filePath);
            fileTodo.setFileSize(fileSizeFromFront);
            processFileService.saveFile(fileTodo);
            processUserService.updateUserRoomUsed(userId, fileSizeFromFront);
            
            // TODO ????????????????????????????????????
            model.addAttribute("file_upload_info", "??????????????????");

        } else {
            model.addAttribute("file_upload_info", "???????????????");
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
                model.addAttribute("dir_make_info", "??????????????????");
            }
        } else {
            model.addAttribute("dir_make_info", "??????????????????");
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

        // ????????? IE ????????????????????? FIREFOX ?????????
        // ?????? IE, EDGE, CHROME, FIREFOX ?????????
        // ????????? ?????? UTF-8 ????????????????????????
        // ?????????????????????????????????
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
        // TODO ???????????????????????????????????????????????????????????????????????????????????????
        // ???????????????????????????????????????????????????????????????????????????
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

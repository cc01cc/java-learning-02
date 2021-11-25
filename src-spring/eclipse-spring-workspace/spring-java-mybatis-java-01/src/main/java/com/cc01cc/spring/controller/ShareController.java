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
*   @Title: ShareController.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-11-25 
*/

package com.cc01cc.spring.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cc01cc.spring.pojo.File;
import com.cc01cc.spring.service.ProcessFileService;

/**
 * @author cc01cc
 * @date 2021-11-25
 * @Description: TODO
 * 
 */
@Controller
public class ShareController {

    @Autowired
    ProcessFileService processFileService;

    @RequestMapping("/share")
    public String returnShare() {
        return "share";
    }

    @PostMapping("/share/download-file-from-share")
    public ResponseEntity<byte[]> downloadFileFromShare(

            @RequestParam("share_password") String fileSharePassword,
            HttpServletRequest request,
            @RequestHeader("User-Agent") String userAgent

    ) throws Exception {
        File file = processFileService.findFileBySharePassword(fileSharePassword);
        System.out.println("file - Share : " + file);
        String filePath = file.getFileLocalStore();

        java.io.File               fileDownload = new java.io.File(filePath);
        ResponseEntity.BodyBuilder builder      = ResponseEntity.ok();
        builder.contentLength(fileDownload.length());
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);

        String fileName = URLEncoder.encode(file.getFileName(), "UTF-8");

        builder.header("Content-Disposition",
                "attachment; filename=" + fileName);
        return builder.body(FileUtils.readFileToByteArray(fileDownload));
    }
}

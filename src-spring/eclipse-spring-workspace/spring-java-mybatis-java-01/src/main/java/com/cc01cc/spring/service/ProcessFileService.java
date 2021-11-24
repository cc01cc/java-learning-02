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
*   @Title: ProcessFileService.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-11-23 
*/

package com.cc01cc.spring.service;

import java.util.List;

import com.cc01cc.spring.pojo.File;

/**
 * @author cc01cc
 * @date 2021-11-23
 * @Description: TODO
 * 
 */
public interface ProcessFileService {

    public boolean saveFile(File fileTodo);

    public File findFileById(String fileId);

    public boolean deleteFileById(String fileId);

    public List<File> listFileByParentId(String parentId);

    public boolean updateFileName(String fileId, String newFileName);

    // 针对没有分享码的文件创建分享码
    public boolean makeFileSharePassword(String fileId);

    // TODO 更新分享码，保留功能
    // public boolean updateFileSharePassword(String fileId);
    
    public File findFileBySharePassword(String fileSharePassword);
}

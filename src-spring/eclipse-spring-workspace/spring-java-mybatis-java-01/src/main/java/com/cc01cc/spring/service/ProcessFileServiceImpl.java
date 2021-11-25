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
 *   @Title: ProcessFileServiceImpl.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-11-23 
 */  

package com.cc01cc.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc01cc.spring.mapper.BaseMapper;
import com.cc01cc.spring.pojo.File;
import com.cc01cc.spring.pojo.User;
import com.cc01cc.spring.util.IdMakerUtil;

/**
 * @author cc01cc
 * @date 2021-11-23 
 * @Description: TODO
 * 
 */
@Service
public class ProcessFileServiceImpl implements ProcessFileService{

    

    IdMakerUtil idMakerUtil = new IdMakerUtil();
    /** 
     * <p>Title: makeFileSharePassword</p>
     * <p>Description: </p>
     * @param fileId
     * @return
     * @see com.cc01cc.spring.service.ProcessFileService#makeFileSharePassword(java.lang.String)
     *
     */
    @Override
    public boolean makeFileSharePassword(String fileId) {
        File file = baseMapper.findFileByFileId(fileId);
        if(file.getFileSharePassword()==null) {
            String newFileSharePassword = idMakerUtil.makeId(file.getFileUserId());
            file.setFileSharePassword(newFileSharePassword);
            baseMapper.updateFileSharePassword(file);
            return true;
        }
        return false;
    }

    /** 
     * <p>Title: findFileBySharePassword</p>
     * <p>Description: </p>
     * @param fileSharePassword
     * @return
     * @see com.cc01cc.spring.service.ProcessFileService#findFileBySharePassword(java.lang.String)
     *
     */
    @Override
    public File findFileBySharePassword(String fileSharePassword) {
        File file = baseMapper.findFileByFileSharePassword(fileSharePassword);
        String fileMD5 = file.getFileMD5();
        file.setFileLocalStore(baseMapper.findFileByMD5(fileMD5).getFileLocalStore());
        return file;
    }

    @Autowired
    BaseMapper baseMapper;
    
    @Override
    public boolean saveFile(File fileTodo) {
        if(!existFile(fileTodo.getFileMD5())) {
            baseMapper.saveFileLocal(fileTodo);
            System.out.println("saveFileLocal : " + fileTodo);
        }
        addFileUserLink(fileTodo);
        baseMapper.saveFileUser(fileTodo);
        return false;
    }
    
    @Override
    public File findFileById(String fileId) {
        
        File file = baseMapper.findFileByFileId(fileId);
        File fileLocal = baseMapper.findFileByMD5(file.getFileMD5());
        file.setFileLocalStore(fileLocal.getFileLocalStore());
        return file;
    }
    
    @Override
    public boolean deleteFileById(String fileId) {
        File file = baseMapper.findFileByFileId(fileId);
        
        baseMapper.deleteFileByFileId(fileId);
        // TODO fileUserId 类型需要优化
        int fileUserId = file.getFileUserId();
        
        User user = baseMapper.findUserById(String.valueOf(fileUserId));
        System.out.println("file : "+baseMapper.findFileByMD5(file.getFileMD5()));
        System.out.println("fileSize : "+baseMapper.findFileByMD5(file.getFileMD5()).getFileSize());
        user.setUserRoomUsed(user.getUserRoomUsed()-baseMapper.findFileByMD5(file.getFileMD5()).getFileSize());
        baseMapper.updateUserRoomUsed(user);
        
        file.setFileUserLink(baseMapper.findFileByMD5(file.getFileMD5()).getFileUserLink());
        System.out.println("deleteFile : " + file);
        int fileUserLink = file.getFileUserLink() - 1;
        if(fileUserLink==0) {
            String filePath = baseMapper.findFileByMD5(file.getFileMD5()).getFileLocalStore();
            System.out.println("deleteFile - filePath : " + filePath);
            java.io.File fileDelete = new java.io.File(filePath);
            if(fileDelete.delete())
            {
                baseMapper.deleteFileByFileMD5(file.getFileMD5());
                return true;
            }
        }else {
            file.setFileUserLink(fileUserLink);
            baseMapper.updateFileUserLink(file);
            return true;
        }
        return false;
    };
    
    /** 
     * <p>Title: listFileByParentId</p>
     * <p>Description: </p>
     * @param parentId
     * @return
     * @see com.cc01cc.spring.service.ProcessFileService#listFileByParentId(java.lang.String)
     *
     */
    @Override
    public List<File> listFileByParentId(String parentId) {
        
        List<File> fileList = baseMapper.findFileByParentId(parentId);
        
        return fileList;
    }

    public boolean existFile(String md5) {
        File file = null;
                file = baseMapper.findFileByMD5(md5);
                System.out.println("exitstFile : " + file);
        if(file==null) {
            return false;
        }else {
            return true;
        }
    }
    
    public boolean addFileUserLink(File file) {
        
        file = baseMapper.findFileByMD5(file.getFileMD5());
        System.out.println("addFileUserLink : " + file);
        file.setFileUserLink(file.getFileUserLink()+1);
        boolean result = baseMapper.updateFileUserLink(file);
        System.out.println("addFileUserLink : " + result);
        return result;
    }
    
    @Override
    public boolean updateFileName(String fileId, String newFileName) {
        File file = baseMapper.findFileByFileId(fileId);
        file.setFileName(newFileName);
        boolean result = baseMapper.updateFileName(file);
        return result;
    }
}

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
 *   @Title: SaveFileServiceImpl.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-11-23 
 */  

package com.cc01cc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc01cc.spring.mapper.BaseMapper;
import com.cc01cc.spring.pojo.File;

/**
 * @author cc01cc
 * @date 2021-11-23 
 * @Description: TODO
 * 
 */
@Service
public class SaveFileServiceImpl implements SaveFileService{

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
}

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
 *   @Title: ProcessDirServiceImpl.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-11-23 
 */  

package com.cc01cc.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc01cc.spring.mapper.BaseMapper;
import com.cc01cc.spring.pojo.Dir;

/**
 * @author cc01cc
 * @date 2021-11-23 
 * @Description: TODO
 * 
 */
@Service
public class ProcessDirServiceImpl implements ProcessDirService{

    /** 
     * <p>Title: saveDir</p>
     * <p>Description: </p>
     * @param dir
     * @return
     * @see com.cc01cc.spring.service.ProcessDirService#saveDir(com.cc01cc.spring.pojo.Dir)
     *
     */
    
    @Autowired
    BaseMapper baseMapper;
    
    @Override
    public boolean saveDir(Dir dir) {
        boolean result = baseMapper.saveDirUser(dir);
        return result;
    }

    /** 
     * <p>Title: findDirById</p>
     * <p>Description: </p>
     * @param dirId
     * @return
     * @see com.cc01cc.spring.service.ProcessDirService#findDirById(java.lang.String)
     *
     */
    @Override
    public Dir findDirById(String dirId) {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * <p>Title: deleteDirById</p>
     * <p>Description: </p>
     * @param dirId
     * @return
     * @see com.cc01cc.spring.service.ProcessDirService#deleteDirById(java.lang.String)
     *
     */
    @Override
    public boolean deleteDirById(String dirId) {
        // TODO Auto-generated method stub
        return false;
    }

    /** 
     * <p>Title: listFileByParentId</p>
     * <p>Description: </p>
     * @param parentId
     * @return
     * @see com.cc01cc.spring.service.ProcessDirService#listFileByParentId(java.lang.String)
     *
     */
    @Override
    public List<Dir> listDirByParentId(String parentId) {
        List<Dir> dirList = baseMapper.findDirByParentId(parentId);
        return dirList;
    }
    

    
}

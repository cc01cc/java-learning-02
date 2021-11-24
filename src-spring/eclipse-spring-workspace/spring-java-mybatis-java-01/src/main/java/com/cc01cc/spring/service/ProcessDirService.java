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
 *   @Title: ProcessDirService.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-11-23 
 */  

package com.cc01cc.spring.service;

import java.util.List;

import com.cc01cc.spring.pojo.Dir;

/**
 * @author cc01cc
 * @date 2021-11-23 
 * @Description: TODO
 * 
 */
public interface ProcessDirService {
    
    

    public boolean saveDir(Dir dir);
    
    
    public Dir findDirById(String dirId);
    // userId 是用于处理根目录的情况
    public Dir findDirById(String dirId, String userId);
    
    public boolean deleteDirById(String dirId);
    
    public List<Dir> listDirByParentId(String parentId);
    
    public void listDirPath(String dirId);


    /**
     * @Title: listDirPath
     * @Description: TODO
     * @param @param dirId
     * @param @param dirPathList
     * @return void
     * @throws
     */
//    List<Dir> listDirPath(String dirId, List<Dir> dirPathList);


    /**
     * @Title: getDirPathList
     * @Description: TODO
     * @param @param dirId
     * @param @return
     * @return List<Dir>
     * @throws
     */
    List<Dir> getDirPathList(String dirId);
    
    public boolean updateDirName(String dirId, String newDirId);
    
}

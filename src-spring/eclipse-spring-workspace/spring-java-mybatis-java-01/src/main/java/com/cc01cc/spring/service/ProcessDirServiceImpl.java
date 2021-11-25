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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc01cc.spring.mapper.BaseMapper;
import com.cc01cc.spring.pojo.Dir;
import com.cc01cc.spring.pojo.File;

/**
 * @author cc01cc
 * @date 2021-11-23
 * @Description: TODO
 * 
 */
@Service
public class ProcessDirServiceImpl implements ProcessDirService {

    /**
     * <p>
     * Title: saveDir
     * </p>
     * <p>
     * Description:
     * </p>
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
     * <p>
     * Title: findDirById
     * </p>
     * <p>
     * Description:
     * </p>
     * @param dirId
     * @return
     * @see com.cc01cc.spring.service.ProcessDirService#findDirById(java.lang.String)
     *
     */
    @Override
    public Dir findDirById(String dirId) {
        Dir dir = new Dir();

        dir = baseMapper.findDirByDirId(dirId);

        return dir;
    }

    @Override
    public Dir findDirById(String dirId, String userId) {
        Dir dir = new Dir();
        if (dirId.equals(userId)) {
            dir.setDirId(userId);
            dir.setDirName(userId);
            dir.setDirParentId(null);
        } else {
            dir = baseMapper.findDirByDirId(dirId);
        }
        return dir;
    }

    /**
     * <p>
     * Title: deleteDirById
     * </p>
     * <p>
     * Description:
     * </p>
     * @param dirId
     * @return
     * @see com.cc01cc.spring.service.ProcessDirService#deleteDirById(java.lang.String)
     *
     */
    @Autowired
    private ProcessFileService processFileService;
    @Override
    public boolean deleteDirById(String dirId) {
        
//        Dir dirTodo = baseMapper.findDirByDirId(dirId);
//        String dirTodoParentId = dirTodo.getDirParentId();

        List<File> fileListDelete = baseMapper.findFileByParentId(dirId);
        List<Dir> dirListDelete = baseMapper.findDirByParentId(dirId);
        
        
        for(File fileDelete : fileListDelete ) {
            processFileService.deleteFileById(fileDelete.getFileId());
        }
        
        for(Dir dirDelete : dirListDelete) {
//            Dir dirKeep = dirDelete;
            deleteDirById(dirDelete.getDirId());
//            System.out.println("dirDelete : " + dirDelete);
//            baseMapper.deleteDirByDirId(dir.getDirId());
        }
        System.out.println("dirDelete : " + dir);
        baseMapper.deleteDirByDirId(dirId);
        return true;
    }

    /**
     * <p>
     * Title: listFileByParentId
     * </p>
     * <p>
     * Description:
     * </p>
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

    // TODO 这个和 listDirPath 关联，但这个结构有些突兀，感觉可以优化或重构一下
    private Dir dir = new Dir();

    @Override
    public List<Dir> getDirPathList(String dirId) {
        System.out.println("getDirPathList Start...");
        List<Dir> dirPathList = new ArrayList<>();
        dirPathList = listDirPath(dirId, dirPathList);
        return dirPathList;
    }

//    @Override
    public List<Dir> listDirPath(String dirId, List<Dir> dirPathList) {
        dir = findDirById(dirId);
        if (dir != null) {
            // TODO 这边设置一个变量存储 dir，我也不清楚为什么这儿递归跳出之后 dir 会变为null
            Dir dirProcess =dir;
            System.out.println("dirListPathProcess : " + dir);
            dirPathList = listDirPath(dir.getDirParentId(), dirPathList);
            System.out.println("dirListPathProcess After: " + dir);
            dirPathList.add(dirProcess);
        } else {
             Dir dirRoot = new Dir();
             dirRoot.setDirId(dirId);
             dirRoot.setDirName(dirId);
             dirRoot.setDirParentId(null);
             System.out.println("dirListPathBotton : "+dirRoot);
             dirPathList.add(dirRoot);
        }

        System.out.println("dirPathList : " + dirPathList);
        return dirPathList;
    }

    /**
     * <p>
     * Title: listDirPath
     * </p>
     * <p>
     * Description:
     * </p>
     * @param dirId
     * @see com.cc01cc.spring.service.ProcessDirService#listDirPath(java.lang.String)
     *
     */
    @Override
    public void listDirPath(String dirId) {
        // TODO Auto-generated method stub

    }

    // @Override
    // public List<Dir> listDirPath(String dirId){
    //// List<Dir> dirPathList = new ArrayList<>();
    // dir = findDirById(dirId);
    //
    // if(dir!=null) {
    // System.out.println("dirListPathProcess : "+dir);
    // listDirPath(dir.getDirParentId());
    //// dirPathList.add(dir);
    // }else {
    //// Dir dirRoot = new Dir();
    //// dirRoot.setDirId(dirId);
    //// dirRoot.setDirName(dirId);
    //// dirRoot.setDirParentId(null);
    //// System.out.println("dirListPathBotton : "+dirRoot);
    //// dirPathList.add(dirRoot);
    // dir.setDirId(dirId);
    // dir.setDirName(dirId);
    // dir.setDirParentId(null);
    // }
    // System.out.println("dirPathList : "+dirPathList);
    // return dirPathList.add(dir);
    // }
    
    @Override
    public boolean updateDirName(String dirId, String dirName) {
        
        Dir dir = baseMapper.findDirByDirId(dirId);
        dir.setDirName(dirName);
        boolean result = baseMapper.updateDirName(dir);
        return result;
        
    }
}

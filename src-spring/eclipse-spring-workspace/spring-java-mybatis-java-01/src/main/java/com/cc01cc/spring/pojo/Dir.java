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
*   @Title: Dir.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-11-21 
*/

package com.cc01cc.spring.pojo;

/**
 * @author cc01cc
 * @date 2021-11-21
 * @Description: TODO
 * 
 */
public class Dir {

    private String dirId;
    private String dirParentId;
    private String dirName;
    private int dirUserId;

    /**
     * @return the dirId
     */
    public String getDirId() {
        return dirId;
    }

    /**
     * @param dirId the dirId to set
     */
    public void setDirId(String dirId) {
        this.dirId = dirId;
    }

    /**
     * @return the dirParentId
     */
    public String getDirParentId() {
        return dirParentId;
    }

    /**
     * @param dirParentId the dirParentId to set
     */
    public void setDirParentId(String dirParentId) {
        this.dirParentId = dirParentId;
    }

    /**
     * @return the dirName
     */
    public String getDirName() {
        return dirName;
    }

    /**
     * @param dirName the dirName to set
     */
    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    /** 
     * <p>Title: toString</p>
     * <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     *
     */
    @Override
    public String toString() {
        return "Dir [dirId=" + dirId + ", dirParentId=" + dirParentId + ", dirName=" + dirName
                + ", dirUserId=" + dirUserId + "]";
    }

}

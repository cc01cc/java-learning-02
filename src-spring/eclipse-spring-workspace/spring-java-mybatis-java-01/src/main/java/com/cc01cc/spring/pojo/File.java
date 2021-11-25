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
*   @Title: File.java
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
public class File {

    private int fileUserLink;

    private String fileMD5;
    private String fileLocalStore;
    private String fileName;
    private String fileSharePassword;
    private String fileId;
    private String fileParentId;
    private int fileSize;



    /**
     * @return the fileSize
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    private int fileUserId;
    
    /**
     * @return the fileUserId
     */
    public int getFileUserId() {
        return fileUserId;
    }

    /**
     * @param fileUserId the fileUserId to set
     */
    public void setFileUserId(int fileUserId) {
        this.fileUserId = fileUserId;
    }

    /**
     * @return the fileUserLink
     */
    public int getFileUserLink() {
        return fileUserLink;
    }

    /**
     * @param fileUserLink the fileUserLink to set
     */
    public void setFileUserLink(int fileUserLink) {
        this.fileUserLink = fileUserLink;
    }

    /**
     * @return the fileMD5
     */
    public String getFileMD5() {
        return fileMD5;
    }

    /**
     * @param fileMD5 the fileMD5 to set
     */
    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }

    /**
     * @return the fileLocalStore
     */
    public String getFileLocalStore() {
        return fileLocalStore;
    }

    /**
     * @param fileLocalStore the fileLocalStore to set
     */
    public void setFileLocalStore(String fileLocalStore) {
        this.fileLocalStore = fileLocalStore;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileSharePassword
     */
    public String getFileSharePassword() {
        return fileSharePassword;
    }

    /**
     * @param fileSharePassword the fileSharePassword to set
     */
    public void setFileSharePassword(String fileSharePassword) {
        this.fileSharePassword = fileSharePassword;
    }

    /**
     * @return the fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the fileParentId
     */
    public String getFileParentId() {
        return fileParentId;
    }

    /**
     * @param fileParentId the fileParentId to set
     */
    public void setFileParentId(String fileParentId) {
        this.fileParentId = fileParentId;
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
        return "File [fileUserLink=" + fileUserLink + ", fileMD5=" + fileMD5 + ", fileLocalStore="
                + fileLocalStore + ", fileName=" + fileName + ", fileSharePassword="
                + fileSharePassword + ", fileId=" + fileId + ", fileParentId=" + fileParentId
                + ", fileSize=" + fileSize + ", fileUserId=" + fileUserId + "]";
    }

}

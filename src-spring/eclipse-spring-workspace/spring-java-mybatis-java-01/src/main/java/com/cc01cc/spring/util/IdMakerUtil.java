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
 *   @Title: IdMakerUtil.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-11-22 
 */  

package com.cc01cc.spring.util;

import java.math.BigInteger;

/**
 * @author cc01cc
 * @date 2021-11-22 
 * @Description: TODO
 * 
 */
public class IdMakerUtil {

    public static String makeId(int token) {
        String ctm = BigInteger.valueOf(System.currentTimeMillis()).toString(16);
        return ctm + Integer.toHexString(token);
    }
    public static String makeId(String token) {
        String ctm = BigInteger.valueOf(System.currentTimeMillis()).toString(16);
        return ctm + token;
    }
}

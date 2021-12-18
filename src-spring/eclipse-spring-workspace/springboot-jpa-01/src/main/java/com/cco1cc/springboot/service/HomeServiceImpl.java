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
*   @Title: HomeServiceImpl.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-12-18 
*/

package com.cco1cc.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author       cc01cc
 * @date         2021-12-18
 * @Description: TODO
 * 
 */
@Service
public class HomeServiceImpl implements HomeService {

    private ArrayList<String> hobbyList;

    public HomeServiceImpl() {
        List<String> listTemp = Arrays.asList("篮球", "足球", "羽毛球", "游戏");
        this.hobbyList.addAll(listTemp);
    }

    public ArrayList<String> hobbyToList(byte byteHobby) {

        ArrayList<String> listHobby = new ArrayList<String>();

        for (byte i = (byte) hobbyList.size(); i < hobbyList.size(); i--) {
            if (((byteHobby >> i) & 1) == byteHobby) {
                listHobby.add(hobbyList.get(i));
            }
        }
        return listHobby;
    }

    public String hobbyToStr(byte byteHobby) {
        ArrayList<String> listHobby = hobbyToList(byteHobby);

        if (listHobby.isEmpty()) return null;

        if (listHobby.size() == 1) return listHobby.get(0);

        String strHobby = listHobby.get(0);
        for (int i = 1; i < listHobby.size(); i++) {
            strHobby = strHobby + ',' + listHobby.get(i);
        }
        return strHobby;
    }

    @Override
    public String getHobby() {
        return null;
    }
    // /**
    // * @return the hobbyList
    // */
    // public ArrayList<String> getHobbyList() {
    // return hobbyList;
    // }

    /**
     * @param hobbyList the hobbyList to set
     */
    // public void setHobbyList(ArrayList<String> hobbyList) {
    // this.hobbyList = hobbyList;
    // }

}

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
 *   @Title: BitAndInt.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-12-21 
 */  

package com.cc01cc.springboot.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cc01cc
 * @date 2021-12-21 
 * @Description: TODO
 * 
 */
public class BitAndInt {
    public static void main(String[] args) {
        ArrayList<String> hobbyList= new ArrayList<String>();
        System.out.println((byte)4);
        System.out.println((byte)4 >> 3);
        System.out.println("这里是 hobbyToList 方法");
        List<String> listTemp = Arrays.asList("篮球", "足球", "羽毛球", "游戏");
        hobbyList.addAll(listTemp);
        
//        this.hobbyList.add("游戏");
        System.out.println("hobbyList 添加成功");
        ArrayList<String> listHobby = new ArrayList<String>();
        int byteHobby =1;
        for (byte i = (byte) (hobbyList.size()-1); i < hobbyList.size(); i--) {
            System.out.println("i="+i);
            if (((byteHobby >> (int)i) & 1) == byteHobby) {
                listHobby.add(hobbyList.get((int) i));
            }
        }
    }

}

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
*   @Title: Hobby.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-12-23 
*/

package com.cco1cc.springboot.entity;

/**
 * @author       cc01cc
 * @date         2021-12-23
 * @Description: TODO
 * 
 */
public enum Hobby {
    basketball("篮球"), soccerball("足球"), badminton("羽毛球"), game("游戏");

    public static final Hobby[] ALL = {
            basketball, soccerball, badminton, game
    };

    private final String name;
    
    public static Hobby forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for Hobby");
        }
        if (name.toUpperCase().equals("篮球")) {
            return basketball;
        } else if (name.toUpperCase().equals("足球")) {
            return soccerball;
        } else if (name.toUpperCase().equals("羽毛球")) {
            return badminton;
        }else if (name.toUpperCase().equals("游戏")) {
            return game;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any Hobby");
    }
    
    private Hobby(final String name) {
        this.name = name;
    }
    
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return getName();
    }
    

}

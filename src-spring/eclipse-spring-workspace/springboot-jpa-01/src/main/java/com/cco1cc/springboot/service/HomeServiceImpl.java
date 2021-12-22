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
*   @date 2021-12-22 
*/

package com.cco1cc.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cco1cc.springboot.entity.UserInfo;
import com.cco1cc.springboot.repository.UserInfoRepository;

/**
 * @author       cc01cc
 * @date         2021-12-22
 * @Description: TODO
 * 
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> findAll(String sortColumn) {
        // https://stackoverflow.com/questions/59060633/the-constructor-sortsort-direction-string-is-undefined
        // userInfoRepository.findAll(new sort(Direction.DESC, sortColumn)); 已不推荐
        return userInfoRepository.findAll(Sort.by(Direction.DESC, sortColumn));

    }

    @Override
    public String findAllUserByPage(Integer page, Model model) {
        if (page == null) page = 1;
        int            size     = 2;
        Page<UserInfo> pageData = userInfoRepository
                .findAll(PageRequest.of(page - 1, size, Sort.by(Direction.ASC, "pkUserInfoId")));
        List<UserInfo> allUser  = pageData.getContent();
        model.addAttribute("allUser", allUser);
        model.addAttribute("totalCount", pageData.getTotalElements());
        model.addAttribute("totalPage", pageData.getTotalPages());
        model.addAttribute("page", page);
        System.out.println("HomeService Model"+model);
        return "home";
    }

}

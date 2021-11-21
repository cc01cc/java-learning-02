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
*   @Title: SkyDriveInitiallizer.java
*   @Description: SkyDriver 网页 (Spring MVC 实践) 初始化入口
*   @author cc01cc
*   @date 2021-11-20 
*/

package com.cc01cc.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.cc01cc.spring.config.WebConfig;

//
/**
 * @author cc01cc
 * @date 2021-11-21
 * @Description: SkyDriver 网页 (Spring MVC 实践) 初始化入口
 * 
 */
public class SkyDriveInitiallizer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {

        System.out.println("初始化");

        // 注册网页配置 WebConfig
        AnnotationConfigWebApplicationContext skyDriveContext = new AnnotationConfigWebApplicationContext();
        skyDriveContext.register(WebConfig.class);

        // 关联当前 Context
        skyDriveContext.setServletContext(container);

        // 注册 DispatcherServlet 并将 SkyDriveContext 与之绑定
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher",
                new DispatcherServlet(skyDriveContext));
        registration.addMapping("/");
        registration.setLoadOnStartup(1);

    }
}

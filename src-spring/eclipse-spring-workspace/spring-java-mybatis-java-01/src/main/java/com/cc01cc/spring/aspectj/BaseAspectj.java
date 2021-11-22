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
*   @Title: BaseAspectj.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-11-21 
*/

package com.cc01cc.spring.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author cc01cc
 * @date 2021-11-21 
 * @Description: TODO
 * 
 */
@Aspect
@Component
public class BaseAspectj {

    /*
     * @Pointcut : 定义切入点
     * @第一个 * : 表示适配任意返回类型 (留意与包名之间的空格)
     * @第二个 * : 表示包下适配任意类型
     * @第三个 * : 表示类下适配任意方法
     * @.. : 表示适配方法内任意参数 (两个点)
     */
    @Pointcut("execution(* com.cc01cc.spring.service.*.*(..))")
    private void servicePointCut() {}
    
    @Around("servicePointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("环绕开始");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("环绕结束");
        return object;
    }
    
}

/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 数据过滤，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class DataFilterAspect {

    @Pointcut("@annotation(com.example.demo.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) {
        Object params = point.getArgs()[0];
//        if (params != null && params instanceof Map) {
//            UserDetail user = SecurityUser.getUser();
//
//            //如果是超级管理员，则不进行数据过滤
//            if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
//                return;
//            }
//
//            try {
//                // 否则进行数据过滤
//                Map map = (Map) params;
//                String sqlFilter = getSqlFilter(user, point);
//                map.put(Constant.SQL_FILTER, new DataScope(sqlFilter));
                  // 设置需要过滤的列
//                查询当前节点的对应人或者人对应的角色不允许看的字段，放到map中，方便查询拦截器的时候根据列取差集过滤字段
//            } catch (Exception e) {
//
//            }

//        }
        return;

//        throw new RenException(ErrorCode.DATA_SCOPE_PARAMS_ERROR);
    }

    /**
     * 获取数据过滤的SQL
     */
//    private String getSqlFilter(UserDetail user, JoinPoint point) throws Exception {
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = point.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
//        DataFilter dataFilter = method.getAnnotation(DataFilter.class);
//
//        //获取表的别名
//        String tableAlias = dataFilter.tableAlias();
//        if (StrUtil.isNotBlank(tableAlias)) {
//            tableAlias += ".";
//        }
//
//        StringBuilder sqlFilter = new StringBuilder();
//        sqlFilter.append(" (");
//
//        //部门ID列表
//        List<Long> deptIdList = user.getDeptIdList();
//        if (CollUtil.isNotEmpty(deptIdList)) {
//            sqlFilter.append(tableAlias).append(dataFilter.deptId());
//
//            sqlFilter.append(" in(").append(StringUtils.join(deptIdList, ",")).append(")");
//        }
//
//        //查询本人数据
//        if (CollUtil.isNotEmpty(deptIdList)) {
//            sqlFilter.append(" or ");
//        }
//        sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getId());
//
//        sqlFilter.append(")");
//
//        return sqlFilter.toString();
//    }
}
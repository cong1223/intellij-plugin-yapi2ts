package com.hunliji.constants;

import com.hunliji.dto.ConfigDTO;
import com.hunliji.utils.ReflexObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认token配置常量类
 */

public class DefaultAuthTokenConstant {
    public static final ConfigDTO haicaoyun = new ConfigDTO(19, "6d7996730a94ff788b62595b3ece4830d93261663bdcfb53cfc2de961868d39c", "海草云");
    public static final ConfigDTO merchantCenter = new ConfigDTO(113, "6bb3a501408adcc700ecea9cb3b011463561f4c081431bca30355447cd96c3dc", "商家中心");
    public static final ConfigDTO orderCenter = new ConfigDTO(115, "7a8f04284714df7dc270186f5fa98a41f8f5237b3dfe202d62e00058f30b9bcf", "订单中心");
    public static final ConfigDTO haicaoCard = new ConfigDTO(248, "1b4ba91096b2ebffdf2de957761dc954f3b699f327a6c23e95272fff2f50f267", "海草名片");

    public static String[] getDefaultProjectCnameList() throws ClassNotFoundException {
        Field[] fields = ReflexObjectUtil.getAllFields(Thread.currentThread().getStackTrace()[1].getClassName());
        List<String> projectCnameList = new ArrayList();
        for (Field f : fields) {
            Class clazz = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
            try {
                Object o = clazz.getConstructor().newInstance();
                ConfigDTO configDTO = (ConfigDTO) ReflexObjectUtil.getKeyAndValue(o).get(f.getName());
                String cname = configDTO.getCname();
                projectCnameList.add(cname);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        String[] sList = projectCnameList.toArray(new String[projectCnameList.size()]);

        return sList;
    }

    public static String getDefaultTokenByProjectId(Integer projectId) throws ClassNotFoundException {
        Field[] fields = ReflexObjectUtil.getAllFields(Thread.currentThread().getStackTrace()[1].getClassName());
        Map<Integer, String> map = new HashMap();
        for (Field f : fields) {
            Class clazz = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
            try {
                Object o = clazz.getConstructor().newInstance();
                ConfigDTO configDTO = (ConfigDTO) ReflexObjectUtil.getKeyAndValue(o).get(f.getName());
                map.put(configDTO.getProjectId(), configDTO.getToken());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return map.get(projectId);
    }
}

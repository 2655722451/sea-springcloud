package com.example.demo.test;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.Scheduler;
import cn.hutool.cron.TaskTable;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Classname HuToolTest
 * @Description TODO
 * @Date 2021/3/11 15:42
 * @Created by Administrator
 */
public class HuToolTest {
    public static void main(String[] args) {
        new HuToolTest().ceshi();
    }

    public void ceshi(){
        Date date1 = DateUtil.date();
        String str = DateUtil.format(date1, "yyyy-MM-dd HH:mm:ss");
        System.out.println(str);

        Date date2 = DateUtil.date();
        long msLong = DateUtil.between(date1, date2, DateUnit.SECOND);
        System.out.println(msLong);
    }






    public List<Map<String, Object>> getList(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("packageUrl", "com.example.demo.test.HuToolTest.lala1");
        map1.put("icon", "*/1 * * * * ?");
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("packageUrl", "com.example.demo.test.HuToolTest.lala2");
        map2.put("icon", "*/1 * * * * ?");
        list.add(map2);

        return list;
    }

    public void cronStart() throws NoSuchFieldException, IllegalAccessException {
        //模拟数据库查询数据
        List<Map<String, Object>> list = this.getList();

        //拼接str
        StringBuffer stringBuffer = new StringBuffer();
        list.forEach(s -> {
            stringBuffer.append(s.get("packageUrl"))
                    .append(" = ")
                    .append(s.get("icon"))
                    .append("\n");
        });

        //生成文件
        FileWriter writer = new FileWriter("config/cron.setting");
        writer.write(StrUtil.str(stringBuffer));

        //执行定时
        CronUtil.setMatchSecond(true);
        CronUtil.start();

        //先获取到存放任务的列表 taskTable
        Scheduler scheduler = CronUtil.getScheduler();
        Field taskTableField = scheduler.getClass().getDeclaredField("taskTable");
        taskTableField.setAccessible(true);
        TaskTable taskTable = (TaskTable) taskTableField.get(scheduler);

        //从taskTable中拿到任务的id
        Field taskIds = taskTable.getClass().getDeclaredField("ids");
        taskIds.setAccessible(true);
        ArrayList<String> arrayList = (ArrayList<String>) taskIds.get(taskTable);
        for(int i = arrayList.size() - 1; i >= 0; i--){
            CronUtil.remove(arrayList.get(i));
        }
    }

    public void lala1(){
        System.out.println("-----------1------------------");
    }

    public void lala2(){
        System.out.println("-----------2------------------");
    }
}

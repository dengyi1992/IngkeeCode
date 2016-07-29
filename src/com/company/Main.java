package com.company;

import com.company.utils.GetInfoThread;
import com.company.utils.GetPhones;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入本次要获取的条数（ps:不大于10）,最好给些接收时间：");
        while (scanner.hasNext()) {
            int count = scanner.nextInt();
            if (count>10)
                count=10;
            int finalCount = count;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String[] nums = GetPhones.getNumbers(finalCount);
                    if (nums.length != finalCount) {
                        System.out.println("获取不到资源，请手动释放手机号资源");
                    } else {
                        for (String num :
                                nums) {
                            System.out.println(num);
                            GetInfoThread getInfoThread = new GetInfoThread(num);
                            getInfoThread.run();

                        }
                    }
                }
            }).start();

        }


    }

}

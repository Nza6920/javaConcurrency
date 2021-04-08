package com.niu.concurrency;

/**
 * 测试类
 *
 * @author [nza]
 * @version 1.0 [2020/08/25 17:31]
 * @createTime [2020/08/25 17:31]
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(4 >> 1);
    }

    public int subtractProductAndSum(int n) {

        int multiply = 1;
        int sum = 0;

        while(n!=0){
            multiply *= n%10;
            sum += n%10;
            n /= 10;
        }

        return multiply - sum;
    }
}

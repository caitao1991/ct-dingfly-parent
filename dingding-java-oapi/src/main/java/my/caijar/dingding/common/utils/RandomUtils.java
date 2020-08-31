package my.caijar.dingding.common.utils;

import java.util.Random;

/**
  * @ClassName RandomStr
  * @Description methods of generating random strings
  * @Author  Tao Cai
  * @Date   2019/6/24 14:47
  *  @Version 1.0
  */
public class RandomUtils {

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";

    /**
     * 返回一个定长的随机字符串(包含大小写字母、数字)
     *
     * @param length
     * 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }
    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length
     * 随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperLowerString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }
    /**
     * 返回一个定长的随机纯小写字母字符串(只包含小写字母)
     *
     * @param length
     * 随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateUpperLowerString(length).toLowerCase();
    }
    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大写字母)
     *
     * @param length
     * 随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateUpperLowerString(length).toUpperCase();
    }
    /**
     * 生成一个定长的纯0字符串
     *
     * @param length
     * 字符串长度
     * @return 纯0字符串
     */
    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }
    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     *
     * @param num
     * 数字
     * @param fixedlenth
     * 字符串长度
     * @return 定长的字符串
     */
    public static String generateFixdLengthString(long num, int fixedlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixedlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixedlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixedlenth
                    + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }
    /**
     * 每次生成的len位数都不相同(仅限于整型数组的每个元素都0-9这十个基本数字，且len的值还不能大于数组的长度)
     *
     * @param param
     * @return 定长的数字
     */
    public static int generateDifferentNum(int[] param, int len) {
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        return result;
    }

    public static void main(String args[]) {
        String str = generateString(43);
        System.out.println(str);
    }
}
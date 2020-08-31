package my.caijar.dingding.oapi.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 判断 数组/集合 为空的工具类
 * @author ct
 */
public class ArrayUtil {
	//判断集合是否为空
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
	
	//判断Map是否为空
	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}
	
	//判断数组是否为空
	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}
	
	//判断List是否为空
	public static boolean isEmpty(List<Object> list) {
		return list == null || list.size() == 0;
	}
}
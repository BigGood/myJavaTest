package jsonUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class jsonFormat {
	public static <T> T getByJson(String json,Class<T> c) throws Exception{
		T o = c.newInstance();
		if(json.substring(0, 1).equals("{")){
			xunhuan(json.substring(1),c,o);
		}
		
		
		
		
		
		
//		for (Iterator i = root.elementIterator(); i.hasNext();) {//第一层循环
//		Element el = (Element) i.next();
//			for (Method method : c.getMethods()) {
//				String str = "set"+el.getName().substring(0, 1).toUpperCase() + el.getName().substring(1); 
//				if(str.equals(method.getName())){
//					method.invoke(o, el.getData());
//				}
//			}
//		}
		
		return o;
	}
	public static <T> void xunhuan(String json,Class<T> c,Object o) throws Exception{
//		List<Element> list =null;
//		List<Element> list2 =null;
//		Object obj=null;
		while (1==1){
			int index=-1;
			if((index=json.indexOf(":"))!=-1){
				if(json.charAt(index+1)=='{'){
					String key=json.substring(0,index).replaceAll("\"", "");
					Method methodGet = c.getMethod("get"+key.substring(0, 1).toUpperCase() + key.substring(1));
					Method method = c.getMethod("set"+key.substring(0, 1).toUpperCase() + key.substring(1),methodGet.getReturnType());
//					xunhuan(json,)
					Class<?> clazz=methodGet.getReturnType();
					Object obj=clazz.newInstance();
					method.invoke(o, obj);
					xunhuan(json.substring(index+2),clazz,obj);
					if(json.indexOf("},")!=-1){
						json=json.substring(json.indexOf("},")+2);
						continue;
					}else{
						break;
					}
//					for (Element e : list) {
//						xunhuan(e,methodGet.getReturnType(),obj);//递归
//					}
				}else if(json.charAt(index+1)=='['){
					
				}else{
					String key=json.substring(0,index).replaceAll("\"", "");
					Method methodGet = c.getMethod("get"+key.substring(0, 1).toUpperCase() + key.substring(1));
					Method method = c.getMethod("set"+key.substring(0, 1).toUpperCase() + key.substring(1),methodGet.getReturnType());
					if(json.indexOf(",")!=-1){
						method.invoke(o, json.substring(index+1,json.indexOf(",")));
						json=json.substring(json.indexOf(",")+1);
						continue;
					}else{
						method.invoke(o, json.substring(index+1,json.indexOf("}")==-1?(json.indexOf("]")==-1?(json.length()-1):(json.indexOf("]"))):json.indexOf("}")));
						break;
					}
					
				}
			
			}
		}
		
		
//		if(((index=json.indexOf(":"))!=-1)&&(json.charAt(index)=='{'||json.charAt(index)=='[')){
////		if((list =element.elements())!=null&&list.size()!=0){
//			Method methodGet = c.getMethod("get"+element.getName().substring(0, 1).toUpperCase() + element.getName().substring(1));
//			
//			Method method = c.getMethod("set"+element.getName().substring(0, 1).toUpperCase() + element.getName().substring(1), methodGet.getReturnType());
//			if(methodGet.getReturnType().isAssignableFrom(ArrayList.class)){
//			
//				obj=new ArrayList<Op>();
//				
//				System.out.println(methodGet.getReturnType().getGenericSuperclass());
//				System.out.println(obj.getClass().getGenericSuperclass());
//				System.out.println(new ArrayList<Op>(){}.getClass().getGenericSuperclass());
//				Type t= new ArrayList<Op>(){}.getClass().getGenericSuperclass();//问题来了：怎样通过反射得到泛型类
//				Type targ = ((ParameterizedType) t).getActualTypeArguments()[0];
//
//				
//   					for (Element e : list) {
//   					Object obj1=Class.forName(targ.getTypeName()).newInstance();
//   					((ArrayList)obj).add(obj1);
//   						if((list2 =e.elements())!=null){//因为是数组，跳过一个节点
//	   						for (Element e2 : list2) {
//	   							xunhuan(e2,obj1.getClass(),obj1);//递归
//	   						}
//   						}
//   					}
//   				method.invoke(o, obj);          
//			             
//			}else{
//				obj = methodGet.getReturnType().newInstance();
//				method.invoke(o, obj);
//				for (Element e : list) {
//					xunhuan(e,methodGet.getReturnType(),obj);//递归
//				}
//			}
//				
//				
//				
//				
//			
////			for (Method method : c.getMethods()) {
////				String str = "set"+element.getName().substring(0, 1).toUpperCase() + element.getName().substring(1); 
////				if(str.equals(method.getName())){
////					method.invoke(o, element.getData());
////				}
////			}
//		}else{
//				Method methodGet = c.getMethod("get"+element.getName().substring(0, 1).toUpperCase() + element.getName().substring(1));
//				Method method = c.getMethod("set"+element.getName().substring(0, 1).toUpperCase() + element.getName().substring(1),methodGet.getReturnType());
//				method.invoke(o, element.getData());
////				method.invoke(o, element.getData());
//			
////				for (Method method : c.getMethods()) {
////					String str = "set"+element.getName().substring(0, 1).toUpperCase() + element.getName().substring(1); 
////					if(str.equals(method.getName())){
////						method.invoke(o, element.getData());
////					}
////				}
//		}
	}
	public static void main(String[] args) throws Exception{
		A a = getByJson("{\"a\":1,\"b\":{\"b\":2,\"c\":{\"c\":3},\"d\":4}}",A.class);
		System.out.println(a.toString());

	}
}

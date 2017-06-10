package tao.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {
	public static void main(String[] args) throws Exception {
		//classpath: prefix does not work in core Java, it's Spring-specific
		//String filePath="classpath:testdata.json";
		URL path=ClassLoader.getSystemResource("testdata2.json");
		
		FileReader reader = new FileReader(path.getPath());
		JSONParser jsonParser = new JSONParser();
		JSONFilter filter=new JSONFilter();
		CheckPermission chk= new CheckPermissionImpl();
		JSONObject objstart=(JSONObject) jsonParser.parse(reader);
		filter.Start(objstart ,chk);

		//System.out.println(filter.GetJSONDestination().toJSONString());
		System.out.println(objstart.toJSONString());
		//;
	}

	public void func1() {
		JSONObject obj = new JSONObject();
		obj.put("name", "mkyong.com");
		obj.put("age", new Integer(100));
		
		JSONArray list = new JSONArray();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");

		obj.put("messages", list);
	}
}

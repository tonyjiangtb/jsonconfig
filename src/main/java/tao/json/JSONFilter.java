package tao.json;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONFilter {
	private Deque<JSONObject2> queue;
	private JSONObject objDest;

	JSONFilter() {
		queue = new LinkedList<JSONObject2>();
		objDest = new JSONObject();
	}

	public void Start(JSONObject objSource, CheckPermission permission) {
		JSONObject2 index;
		JSONObject2 start = new JSONObject2("root", objSource);
		queue.addLast(start);
		while ((index = queue.pop()) != null) {
			Iterator<String> iterator = (Iterator<String>) index.getData().keySet().iterator();

			while (iterator.hasNext()) {
				String key = iterator.next();
				
				String currentPath = index.getPath() + "\\" + key;
				String objType=index.getData().get(key).getClass().getSimpleName().toString();
				System.out.println(currentPath);

				switch (objType) {
				case "String":
				case "Long":
					if (permission.Check(currentPath)) {
						objDest.put(currentPath, index.getData().get(key));
					}else{
						iterator.remove();
					}
					break;
				case "JSONArray":
					if (permission.Check(currentPath)) {
						objDest.put(key, index.getData().get(key));
					} else {
						JSONArray array = (JSONArray) index.getData().get(key);
						Iterator<JSONObject> arrayit = array.iterator();
						while (arrayit.hasNext()) {
							JSONObject2 arrayobj = new JSONObject2(currentPath, arrayit.next());
							queue.addLast(arrayobj);
						}
					}
					break;
				case "JSONObject":
					if (permission.Check(currentPath)) {
						objDest.put(key, index.getData().get(key));
					} else {
						queue.addLast(
								new JSONObject2(currentPath,
								(JSONObject) index.getData().get(key)));
					}
					break;
				}
			}
			if (queue.isEmpty()) {
				break;
			}
		}
	}

	public JSONObject GetJSONDestination() {
		return this.objDest;
	}
}

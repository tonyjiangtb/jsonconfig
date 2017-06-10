package tao.json;

import org.json.simple.JSONObject;

public class JSONObject2{
	private String path;
	private JSONObject data;
	public JSONObject2(String initPath, JSONObject objData){
		this.path=initPath;
		this.data=objData;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
	
}

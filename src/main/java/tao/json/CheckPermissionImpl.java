package tao.json;

public class CheckPermissionImpl implements CheckPermission {

	@Override
	public boolean Check(String Path) {
		boolean permission;
		switch (Path) {
		case "root\\languages\\lang":
			// case "root\\languages\\knowledge":
			// case "root\\job\\site":
		case "root\\job\\name":
			// case "root\\firstname":
		//case "root\\layer1":
			case "root\\layer1\\1_1":
			// case "root\\layer1\\layer2":
			// case "root\\layer1\\layer2\\2_1":
			case "root\\layer1\\layer2\\2_2":
		case "root\\layer1\\layer2\\layer3":
			permission = true;
			break;
		default:
			permission = false;
		}
		return permission;
	}

}

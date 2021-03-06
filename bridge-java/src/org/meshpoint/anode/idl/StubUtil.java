package org.meshpoint.anode.idl;

public class StubUtil {
	
	public static final int MODE_USER = 0;
	public static final int MODE_PLATFORM = 1;
	public static final int MODE_DICT  = 2;
	
	public static final String[] modes = new String[]{"user", "platform", "dict"};

	private static final String STUB_PACKAGE = "org.meshpoint.anode.stub.gen.";
	
	public static String getStubPackage(int mode) {
		return STUB_PACKAGE + modes[mode];
	}

	public static String uclName(String attrName) {
		return Character.toUpperCase(attrName.charAt(0)) + attrName.substring(1);
	}

}

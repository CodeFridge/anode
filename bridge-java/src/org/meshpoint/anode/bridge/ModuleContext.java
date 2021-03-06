package org.meshpoint.anode.bridge;

import org.meshpoint.anode.js.JSObject;
import org.meshpoint.anode.module.IModule;
import org.meshpoint.anode.module.IModuleContext;
import org.meshpoint.anode.type.IIndexedCollection;

public class ModuleContext implements IModuleContext {
	@SuppressWarnings("unused")
	private Env env;
	private JSObject exports;
	private IModule module;
	private long threadId;
	
	protected ModuleContext(Env env, JSObject exports) {
		this.env = env;
		this.exports = exports;
		threadId = Thread.currentThread().getId();
	}
	
	void setModule(IModule module) {
		this.module = module;
	}

	@Override
	public IIndexedCollection<Object> getModuleExports() {
		return exports;
	}

	@Override
	public long getEventThreadId() {
		return threadId;
	}

	public IModule getModule() {
		return module;
	}

}

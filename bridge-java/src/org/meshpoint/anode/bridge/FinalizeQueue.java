package org.meshpoint.anode.bridge;


public class FinalizeQueue implements SynchronousOperation {
	
	/********************
	 * private state
	 ********************/
	private Env env;
	private static final int QUEUE_LENGTH = 256;
	private long[] handleBuffer = new long[QUEUE_LENGTH];
	private int[] typeBuffer = new int[QUEUE_LENGTH];
	private int count;
	
	/********************
	 * public API
	 *******************/
	
	public FinalizeQueue(Env env) {
		this.env = env;
	}
	
	/**
	 * Appends a new item to the queue; may be called
	 * from any thread
	 * @param h the handle
	 */
	public synchronized void put(long h, int type) {
		if(count == QUEUE_LENGTH)
			env.waitForOperation(this);
		if(count == QUEUE_LENGTH)
			throw new RuntimeException("Fatal error processing FinalizeQueue");
		handleBuffer[count] = h;
		typeBuffer[count++] = type;
	}

	/**
	 * Removes and releases all items currently queued
	 */
	@Override
	public synchronized void run() {
		for(int i = 0; i < count; i++) {
			BridgeNative.releaseObjectHandle(env.envHandle, handleBuffer[i], typeBuffer[i]);
		}
		count = 0;
	}

	@Override
	public synchronized boolean isPending() {
		return count > 0;
	}

	@Override
	public void cancel() {}
}

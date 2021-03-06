#include "AndroidVM.h"

using namespace bridge;

AndroidVM::AndroidVM(JNIEnv *jniEnv, jobject jAndroidContext) : VM() {
	this->jniEnv = jniEnv;
	this->jAndroidContext = jniEnv->NewGlobalRef(jAndroidContext);
	jContextClass = (jclass)jniEnv->NewGlobalRef(jniEnv->FindClass("org/meshpoint/anode/AndroidContext"));
	createContextMethodId = jniEnv->GetMethodID(jContextClass, "<init>", "(Lorg/meshpoint/anode/bridge/Env;Lorg/meshpoint/anode/js/JSObject;Landroid/content/Context;)V");
}

AndroidVM::~AndroidVM() {
    jniEnv->DeleteGlobalRef(jContextClass);
    jniEnv->DeleteGlobalRef(jAndroidContext);
}

int AndroidVM::createContext(jobject jEnv, jobject jExports, jobject *jCtx) {
	int result = OK;
	*jCtx = jniEnv->NewObject(jContextClass, createContextMethodId, jEnv, jExports, jAndroidContext);
	if(jniEnv->ExceptionCheck()) {
		result = ErrorVM;
		jniEnv->ExceptionClear();
	}
	return result;
}

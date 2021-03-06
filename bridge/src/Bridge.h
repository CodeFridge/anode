#ifndef BRIDGE_BRIDGE_H
#define BRIDGE_BRIDGE_H

#include <node.h>

v8::Handle<v8::Value> Load(const v8::Arguments& args);
v8::Handle<v8::Value> Unload(const v8::Arguments& args);

extern "C" NODE_EXTERN void init(v8::Handle<v8::Object> target);

#endif

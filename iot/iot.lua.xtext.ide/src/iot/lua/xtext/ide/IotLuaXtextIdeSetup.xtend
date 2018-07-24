/*
 * generated by Xtext 2.12.0
 */
package iot.lua.xtext.ide

import com.google.inject.Guice
import iot.lua.xtext.IotLuaXtextRuntimeModule
import iot.lua.xtext.IotLuaXtextStandaloneSetup
import org.eclipse.xtext.util.Modules2

/**
 * Initialization support for running Xtext languages as language servers.
 */
class IotLuaXtextIdeSetup extends IotLuaXtextStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new IotLuaXtextRuntimeModule, new IotLuaXtextIdeModule))
	}
	
}

/*
 * generated by Xtext 2.12.0
 */
package iot.simpleexpressions.xtext


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class IoTStandaloneSetup extends IoTStandaloneSetupGenerated {

	def static void doSetup() {
		new IoTStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}

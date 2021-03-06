/**
 * generated by Xtext 2.12.0
 */
package iot.simpleexpressions.xtext.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import iot.simpleexpressions.xtext.IoTRuntimeModule;
import iot.simpleexpressions.xtext.IoTStandaloneSetup;
import iot.simpleexpressions.xtext.ide.IoTIdeModule;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
@SuppressWarnings("all")
public class IoTIdeSetup extends IoTStandaloneSetup {
  @Override
  public Injector createInjector() {
    IoTRuntimeModule _ioTRuntimeModule = new IoTRuntimeModule();
    IoTIdeModule _ioTIdeModule = new IoTIdeModule();
    return Guice.createInjector(Modules2.mixin(_ioTRuntimeModule, _ioTIdeModule));
  }
}

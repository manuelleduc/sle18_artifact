/**
 * generated by Xtext 2.10.0
 */
package ale.xtext;

import ale.xtext.AleStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class AleStandaloneSetup extends AleStandaloneSetupGenerated {
  public static void doSetup() {
    new AleStandaloneSetup().createInjectorAndDoEMFRegistration();
  }
}

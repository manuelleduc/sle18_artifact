/*
 * generated by Xtext 2.12.0
 */
package iot.simpleexpressions.xtext.tests

import com.google.inject.Inject
import com.google.inject.Provider
import iot.System
import iot_simpleexpression_exec.revisitor.impl.Iot_simpleexpression_execRevisitor
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(IoTInjectorProvider)
class IoTExecutionTest {
	@Inject
	Provider<ResourceSet> rsp

	@Test
	def void loadModel() {

		val rs = rsp.get
		val r = rs.getResource(URI.createURI("usecase.iot_se"), true)
		r.load(null)

		val s = r.contents.head as System

		val rev = new Iot_simpleexpression_execRevisitor() {
		}
		val o = rev.$(s.sketch.activity)
		o.main()
	}
}

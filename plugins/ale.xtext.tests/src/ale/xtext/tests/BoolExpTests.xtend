package ale.xtext.tests

import boolexp.BoolexpFactory
import com.google.inject.Inject
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
import org.eclipse.emf.ecore.EPackage
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(AleInjectorProvider)
class BoolExpTests {
	@Inject extension AleTestHelper
	val fact = BoolexpFactory::eINSTANCE

	@Before
	def void setUp() {
		EPackage.Registry.INSTANCE.put(GenModelPackage::eNS_URI, GenModelPackage::eINSTANCE)
	}

	@Test
	def void testPrintRevisitor() {
		'''
			behavior test
			import ecore "../testdata/boolexp/model/BoolExp.ecore"
			open abstract class Exp {
				abstract def String print()
			}
			
			open abstract class BinaryExp {}
			open abstract class Lit {}
			
			open class Tru {
				override String print() {
					return "T"
				}
			}
			
			open class Fals {
				override String print() {
					return "F"
				}
			}
			
			open class And {
				override String print() {
					return alg.$(obj.lhs).print() + " && " + alg.$(obj.rhs).print() 
				}
			}
			
			open class Or {
				override String print() {
					return alg.$(obj.lhs).print() + " || " + alg.$(obj.rhs).print()
				}
			}

		'''
			.with(
				fact.createOr => [
					lhs = fact.createAnd => [
						lhs = fact.createTru
						rhs = fact.createFals
					]
					rhs = fact.createTru
				]
			)
			.call("print")
			.assertEvaluatesTo("T && F || T")
	}

	@Test
	def void testEvalRevisitor() {
		'''
			behavior test
			import ecore "../testdata/boolexp/model/BoolExp.ecore"
			open abstract class Exp {
				abstract def boolean eval()
			}
			
			open abstract class BinaryExp {}
			open abstract class Lit {}
			
			open class Tru {
				override boolean eval() {
					return true
				}
			}
			
			open class Fals {
				override boolean eval() {
					return false
				}
			}
			
			open class And {
				override boolean eval() {
					return alg.$(obj.lhs).eval() && alg.$(obj.rhs).eval()
				}
			}
			
			open class Or {
				override boolean eval() {
					return alg.$(obj.lhs).eval() || alg.$(obj.rhs).eval()
				}
			}
		'''
			.with(
				fact.createOr => [
					lhs = fact.createAnd => [
						lhs = fact.createTru
						rhs = fact.createFals
					]
					rhs = fact.createTru
				]
			)
			.call("eval")
			.assertEvaluatesTo(true)
	}
}

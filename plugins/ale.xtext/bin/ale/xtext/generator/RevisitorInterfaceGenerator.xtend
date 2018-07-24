package ale.xtext.generator

import ale.xtext.utils.EcoreUtils
import ale.xtext.utils.NamingUtils
import java.util.List
import java.util.Map
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage

class RevisitorInterfaceGenerator {
	extension NamingUtils = new NamingUtils()
	extension EcoreUtils = new EcoreUtils()

	def allClassesCompl(EPackage pkg) {
		(pkg.allClasses + pkg.getComplementaryFromEPackage [ Map.Entry<String, String> z |
			z.key.loadEPackage.allClasses
		]).toMap(['''«it.EPackage.name».«it.name»'''], [it]).values.sortByName

	}

	def String generateInterface(EPackage pkg, GenModel gm) {

		resetResourceSet

		val Iterable<EClassifier> complementaryClassifiers = pkg.getComplementaryFromEPackage [ Map.Entry<String, String> z |
			z.key.loadEPackage.EClassifiers
		]
		val Iterable<EClassifier> classifiers = pkg.EClassifiers + complementaryClassifiers
		val eclasses = classifiers.filter(EClass)
		val localClasses = eclasses.sortBy[name].buildExtendedFactoryNames
		val allClasses = pkg.allClassesCompl.buildExtendedFactoryNames
		return '''
			package «pkg.revisitorPackageFqn»;
			
			public interface «pkg.revisitorInterfaceName»«allClasses.getTypeParams(true)»«
		»«FOR ref : pkg.directReferencedPkgs BEFORE '\n\textends ' SEPARATOR ',\n\t\t'»«
			»«ref.revisitorInterfaceFqn»«ref.allClassesCompl.buildExtendedFactoryNames.getTypeParams(false)»«
		»«ENDFOR» {
				«FOR cls : localClasses.filter[!it.key.abstract]»
					«cls.getTypeParam(false)» «cls.denotationName»(final «cls.key.getGenClass(gm)?.qualifiedInterfaceName» «cls.key.varName»);
				«ENDFOR»
			
				«FOR cls : allClasses.filter[it.value === null]»
					«val genCls = cls.key.getGenClass(gm)»
					«IF cls.key.hasRequiredAnnotation && cls.key.getSubClasses(allClasses).empty»
						«cls.getTypeParam(false)» $(final «genCls.qualifiedInterfaceName» it);
					«ELSE»
						default «cls.getTypeParam(false)» $(final «genCls?.qualifiedInterfaceName» it) {
							«FOR subClass : cls.key.getSubClasses(allClasses).filter[!it.key.abstract]»
								«val subGenCls = subClass.key.getGenClass(gm)»
								if (it.getClass() == «subGenCls?.qualifiedClassName».class)
									return «subClass.denotationName»((«subGenCls?.qualifiedInterfaceName») it);
							«ENDFOR»
							«IF cls.key.abstract»
								return null;
							«ELSE»
								return «cls.key.denotationName»(it);
							«ENDIF»
						}
					«ENDIF»
				«ENDFOR»
			}
		'''
	}

	

	def String getTypeParams(List<Pair<EClass, EClass>> classes, boolean withExtends) {
		'''«FOR cls : classes BEFORE '<' SEPARATOR ', ' AFTER '>'»«
		»«cls.getTypeParam(withExtends)»«ENDFOR»'''

	}

	def String getTypeParam(Pair<EClass, EClass> pairCls, boolean withExtends) {
		val cls = pairCls.key
		val parent = pairCls.value
		if (pairCls.value === null) {
			'''«cls.EPackage.name.replaceAll("\\.","").toFirstUpper»«
			»__«cls.name»T«
			»«IF cls.ESuperTypes.size == 1 && withExtends» extends «(cls.ESuperTypes.head -> null).getTypeParam(false)»«
			»«ENDIF»'''
		} else {
			'''«cls.EPackage.name.replaceAll("\\.","").toFirstUpper»__«cls.name»T_AS_«parent.EPackage.name.replaceAll("\\.","").toFirstUpper»«
												»__«parent.name»T«IF parent !== null && withExtends» extends «(parent -> null).getTypeParam(false)»«
															»«ENDIF»'''
		}

	}
}

package boolexp.main;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import boolexp.BoolexpPackage;
import boolexp.Exp;
import evalexp.revisitor.impl.EvalexpRevisitor;
import printexp.revisitor.impl.PrintexpRevisitor;

public class Main {
	public static void main(String[] args) {
		final Exp e = loadModel();
		printexp.revisitor.operations.printexp.ExpOperation printSem = new PrintexpRevisitor(){}.$(e);
		evalexp.revisitor.operations.evalexp.ExpOperation evalSem = new EvalexpRevisitor() {}.$(e);

		System.out.println(printSem.print() + " = " + evalSem.eval());
	}

	public static Exp loadModel() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("boolexp", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(BoolexpPackage.eNS_URI, BoolexpPackage.eINSTANCE);

		ResourceSet rs = new ResourceSetImpl();
		Resource res = rs.getResource(URI.createURI("model/Simple.boolexp"), true);
		return (Exp) res.getContents().get(0);
	}
}

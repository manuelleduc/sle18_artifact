/**
 * generated by Xtext 2.12.0
 */
package ale.xtext.formatting2;

import ale.xtext.ale.AleClass;
import ale.xtext.ale.AleImport;
import ale.xtext.ale.AleMethod;
import ale.xtext.ale.AleRoot;
import ale.xtext.ale.ConcreteMethod;
import ale.xtext.ale.EcoreImport;
import ale.xtext.services.AleGrammarAccess;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XBasicForLoopExpression;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XCollectionLiteral;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XDoWhileExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XForLoopExpression;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XPostfixOperation;
import org.eclipse.xtext.xbase.XReturnExpression;
import org.eclipse.xtext.xbase.XSwitchExpression;
import org.eclipse.xtext.xbase.XSynchronizedExpression;
import org.eclipse.xtext.xbase.XThrowExpression;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XWhileExpression;
import org.eclipse.xtext.xbase.formatting2.XbaseFormatter;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xtype.XFunctionTypeRef;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XImportSection;

@SuppressWarnings("all")
public class AleFormatter extends XbaseFormatter {
  @Inject
  @Extension
  private AleGrammarAccess _aleGrammarAccess;
  
  protected void _format(final AleRoot aleRoot, @Extension final IFormattableDocument document) {
    document.<XImportSection>format(aleRoot.getJavaImports());
    document.<EcoreImport>format(aleRoot.getEcoreImport());
    EList<AleImport> _aleImports = aleRoot.getAleImports();
    for (final AleImport aleImport : _aleImports) {
      document.<AleImport>format(aleImport);
    }
    EList<AleClass> _classes = aleRoot.getClasses();
    for (final AleClass aleClass : _classes) {
      document.<AleClass>format(aleClass);
    }
  }
  
  protected void _format(final AleClass aleClass, @Extension final IFormattableDocument document) {
    final ISemanticRegion openBracket = this.textRegionExtensions.regionFor(aleClass).keyword(this._aleGrammarAccess.getAleClassAccess().getLeftCurlyBracketKeyword_4());
    final ISemanticRegion closeBracket = this.textRegionExtensions.regionFor(aleClass).keyword(this._aleGrammarAccess.getAleClassAccess().getRightCurlyBracketKeyword_6());
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    document.<ISemanticRegion, ISemanticRegion>interior(openBracket, closeBracket, _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.setNewLines(1, 1, 2);
    };
    document.append(openBracket, _function_1);
    EList<AleMethod> _methods = aleClass.getMethods();
    for (final AleMethod aleMethod : _methods) {
      document.<AleMethod>format(aleMethod);
    }
  }
  
  protected void _format(final ConcreteMethod aleMethod, @Extension final IFormattableDocument document) {
    document.<XExpression>format(aleMethod.getBlock());
  }
  
  public void format(final Object aleMethod, final IFormattableDocument document) {
    if (aleMethod instanceof JvmTypeParameter) {
      _format((JvmTypeParameter)aleMethod, document);
      return;
    } else if (aleMethod instanceof JvmFormalParameter) {
      _format((JvmFormalParameter)aleMethod, document);
      return;
    } else if (aleMethod instanceof XtextResource) {
      _format((XtextResource)aleMethod, document);
      return;
    } else if (aleMethod instanceof XAssignment) {
      _format((XAssignment)aleMethod, document);
      return;
    } else if (aleMethod instanceof XBinaryOperation) {
      _format((XBinaryOperation)aleMethod, document);
      return;
    } else if (aleMethod instanceof XDoWhileExpression) {
      _format((XDoWhileExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XFeatureCall) {
      _format((XFeatureCall)aleMethod, document);
      return;
    } else if (aleMethod instanceof XMemberFeatureCall) {
      _format((XMemberFeatureCall)aleMethod, document);
      return;
    } else if (aleMethod instanceof XPostfixOperation) {
      _format((XPostfixOperation)aleMethod, document);
      return;
    } else if (aleMethod instanceof XWhileExpression) {
      _format((XWhileExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XFunctionTypeRef) {
      _format((XFunctionTypeRef)aleMethod, document);
      return;
    } else if (aleMethod instanceof ConcreteMethod) {
      _format((ConcreteMethod)aleMethod, document);
      return;
    } else if (aleMethod instanceof JvmGenericArrayTypeReference) {
      _format((JvmGenericArrayTypeReference)aleMethod, document);
      return;
    } else if (aleMethod instanceof JvmParameterizedTypeReference) {
      _format((JvmParameterizedTypeReference)aleMethod, document);
      return;
    } else if (aleMethod instanceof JvmWildcardTypeReference) {
      _format((JvmWildcardTypeReference)aleMethod, document);
      return;
    } else if (aleMethod instanceof XBasicForLoopExpression) {
      _format((XBasicForLoopExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XBlockExpression) {
      _format((XBlockExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XCastedExpression) {
      _format((XCastedExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XClosure) {
      _format((XClosure)aleMethod, document);
      return;
    } else if (aleMethod instanceof XCollectionLiteral) {
      _format((XCollectionLiteral)aleMethod, document);
      return;
    } else if (aleMethod instanceof XConstructorCall) {
      _format((XConstructorCall)aleMethod, document);
      return;
    } else if (aleMethod instanceof XForLoopExpression) {
      _format((XForLoopExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XIfExpression) {
      _format((XIfExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XInstanceOfExpression) {
      _format((XInstanceOfExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XReturnExpression) {
      _format((XReturnExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XSwitchExpression) {
      _format((XSwitchExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XSynchronizedExpression) {
      _format((XSynchronizedExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XThrowExpression) {
      _format((XThrowExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XTryCatchFinallyExpression) {
      _format((XTryCatchFinallyExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XTypeLiteral) {
      _format((XTypeLiteral)aleMethod, document);
      return;
    } else if (aleMethod instanceof XVariableDeclaration) {
      _format((XVariableDeclaration)aleMethod, document);
      return;
    } else if (aleMethod instanceof AleClass) {
      _format((AleClass)aleMethod, document);
      return;
    } else if (aleMethod instanceof AleRoot) {
      _format((AleRoot)aleMethod, document);
      return;
    } else if (aleMethod instanceof JvmTypeConstraint) {
      _format((JvmTypeConstraint)aleMethod, document);
      return;
    } else if (aleMethod instanceof XExpression) {
      _format((XExpression)aleMethod, document);
      return;
    } else if (aleMethod instanceof XImportDeclaration) {
      _format((XImportDeclaration)aleMethod, document);
      return;
    } else if (aleMethod instanceof XImportSection) {
      _format((XImportSection)aleMethod, document);
      return;
    } else if (aleMethod instanceof EObject) {
      _format((EObject)aleMethod, document);
      return;
    } else if (aleMethod == null) {
      _format((Void)null, document);
      return;
    } else if (aleMethod != null) {
      _format(aleMethod, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(aleMethod, document).toString());
    }
  }
}

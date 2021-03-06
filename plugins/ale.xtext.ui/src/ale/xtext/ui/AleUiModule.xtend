/*
 * generated by Xtext 2.10.0
 */
package ale.xtext.ui

import ale.xtext.ui.hyperlink.AleHyperlinkHelper
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
class AleUiModule extends AbstractAleUiModule {
	override Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
	    return typeof(AleHyperlinkHelper)
	}
}

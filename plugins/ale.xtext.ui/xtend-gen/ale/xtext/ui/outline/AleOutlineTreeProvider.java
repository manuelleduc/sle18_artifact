package ale.xtext.ui.outline;

import ale.xtext.ale.AleMethod;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.xtype.XImportSection;

@SuppressWarnings("all")
public class AleOutlineTreeProvider extends DefaultOutlineTreeProvider {
  protected void _createNode(final IOutlineNode parent, final XImportSection it) {
  }
  
  protected boolean _isLeaf(final AleMethod m) {
    return true;
  }
}

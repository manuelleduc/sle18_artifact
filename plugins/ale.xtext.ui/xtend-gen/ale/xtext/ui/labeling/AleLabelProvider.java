package ale.xtext.ui.labeling;

import ale.xtext.ale.AleMethod;
import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class AleLabelProvider extends DefaultEObjectLabelProvider {
  @Inject
  public AleLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final AleMethod it) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = it.getName();
    _builder.append(_name);
    _builder.append("(");
    final Function1<JvmFormalParameter, String> _function = (JvmFormalParameter it_1) -> {
      return it_1.getName();
    };
    String _join = IterableExtensions.join(ListExtensions.<JvmFormalParameter, String>map(it.getParams(), _function), ", ");
    _builder.append(_join);
    _builder.append(")");
    return _builder.toString();
  }
}

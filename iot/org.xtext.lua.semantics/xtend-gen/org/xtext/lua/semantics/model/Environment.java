package org.xtext.lua.semantics.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.xtext.lua.lua.Function;

@SuppressWarnings("all")
public class Environment {
  private Environment parent;
  
  private final Stack<Object> values = new Stack<Object>();
  
  private final HashMap<String, Object> variables = new HashMap<String, Object>();
  
  private final HashMap<String, Function> functions = new HashMap<String, Function>();
  
  public Environment getParent() {
    return this.parent;
  }
  
  public Environment setParent(final Environment e) {
    return this.parent = e;
  }
  
  public Object getVariable(final String s) {
    return this.variables.get(s);
  }
  
  public Object pushValue(final Object o) {
    return this.values.push(o);
  }
  
  public Object popValue() {
    Object _xblockexpression = null;
    {
      final Object ret = this.values.pop();
      _xblockexpression = ret;
    }
    return _xblockexpression;
  }
  
  public Function putFunction(final String s, final Function f) {
    return this.functions.put(s, f);
  }
  
  public Function getFunction(final String s) {
    return this.functions.get(s);
  }
  
  public Object putVariable(final String s, final Object o) {
    return this.variables.put(s, o);
  }
  
  public void putAllVariables(final Map<String, Object> v) {
    this.variables.putAll(v);
  }
  
  public void putAllFunctions(final Map<String, Function> f) {
    this.functions.putAll(f);
  }
  
  public boolean pushAllValues(final Stack<Object> v) {
    return this.values.addAll(v);
  }
  
  public Stack<Object> getValues() {
    return this.values;
  }
  
  public HashMap<String, Object> getVariables() {
    return this.variables;
  }
  
  public HashMap<String, Function> getFunctions() {
    return this.functions;
  }
  
  @Override
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("variables=");
    _builder.newLine();
    {
      Set<Map.Entry<String, Object>> _entrySet = this.variables.entrySet();
      for(final Map.Entry<String, Object> v : _entrySet) {
        String _key = v.getKey();
        _builder.append(_key);
        _builder.append(" -> ");
        Object _value = v.getValue();
        _builder.append(_value);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("values=");
    _builder.newLine();
    {
      for(final Object v_1 : this.values) {
        _builder.append(v_1);
        _builder.append("\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}

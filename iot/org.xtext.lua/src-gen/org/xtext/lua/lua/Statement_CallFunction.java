/**
 */
package org.xtext.lua.lua;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement Call Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.lua.lua.Statement_CallFunction#getObject <em>Object</em>}</li>
 *   <li>{@link org.xtext.lua.lua.Statement_CallFunction#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.lua.lua.LuaPackage#getStatement_CallFunction()
 * @model
 * @generated
 */
public interface Statement_CallFunction extends Statement_FunctioncallOrAssignment
{
  /**
   * Returns the value of the '<em><b>Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Object</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object</em>' containment reference.
   * @see #setObject(Expression)
   * @see org.xtext.lua.lua.LuaPackage#getStatement_CallFunction_Object()
   * @model containment="true"
   * @generated
   */
  Expression getObject();

  /**
   * Sets the value of the '{@link org.xtext.lua.lua.Statement_CallFunction#getObject <em>Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Object</em>' containment reference.
   * @see #getObject()
   * @generated
   */
  void setObject(Expression value);

  /**
   * Returns the value of the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arguments</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arguments</em>' containment reference.
   * @see #setArguments(Functioncall_Arguments)
   * @see org.xtext.lua.lua.LuaPackage#getStatement_CallFunction_Arguments()
   * @model containment="true"
   * @generated
   */
  Functioncall_Arguments getArguments();

  /**
   * Sets the value of the '{@link org.xtext.lua.lua.Statement_CallFunction#getArguments <em>Arguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arguments</em>' containment reference.
   * @see #getArguments()
   * @generated
   */
  void setArguments(Functioncall_Arguments value);

} // Statement_CallFunction

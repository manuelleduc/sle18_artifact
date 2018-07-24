/**
 */
package org.xtext.lua.lua;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.lua.lua.Statement_Assignment#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.xtext.lua.lua.Statement_Assignment#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.lua.lua.LuaPackage#getStatement_Assignment()
 * @model
 * @generated
 */
public interface Statement_Assignment extends Statement_FunctioncallOrAssignment
{
  /**
   * Returns the value of the '<em><b>Variable</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.lua.lua.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' containment reference list.
   * @see org.xtext.lua.lua.LuaPackage#getStatement_Assignment_Variable()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getVariable();

  /**
   * Returns the value of the '<em><b>Values</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.lua.lua.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Values</em>' containment reference list.
   * @see org.xtext.lua.lua.LuaPackage#getStatement_Assignment_Values()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getValues();

} // Statement_Assignment

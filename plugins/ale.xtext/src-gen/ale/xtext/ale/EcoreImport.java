/**
 * generated by Xtext 2.14.0
 */
package ale.xtext.ale;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ecore Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ale.xtext.ale.EcoreImport#getUri <em>Uri</em>}</li>
 * </ul>
 *
 * @see ale.xtext.ale.AlePackage#getEcoreImport()
 * @model
 * @generated
 */
public interface EcoreImport extends EObject
{
  /**
   * Returns the value of the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Uri</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Uri</em>' attribute.
   * @see #setUri(String)
   * @see ale.xtext.ale.AlePackage#getEcoreImport_Uri()
   * @model
   * @generated
   */
  String getUri();

  /**
   * Sets the value of the '{@link ale.xtext.ale.EcoreImport#getUri <em>Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uri</em>' attribute.
   * @see #getUri()
   * @generated
   */
  void setUri(String value);

} // EcoreImport
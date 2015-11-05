/**
 */
package org.amalthea4public.metamodel.trace;

import org.amalthea4public.generic.tracecreation.ArtifactWrapper;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact To Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.amalthea4public.metamodel.trace.ArtifactToArtifact#getSource <em>Source</em>}</li>
 *   <li>{@link org.amalthea4public.metamodel.trace.ArtifactToArtifact#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.amalthea4public.metamodel.trace.TracePackage#getArtifactToArtifact()
 * @model
 * @generated
 */
public interface ArtifactToArtifact extends TraceElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' containment reference.
	 * @see #setSource(ArtifactWrapper)
	 * @see org.amalthea4public.metamodel.trace.TracePackage#getArtifactToArtifact_Source()
	 * @model containment="true"
	 * @generated
	 */
	ArtifactWrapper getSource();

	/**
	 * Sets the value of the '{@link org.amalthea4public.metamodel.trace.ArtifactToArtifact#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(ArtifactWrapper value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' containment reference.
	 * @see #setTarget(ArtifactWrapper)
	 * @see org.amalthea4public.metamodel.trace.TracePackage#getArtifactToArtifact_Target()
	 * @model containment="true"
	 * @generated
	 */
	ArtifactWrapper getTarget();

	/**
	 * Sets the value of the '{@link org.amalthea4public.metamodel.trace.ArtifactToArtifact#getTarget <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' containment reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(ArtifactWrapper value);

} // ArtifactToArtifact

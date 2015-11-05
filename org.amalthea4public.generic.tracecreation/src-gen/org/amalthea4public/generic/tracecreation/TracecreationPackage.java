/**
 */
package org.amalthea4public.generic.tracecreation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.amalthea4public.generic.tracecreation.TracecreationFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel basePackage='org.amalthea4public.generic'"
 * @generated
 */
public interface TracecreationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tracecreation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.amalthea4public.generic.tracecreation";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tracecreation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracecreationPackage eINSTANCE = org.amalthea4public.generic.tracecreation.impl.TracecreationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.amalthea4public.generic.tracecreation.impl.ArtifactWrapperImpl <em>Artifact Wrapper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.amalthea4public.generic.tracecreation.impl.ArtifactWrapperImpl
	 * @see org.amalthea4public.generic.tracecreation.impl.TracecreationPackageImpl#getArtifactWrapper()
	 * @generated
	 */
	int ARTIFACT_WRAPPER = 0;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER__URI = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Artifact Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER__ARTIFACT_HANDLER = 2;

	/**
	 * The number of structural features of the '<em>Artifact Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Artifact Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_WRAPPER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.amalthea4public.generic.tracecreation.ArtifactWrapper <em>Artifact Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Wrapper</em>'.
	 * @see org.amalthea4public.generic.tracecreation.ArtifactWrapper
	 * @generated
	 */
	EClass getArtifactWrapper();

	/**
	 * Returns the meta object for the attribute '{@link org.amalthea4public.generic.tracecreation.ArtifactWrapper#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.amalthea4public.generic.tracecreation.ArtifactWrapper#getUri()
	 * @see #getArtifactWrapper()
	 * @generated
	 */
	EAttribute getArtifactWrapper_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.amalthea4public.generic.tracecreation.ArtifactWrapper#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.amalthea4public.generic.tracecreation.ArtifactWrapper#getName()
	 * @see #getArtifactWrapper()
	 * @generated
	 */
	EAttribute getArtifactWrapper_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.amalthea4public.generic.tracecreation.ArtifactWrapper#getArtifactHandler <em>Artifact Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Handler</em>'.
	 * @see org.amalthea4public.generic.tracecreation.ArtifactWrapper#getArtifactHandler()
	 * @see #getArtifactWrapper()
	 * @generated
	 */
	EAttribute getArtifactWrapper_ArtifactHandler();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TracecreationFactory getTracecreationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.amalthea4public.generic.tracecreation.impl.ArtifactWrapperImpl <em>Artifact Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.amalthea4public.generic.tracecreation.impl.ArtifactWrapperImpl
		 * @see org.amalthea4public.generic.tracecreation.impl.TracecreationPackageImpl#getArtifactWrapper()
		 * @generated
		 */
		EClass ARTIFACT_WRAPPER = eINSTANCE.getArtifactWrapper();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_WRAPPER__URI = eINSTANCE.getArtifactWrapper_Uri();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_WRAPPER__NAME = eINSTANCE.getArtifactWrapper_Name();

		/**
		 * The meta object literal for the '<em><b>Artifact Handler</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_WRAPPER__ARTIFACT_HANDLER = eINSTANCE.getArtifactWrapper_ArtifactHandler();

	}

} //TracecreationPackage

/**
 */
package org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.impl;

import org.amalthea4public.tracemanagement.generic.artifacts.ArtifactWrapper;

import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.ArtifactToArtifact;
import org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.TracemetamodelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Artifact To Artifact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.impl.ArtifactToArtifactImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.amalthea4public.tracemanagement.simpletrace.tracemetamodel.impl.ArtifactToArtifactImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArtifactToArtifactImpl extends TraceElementImpl implements ArtifactToArtifact {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected ArtifactWrapper source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected ArtifactWrapper target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactToArtifactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemetamodelPackage.Literals.ARTIFACT_TO_ARTIFACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactWrapper getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (ArtifactWrapper)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactWrapper basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(ArtifactWrapper newSource) {
		ArtifactWrapper oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactWrapper getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (ArtifactWrapper)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactWrapper basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(ArtifactWrapper newTarget) {
		ArtifactWrapper oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__SOURCE:
				setSource((ArtifactWrapper)newValue);
				return;
			case TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__TARGET:
				setTarget((ArtifactWrapper)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__SOURCE:
				setSource((ArtifactWrapper)null);
				return;
			case TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__TARGET:
				setTarget((ArtifactWrapper)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__SOURCE:
				return source != null;
			case TracemetamodelPackage.ARTIFACT_TO_ARTIFACT__TARGET:
				return target != null;
		}
		return super.eIsSet(featureID);
	}

} //ArtifactToArtifactImpl

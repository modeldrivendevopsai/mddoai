/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resources</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl#getBuilds <em>Builds</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl#getContainers <em>Containers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl#getPipelines <em>Pipelines</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl#getWebhooks <em>Webhooks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl#getPackages <em>Packages</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourcesImpl extends MinimalEObjectImpl.Container implements Resources {
	/**
	 * The cached value of the '{@link #getBuilds() <em>Builds</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuilds()
	 * @generated
	 * @ordered
	 */
	protected EList<BuildResource> builds;

	/**
	 * The cached value of the '{@link #getContainers() <em>Containers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<ContainerResource> containers;

	/**
	 * The cached value of the '{@link #getPipelines() <em>Pipelines</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPipelines()
	 * @generated
	 * @ordered
	 */
	protected EList<PipelineResource> pipelines;

	/**
	 * The cached value of the '{@link #getRepositories() <em>Repositories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepositories()
	 * @generated
	 * @ordered
	 */
	protected EList<RepositoryResource> repositories;

	/**
	 * The cached value of the '{@link #getWebhooks() <em>Webhooks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebhooks()
	 * @generated
	 * @ordered
	 */
	protected EList<WebhookResource> webhooks;

	/**
	 * The cached value of the '{@link #getPackages() <em>Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageResource> packages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourcesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.RESOURCES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<BuildResource> getBuilds() {
		if (builds == null) {
			builds = new EObjectContainmentEList<BuildResource>(BuildResource.class, this,
					AzuredevopsMMPackage.RESOURCES__BUILDS);
		}
		return builds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ContainerResource> getContainers() {
		if (containers == null) {
			containers = new EObjectContainmentEList<ContainerResource>(ContainerResource.class, this,
					AzuredevopsMMPackage.RESOURCES__CONTAINERS);
		}
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PipelineResource> getPipelines() {
		if (pipelines == null) {
			pipelines = new EObjectContainmentEList<PipelineResource>(PipelineResource.class, this,
					AzuredevopsMMPackage.RESOURCES__PIPELINES);
		}
		return pipelines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<RepositoryResource> getRepositories() {
		if (repositories == null) {
			repositories = new EObjectContainmentEList<RepositoryResource>(RepositoryResource.class, this,
					AzuredevopsMMPackage.RESOURCES__REPOSITORIES);
		}
		return repositories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<WebhookResource> getWebhooks() {
		if (webhooks == null) {
			webhooks = new EObjectContainmentEList<WebhookResource>(WebhookResource.class, this,
					AzuredevopsMMPackage.RESOURCES__WEBHOOKS);
		}
		return webhooks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PackageResource> getPackages() {
		if (packages == null) {
			packages = new EObjectContainmentEList<PackageResource>(PackageResource.class, this,
					AzuredevopsMMPackage.RESOURCES__PACKAGES);
		}
		return packages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.RESOURCES__BUILDS:
			return ((InternalEList<?>) getBuilds()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.RESOURCES__CONTAINERS:
			return ((InternalEList<?>) getContainers()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.RESOURCES__PIPELINES:
			return ((InternalEList<?>) getPipelines()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.RESOURCES__REPOSITORIES:
			return ((InternalEList<?>) getRepositories()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.RESOURCES__WEBHOOKS:
			return ((InternalEList<?>) getWebhooks()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.RESOURCES__PACKAGES:
			return ((InternalEList<?>) getPackages()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.RESOURCES__BUILDS:
			return getBuilds();
		case AzuredevopsMMPackage.RESOURCES__CONTAINERS:
			return getContainers();
		case AzuredevopsMMPackage.RESOURCES__PIPELINES:
			return getPipelines();
		case AzuredevopsMMPackage.RESOURCES__REPOSITORIES:
			return getRepositories();
		case AzuredevopsMMPackage.RESOURCES__WEBHOOKS:
			return getWebhooks();
		case AzuredevopsMMPackage.RESOURCES__PACKAGES:
			return getPackages();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case AzuredevopsMMPackage.RESOURCES__BUILDS:
			getBuilds().clear();
			getBuilds().addAll((Collection<? extends BuildResource>) newValue);
			return;
		case AzuredevopsMMPackage.RESOURCES__CONTAINERS:
			getContainers().clear();
			getContainers().addAll((Collection<? extends ContainerResource>) newValue);
			return;
		case AzuredevopsMMPackage.RESOURCES__PIPELINES:
			getPipelines().clear();
			getPipelines().addAll((Collection<? extends PipelineResource>) newValue);
			return;
		case AzuredevopsMMPackage.RESOURCES__REPOSITORIES:
			getRepositories().clear();
			getRepositories().addAll((Collection<? extends RepositoryResource>) newValue);
			return;
		case AzuredevopsMMPackage.RESOURCES__WEBHOOKS:
			getWebhooks().clear();
			getWebhooks().addAll((Collection<? extends WebhookResource>) newValue);
			return;
		case AzuredevopsMMPackage.RESOURCES__PACKAGES:
			getPackages().clear();
			getPackages().addAll((Collection<? extends PackageResource>) newValue);
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
		case AzuredevopsMMPackage.RESOURCES__BUILDS:
			getBuilds().clear();
			return;
		case AzuredevopsMMPackage.RESOURCES__CONTAINERS:
			getContainers().clear();
			return;
		case AzuredevopsMMPackage.RESOURCES__PIPELINES:
			getPipelines().clear();
			return;
		case AzuredevopsMMPackage.RESOURCES__REPOSITORIES:
			getRepositories().clear();
			return;
		case AzuredevopsMMPackage.RESOURCES__WEBHOOKS:
			getWebhooks().clear();
			return;
		case AzuredevopsMMPackage.RESOURCES__PACKAGES:
			getPackages().clear();
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
		case AzuredevopsMMPackage.RESOURCES__BUILDS:
			return builds != null && !builds.isEmpty();
		case AzuredevopsMMPackage.RESOURCES__CONTAINERS:
			return containers != null && !containers.isEmpty();
		case AzuredevopsMMPackage.RESOURCES__PIPELINES:
			return pipelines != null && !pipelines.isEmpty();
		case AzuredevopsMMPackage.RESOURCES__REPOSITORIES:
			return repositories != null && !repositories.isEmpty();
		case AzuredevopsMMPackage.RESOURCES__WEBHOOKS:
			return webhooks != null && !webhooks.isEmpty();
		case AzuredevopsMMPackage.RESOURCES__PACKAGES:
			return packages != null && !packages.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ResourcesImpl

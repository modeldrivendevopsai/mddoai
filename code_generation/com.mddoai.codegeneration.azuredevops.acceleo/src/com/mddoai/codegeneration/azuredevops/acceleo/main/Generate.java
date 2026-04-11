/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package com.mddoai.codegeneration.azuredevops.acceleo.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.engine.event.IAcceleoTextGenerationListener;
import org.eclipse.acceleo.engine.generation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Entry point of the 'Generate' generation module.
 *
 * @generated
 */
public class Generate extends AbstractAcceleoGenerator {
    /**
     * The name of the module.
     *
     * @generated
     */
    public static final String MODULE_FILE_NAME = "/com/mddoai/codegeneration/azuredevops/acceleo/main/generate";

    /**
     * The name of the templates that are to be generated.
     *
     * @generated
     */
    public static final String[] TEMPLATE_NAMES = { "generateElement" };

    /**
     * The list of properties files from the launch parameters (Launch configuration).
     *
     * @generated
     */
    private List<String> propertiesFiles = new ArrayList<String>();

    /**
     * @generated
     */
    public Generate() {
        // Empty implementation
    }

    /**
     * @generated
     */
    public Generate(URI modelURI, File targetFolder,
            List<? extends Object> arguments) throws IOException {
        initialize(modelURI, targetFolder, arguments);
    }

    /**
     * @generated
     */
    public Generate(EObject model, File targetFolder,
            List<? extends Object> arguments) throws IOException {
        initialize(model, targetFolder, arguments);
    }

    /**
     * @generated
     */
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Arguments not valid : {model, folder}.");
            } else {
                URI modelURI = URI.createFileURI(args[0]);
                File folder = new File(args[1]);
                List<String> arguments = new ArrayList<String>();
                Generate generator = new Generate(modelURI, folder, arguments);
                for (int i = 2; i < args.length; i++) {
                    generator.addPropertiesFile(args[i]);
                }
                generator.doGenerate(new BasicMonitor());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @generated
     */
    @Override
    public void doGenerate(Monitor monitor) throws IOException {
        super.doGenerate(monitor);
    }

    /**
     * @generated
     */
    @Override
    public List<IAcceleoTextGenerationListener> getGenerationListeners() {
        List<IAcceleoTextGenerationListener> listeners = super.getGenerationListeners();
        return listeners;
    }

    /**
     * @generated
     */
    @Override
    public IAcceleoGenerationStrategy getGenerationStrategy() {
        return super.getGenerationStrategy();
    }

    /**
     * @generated
     */
    @Override
    public String getModuleName() {
        return MODULE_FILE_NAME;
    }

    /**
     * @generated
     */
    @Override
    public List<String> getProperties() {
        return propertiesFiles;
    }

    /**
     * @generated
     * @since 3.1
     */
    @Override
    public void addPropertiesFile(String propertiesFile) {
        this.propertiesFiles.add(propertiesFile);
    }

    /**
     * @generated
     */
    @Override
    public String[] getTemplateNames() {
        return TEMPLATE_NAMES;
    }

    /**
     * @generated NOT
     */
    @Override
    public void registerPackages(ResourceSet resourceSet) {
        super.registerPackages(resourceSet);
        resourceSet.getPackageRegistry().put(
            com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage.eNS_URI,
            com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage.eINSTANCE);
    }

    /**
     * @generated NOT
     */
    @Override
    public void registerResourceFactories(ResourceSet resourceSet) {
        super.registerResourceFactories(resourceSet);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            "azuredevops", new org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl());
    }

}

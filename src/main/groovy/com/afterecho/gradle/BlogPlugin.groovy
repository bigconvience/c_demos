package com.afterecho.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by jiangbenpeng on 13/06/2017.
 * @author benpeng.jiang
 * @version 1.0.0
 */
class BlogPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        target.extensions.create('bpplugin', BlogPluginExtension)

        target.afterEvaluate {

            println target.extensions.bpplugin.words
            println target.extensions.bpplugin.enumClass
            println target.extensions.bpplugin.outputPackage

            target.tasks.create(name: "showDevices", type: ShowDevicesTask)

            target.android.applicationVariants.all { variant ->
                File inputWordFile = new File(target.projectDir, target.extensions.bpplugin.words)
                File outputDir = new File(target.buildDir, "generated/source/wordsToEnum/${variant.dirName}")
                def task = target.tasks.create(name: "wordsToEnum${variant.name.capitalize()}", type: WordsToEnumTask) {
                    outDir = outputDir
                    wordsFile = inputWordFile
                    enumClassName = target.extensions.bpplugin.enumClass
                    outputPackageName = target.extensions.bpplugin.outputPackage
                }

                variant.registerJavaGeneratingTask task, outputDir
            }
        }
    }
}

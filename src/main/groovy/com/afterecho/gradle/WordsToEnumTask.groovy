package com.afterecho.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory

/**
 * Created by jiangbenpeng on 13/06/2017.
 * @author benpeng.jiang
 * @version 1.0.0
 */
class WordsToEnumTask extends DefaultTask {
    String group = "blogplugin"
    String description = "Makes a list of words into an enum"

    @InputFile
    File wordsFile

    @OutputDirectory
    File outDir

    @TaskAction
    def makeWordsIntoEnums() {
        println wordsFile.absolutePath
        println outDir.absolutePath
    }
}

package com.afterecho.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by jiangbenpeng on 13/06/2017.
 * @author benpeng.jiang
 * @version 1.0.0
 */
class ShowDevicesTask extends DefaultTask {
    String group = "blogplugin"
    String description = "Run adb devices command"

    @TaskAction
    def showDevices() {
        def adbExe = project.android.getAdbExe().toString()
        println "${adbExe} devices".execute().text
    }
}

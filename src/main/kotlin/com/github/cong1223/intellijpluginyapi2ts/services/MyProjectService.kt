package com.github.cong1223.intellijpluginyapi2ts.services

import com.intellij.openapi.project.Project
import com.github.cong1223.intellijpluginyapi2ts.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

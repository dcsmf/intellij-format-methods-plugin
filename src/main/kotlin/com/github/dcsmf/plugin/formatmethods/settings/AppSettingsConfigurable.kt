package com.github.dcsmf.plugin.formatmethods.settings

import com.github.dcsmf.plugin.formatmethods.bundle.SettingsTextBundle.message
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel

class AppSettingsConfigurable(project: Project) : BoundConfigurable(message("settingName")) {

    private val appSettingsState: AppSettingsState = AppSettingsState.getInstance(project)

    override fun createPanel(): DialogPanel {
        return panel {
            group(message("group1")) {
                row {
                    label(message("note"))
                }
                row {
                    label(message("methodExample"))
                }
                twoColumnsRow(
                    {
                        checkBox(message("modifier"))
                            .comment(message("modifierLabel"))
                            .bindSelected({ appSettingsState.modifier },
                                { value -> appSettingsState.modifier = value })
                    },
                    {
                        checkBox(message("genericTypeParameter"))
                            .comment(message("genericTypeParameterLabel"))
                            .bindSelected({ appSettingsState.typeParameter },
                                { value -> appSettingsState.typeParameter = value })
                    }
                )
                twoColumnsRow(
                    {
                        checkBox(message("returnType"))
                            .comment(message("returnTypeLabel"))
                            .bindSelected({ appSettingsState.returnType },
                                { value -> appSettingsState.returnType = value })
                    },
                    {
                        checkBox(message("methodName"))
                            .comment(message("methodNameLabel"))
                            .bindSelected({ appSettingsState.methodName },
                                { value -> appSettingsState.methodName = value })
                    }
                )
                twoColumnsRow(
                    {
                        checkBox(message("parameters"))
                            .comment(message("parametersLabel"))
                            .bindSelected({ appSettingsState.parameters },
                                { value -> appSettingsState.parameters = value })
                    },
                    {
                        checkBox(
                            message("throws")
                        ).comment(message("throwsLabel"))
                            .bindSelected({ appSettingsState.throws },
                                { value -> appSettingsState.throws = value })
                    }
                )
            }
        }
    }
}
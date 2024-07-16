package com.github.dcsmf.plugin.formatmethods.settings

import com.github.dcsmf.plugin.formatmethods.bundle.SettingsTextBundle.message
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel

class AppSettingsConfigurable : BoundConfigurable(message("settingName")) {

    private val appSettingsState: AppSettingsState =
        ApplicationManager.getApplication().getService(AppSettingsState().javaClass)

    override fun createPanel(): DialogPanel {
        return panel {
            group(message("group1")) {
                row {
                    label(message("note"))
                }
                row {
                    label(message("methodExample"))
                }
                threeColumnsRow(
                    {
                        checkBox(message("accessModifier"))
                            .comment(message("accessModifierLabel"))
                            .bindSelected({ appSettingsState.accessModifier },
                                { value -> appSettingsState.accessModifier = value })
                    },
                    {
                        checkBox(message("otherModifier"))
                            .comment(message("otherModifierLabel"))
                            .bindSelected({ appSettingsState.otherModifier },
                                { value -> appSettingsState.otherModifier = value })
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
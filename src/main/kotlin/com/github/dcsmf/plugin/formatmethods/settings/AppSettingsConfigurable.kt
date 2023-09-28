package com.github.dcsmf.plugin.formatmethods.settings

import com.github.dcsmf.plugin.formatmethods.bundle.SettingsTextBundle.message
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.PropertyBinding
import com.intellij.ui.layout.panel
import com.intellij.ui.layout.withSelectedBinding

class AppSettingsConfigurable(project: Project) : BoundConfigurable(message("settingName")) {

    private val appSettingsState: AppSettingsState = AppSettingsState.getInstance(project)

    override fun createPanel(): DialogPanel {
        return panel {
            row{
                label(message("note"))
            }
            titledRow(message("group1")) {
                row {
                    label(message("methodExample"))
                }
                row {
                    checkBox(
                        message("modifier"),
                        comment = message("modifierLabel")
                    ).withSelectedBinding(
                        PropertyBinding({ appSettingsState.modifier },
                            { value -> appSettingsState.modifier = value })
                    )
                }
                row {
                    checkBox(
                        message("genericTypeParameter"),
                        comment = message("genericTypeParameterLabel")
                    ).withSelectedBinding(
                        PropertyBinding({ appSettingsState.typeParameter },
                            { value -> appSettingsState.typeParameter = value })
                    )
                }
                row {
                    checkBox(
                        message("returnType"),
                        comment = message("returnTypeLabel")
                    ).withSelectedBinding(
                        PropertyBinding({ appSettingsState.returnType },
                            { value -> appSettingsState.returnType = value })
                    )
                }
                row {
                    checkBox(
                        message("methodName"),
                        comment = message("methodNameLabel")
                    ).withSelectedBinding(
                        PropertyBinding({ appSettingsState.methodName },
                            { value -> appSettingsState.methodName = value })
                    )
                }
                row {
                    checkBox(
                        message("parameters"),
                        comment = message("parametersLabel")
                    ).withSelectedBinding(
                        PropertyBinding({ appSettingsState.parameters },
                            { value -> appSettingsState.parameters = value })
                    )
                }
                row {
                    checkBox(
                        message("throws"),
                        comment = message("throwsLabel")
                    ).withSelectedBinding(
                        PropertyBinding({ appSettingsState.throws },
                            { value -> appSettingsState.throws = value })
                    )
                }
            }
        }
    }

}
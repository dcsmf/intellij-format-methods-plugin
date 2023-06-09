package com.github.dcsmf.intellijformatmethodsplugin.utils

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.ui.JBColor
import java.awt.Color

@Suppress("unused")
object NotifyUtil {

    @JvmStatic
    fun notifyInfo(anAction: Any, project: Project, content: String) {
        val notification = Notification(
            anAction.hashCode().toString(), I18nBundle.message("name"),
            content, NotificationType.INFORMATION
        )
        Notifications.Bus.notify(notification, project)
    }

    @JvmStatic
    fun notifyWarn(anAction: Any, project: Project?, content: String) {
        val notification = Notification(
            anAction.hashCode().toString(), I18nBundle.message("name"),
            content, NotificationType.WARNING
        )
        Notifications.Bus.notify(notification, project)
    }

    @JvmStatic
    fun notifyError(anAction: Any, project: Project, content: String) {
        val notification = Notification(
            anAction.hashCode().toString(), I18nBundle.message("name"),
            content, NotificationType.ERROR
        )
        Notifications.Bus.notify(notification, project)
    }

    @JvmStatic
    fun showPopupBalloon(editor: Editor, result: String) {
        ApplicationManager.getApplication().invokeLater {
            val factory = JBPopupFactory.getInstance()
            factory.createHtmlTextBalloonBuilder(
                result,
                null,
                JBColor(Color(186, 238, 186), Color(73, 117, 73)),
                null
            )
                .setFadeoutTime(3000)
                .createBalloon()
                .show(factory.guessBestPopupLocation(editor), Balloon.Position.below)
        }
    }
}
package com.github.dcsmf.intellijformatmethodsplugin.action

import com.github.dcsmf.intellijformatmethodsplugin.I18nBundle
import com.github.dcsmf.intellijformatmethodsplugin.utils.NotifyUtil
import com.google.common.collect.Lists
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.impl.source.PsiExtensibleClass
import org.apache.commons.lang.ArrayUtils
import org.apache.commons.lang.StringUtils
import java.util.*
import kotlin.collections.ArrayList

class FormatMethodWithPyramidAction : AnAction() {
    override fun update(e: AnActionEvent) {
        //没有打开文件的时候禁用按钮
        if (null == e.project) {
            e.presentation.isEnabled = false
        }
        if (null ==e.getData(CommonDataKeys.EDITOR)) {
            e.presentation.isEnabled = false
        }
        super.update(e)
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return

        when (PsiDocumentManager.getInstance(project).getPsiFile(editor.document)) {
            null -> {
                NotifyUtil.notifyWarn(e, project, I18nBundle.message("emptyFile"))
                return
            }

            !is PsiJavaFile -> {
                NotifyUtil.notifyWarn(e, project, I18nBundle.message("notJavaFile"))
                return
            }
        }
        val selectionModel = editor.selectionModel
        try {
            if (StringUtils.isBlank(selectionModel.selectedText)) {
                //全部格式化
                formatAllClasses(project, editor)
            } else {
                //格式化选中的
                val start = selectionModel.selectionStart
                val end = selectionModel.selectionEnd
                formatAllClasses(project, editor, start, end)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            NotifyUtil.notifyError(e, project, I18nBundle.message("exception") + ex)
        }
    }

    private fun formatAllClasses(project: Project, editor: Editor) {
        val allClassesList = getAllPsiClasses(project, editor)
        when(allClassesList.stream().mapToInt { sortPsiClass(project, it) }.sum()){
            0->NotifyUtil.showPopupBalloon(editor,I18nBundle.message("sorted"))
            else->NotifyUtil.showPopupBalloon(editor,I18nBundle.message("sortComplete"))
        }
    }

    private fun formatAllClasses(project: Project, editor: Editor, start: Int, end: Int) {

    }

    private fun sortPsiClass(project: Project, currentPsiClass: PsiClass):Int {
        return 0
    }

    private fun getAllPsiClasses(project: Project, editor: Editor): ArrayList<PsiClass> {
        val javaFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) as PsiJavaFile
        val classes = javaFile.classes
        val allClassList = ArrayList<PsiClass>()
        Arrays.stream(classes).forEach {
            allClassList.add(it)

            val innerClasses: List<PsiClass> = when (it) {
                is PsiExtensibleClass -> it.ownInnerClasses
                else -> it.allInnerClasses.toList()
            }
            if (innerClasses.isNotEmpty()) {
                allClassList.addAll(innerClasses)
            }
        }
        return allClassList
    }
}

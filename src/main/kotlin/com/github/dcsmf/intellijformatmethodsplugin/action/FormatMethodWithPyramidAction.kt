package com.github.dcsmf.intellijformatmethodsplugin.action

import com.github.dcsmf.intellijformatmethodsplugin.bundle.TextBundle
import com.github.dcsmf.intellijformatmethodsplugin.model.InsertType
import com.github.dcsmf.intellijformatmethodsplugin.model.SelectSortModel
import com.github.dcsmf.intellijformatmethodsplugin.utils.ElementUtil
import com.github.dcsmf.intellijformatmethodsplugin.utils.MethodUtil.getJvmStyleSignature
import com.github.dcsmf.intellijformatmethodsplugin.utils.NotifyUtil
import com.github.dcsmf.intellijformatmethodsplugin.utils.SortUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.impl.source.PsiExtensibleClass
import com.intellij.util.containers.stream
import org.apache.commons.lang.StringUtils
import java.util.*
import java.util.stream.Collectors

class FormatMethodWithPyramidAction : AnAction() {

    override fun update(e: AnActionEvent) {
        if(ApplicationManager.getApplication().isDisposed){
            return
        }
        //没有打开文件的时候禁用按钮(Disable button when file isn't open)
        if (null == e.project) {
            e.presentation.isEnabled = false
        }
        if (null == e.getData(CommonDataKeys.EDITOR)) {
            e.presentation.isEnabled = false
        }
        super.update(e)
    }

    //格式化方法的按钮被按下(Button press)
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return

        when (PsiDocumentManager.getInstance(project).getPsiFile(editor.document)) {
            null -> {
                NotifyUtil.notifyWarn(e, project, TextBundle.message("emptyFile"))
                return
            }

            !is PsiJavaFile -> {
                NotifyUtil.notifyWarn(e, project, TextBundle.message("notJavaFile"))
                return
            }
        }
        val selectionModel = editor.selectionModel
        try {
            if (StringUtils.isBlank(selectionModel.selectedText)) {
                //全部格式化(Sort all)
                sortAllClasses(project, editor)
            } else {
                //格式化选中的(Sort selections)
                val start = selectionModel.selectionStart
                val end = selectionModel.selectionEnd
                sortAllClasses(project, editor, start, end)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            NotifyUtil.notifyError(e, project, TextBundle.message("exception") + ex)
        }
    }

    private fun sortAllClasses(project: Project, editor: Editor, start: Int = -1, end: Int = -1) {
        val allClassesList = getAllPsiClasses(project, editor)
        when (allClassesList.stream().mapToInt { sortClassBySignature(project, it, start, end) }.sum()) {
            0 -> NotifyUtil.showPopupBalloon(editor, TextBundle.message("sorted"))
            else -> NotifyUtil.showPopupBalloon(editor, TextBundle.message("sortComplete"))
        }
    }

    private fun sortClassBySignature(project: Project, currentPsiClass: PsiClass, start: Int = -1, end: Int = -1): Int {
        var methods = currentPsiClass.methods
        if (methods.isEmpty()) {
            return 0
        }

        var sortModel: SelectSortModel? = null
        if (start != -1 || end != -1) {
            sortModel = SortUtil.getSelectSortModel(currentPsiClass, start, end, methods.toList())
            methods = methods.filter { it.textRange.endOffset in start..end }.toTypedArray()
        }
        val sortedMethods = methods.stream().sorted { o1, o2 ->
            if (o1.isConstructor) {
                return@sorted 1
            }
            val s1 = getJvmStyleSignature(o1)
            val s2 = getJvmStyleSignature(o2)
            s1.length.compareTo(s2.length)
        }.collect(Collectors.toList())
        if (SortUtil.isSameAfterSort(methods, sortedMethods)) {
            return 0
        }
        val copySorted: List<PsiElement> = sortedMethods.map(PsiMethod::copy)
        WriteCommandAction.runWriteCommandAction(project) {
            methods.forEach { ElementUtil.deleteElement(it) }
            if (sortModel == null || sortModel.insertType == InsertType.ADD) {
                copySorted.forEach { ElementUtil.addElement(currentPsiClass, it) }
            } else {
                when (sortModel.insertType) {
                    InsertType.ADD_AFTER -> {
                        // 如果是往后追加，则倒序追加
                        for (index in copySorted.size - 1 downTo 0) {
                            ElementUtil.addAfterElement(currentPsiClass, copySorted[index], sortModel.locationElement!!)
                        }
                    }

                    else -> {
                        // 往前追加，则正序
                        copySorted.forEach {
                            ElementUtil.addBeforeElement(
                                currentPsiClass,
                                it,
                                sortModel.locationElement!!
                            )
                        }
                    }
                }
            }

        }
        return 1
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

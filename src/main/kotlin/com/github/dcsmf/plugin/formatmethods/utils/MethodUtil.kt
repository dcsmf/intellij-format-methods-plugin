package com.github.dcsmf.plugin.formatmethods.utils

import com.github.dcsmf.plugin.formatmethods.settings.AppSettingsState
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiKeyword
import com.intellij.psi.PsiMethod

object MethodUtil {
    /**
     * 构建带权限标识符、返回类型、泛型声明、函数名、参数列表和throws的JVM风格函数定义
     *
     * 原始定义：
     * default <T> void myMethod(String userId, @NotNull Group group, T t) throws Exception {}
     *
     * 返回的：
     * default<T>voidmyMethod(String userId, @NotNull Group group, T t)throws Exception
     */
    @JvmStatic
    fun getJvmStyleSignature(method: PsiMethod, project: Project): String {
        val state: AppSettingsState = AppSettingsState.getInstance(project)
        val returnTypeElementText = when (method.returnTypeElement) {
            null -> ""
            else -> method.returnTypeElement!!.text
        }
        val typeParameterListText = when (method.typeParameterList) {
            null -> ""
            else -> method.typeParameterList!!.text

        }
        val modifier = checkTrueThenConcat(
            method.modifierList.children.filterIsInstance<PsiKeyword>()
                .joinToString("") { it.text }/* Remove the impact of the annotation like @Override */,
            state.modifier
        )
        val typeParameter = checkTrueThenConcat(
            typeParameterListText,
            state.typeParameter
        )
        val returnType = checkTrueThenConcat(
            returnTypeElementText,
            state.returnType
        )
        var methodName = checkTrueThenConcat(method.name, state.methodName)
        val parameters = checkTrueThenConcat(
            method.parameterList.text,
            state.parameters
        )
        val throws = checkTrueThenConcat(method.throwsList.text, state.throws)
        if (modifier.isEmpty() && typeParameter.isEmpty() && returnType.isEmpty() && parameters.isEmpty() && throws.isEmpty()) {
            methodName = method.name
        }
        return modifier + typeParameter + returnType + methodName + throws
    }

//    @JvmStatic
//    fun getJvmStyleSignature(method: PsiMethod, project: Project): String {
//        val returnTypeElementText = when (method.returnTypeElement) {
//            null -> ""
//            else -> method.returnTypeElement!!.text
//        }
//        val typeParameterListText = when (method.typeParameterList) {
//            null -> ""
//            else -> method.typeParameterList!!.text
//
//        }
//        val s =
//            method.modifierList.text + typeParameterListText + returnTypeElementText + method.name + method.parameterList.text + method.throwsList.text
//        return s;
//    }

    private fun checkTrueThenConcat(str: String, state: Boolean): String {
        if (state) {
            return str
        }
        return ""
    }
}
package com.github.dcsmf.plugin.formatmethods.utils

import com.github.dcsmf.plugin.formatmethods.settings.AppSettingsState
import com.intellij.openapi.application.ApplicationManager
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiKeyword
import com.intellij.psi.PsiMethod

object MethodUtil {

    private val accessModifiers = listOf(
        JavaTokenType.PUBLIC_KEYWORD,
        JavaTokenType.PRIVATE_KEYWORD,
        JavaTokenType.PROTECTED_KEYWORD,
        JavaTokenType.DEFAULT_KEYWORD
    )

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
    fun getJvmStyleSignature(method: PsiMethod): String {
        val state: AppSettingsState = ApplicationManager.getApplication().getService(AppSettingsState().javaClass)
        val returnTypeElementText = when (method.returnTypeElement) {
            null -> ""
            else -> method.returnTypeElement!!.text
        }
        val typeParameterListText = when (method.typeParameterList) {
            null -> ""
            else -> method.typeParameterList!!.text
        }
        // Remove the impact of the annotation like @Override
        val modifierList = method.modifierList.children.filterIsInstance<PsiKeyword>()
            .groupBy { accessModifiers.contains(it.tokenType) }
        val accessModifier = checkTrueThenConcat(
            modifierList.getOrDefault(true, ArrayList()).joinToString("") { it.text },
            state.accessModifier
        )
        val otherModifier = checkTrueThenConcat(
            modifierList.getOrDefault(false, ArrayList()).joinToString("") { it.text },
            state.otherModifier
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
        if (accessModifier.isEmpty() && typeParameter.isEmpty() && returnType.isEmpty() && parameters.isEmpty() && throws.isEmpty()) {
            methodName = method.name
        }
        return accessModifier + otherModifier + typeParameter + returnType + methodName + parameters + throws
    }

    private fun checkTrueThenConcat(str: String, state: Boolean): String {
        if (state) {
            return str
        }
        return ""
    }
}
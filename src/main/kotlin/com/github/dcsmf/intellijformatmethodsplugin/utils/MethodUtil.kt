package com.github.dcsmf.intellijformatmethodsplugin.utils

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
    fun getJvmStyleSignature(method: PsiMethod): String {
        val returnTypeElementText = when (method.returnTypeElement) {
            null -> ""
            else -> method.returnTypeElement!!.text
        }
        val typeParameterListText = when (method.typeParameterList) {
            null -> ""
            else -> method.typeParameterList!!.text

        }
        return method.modifierList.text + typeParameterListText + returnTypeElementText + method.name + method.parameterList.text + method.throwsList.text
    }
}
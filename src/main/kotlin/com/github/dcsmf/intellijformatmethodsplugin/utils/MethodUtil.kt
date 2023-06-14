package com.github.dcsmf.intellijformatmethodsplugin.utils

import com.intellij.psi.PsiMethod

object MethodUtil {
    /**
     * 构建带权限标识符、返回类型、泛型声明、函数名、参数列表和throws的JVM风格函数定义
     * default <T> void buildDefaultField(String userId, @NotNull Group group, T t) throws Exception {System.out.println(t);}
     *
     */
    @JvmStatic
    fun getJvmStyleSignature(method: PsiMethod): String {
        if(method.isConstructor){
            val returnTypeElement=method.returnTypeElement
        }
        return with(method) {
            modifierList.text
        }
    }
}
package com.github.dcsmf.plugin.formatmethods.utils

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement

object ElementUtil {
    @JvmStatic
    fun deleteElement(element: PsiElement) {
        element.delete()
    }

    @JvmStatic
    fun addElement(currentClass: PsiClass, element: PsiElement) {
        currentClass.add(element)
    }

    @JvmStatic
    fun addAfterElement(currentClass: PsiClass, addElement: PsiElement, locationElement: PsiElement) {
        currentClass.addAfter(addElement, locationElement)
    }

    @JvmStatic
    fun addBeforeElement(currentClass: PsiClass, addElement: PsiElement, locationElement: PsiElement) {
        currentClass.addBefore(addElement, locationElement)
    }
}
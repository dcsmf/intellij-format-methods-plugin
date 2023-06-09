package com.github.dcsmf.intellijformatmethodsplugin.utils

import com.github.dcsmf.intellijformatmethodsplugin.model.InsertType
import com.github.dcsmf.intellijformatmethodsplugin.model.selectSortModel
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import com.intellij.psi.impl.source.PsiExtensibleClass
import org.apache.commons.lang.ArrayUtils

object SortUtil {
    @JvmStatic
    fun isSameAfterSort(allMethods: Array<PsiMethod>, sorted: List<PsiMethod>): Boolean {
        for ((index, e) in allMethods.withIndex()) {
            if (e != sorted[index]) {
                return false
            }
        }
        return true
    }

    @JvmStatic
    fun getSelectSortModel(
        currentClass: PsiClass,
        start: Int,
        end: Int,
        methodsList: List<PsiMethod>
    ): selectSortModel {
        val sortModel: selectSortModel
        var location: PsiMethod? = null
        //从头往后找
        var min: Int = Int.MAX_VALUE
        for (psiMethod in methodsList) {
            val temp = start - psiMethod.textRange.endOffset
            if (temp in 1 until min) {
                location = psiMethod
                min = temp
            }
        }
        //没找到就从后往前
        if (location == null) {
            min = Int.MAX_VALUE
            for (psiMethod in methodsList) {
                val temp = psiMethod.textRange.startOffset - end
                if (temp in 1 until min) {
                    location = psiMethod
                    min = temp
                }
            }
            if (location == null) {
                val methods = currentClass.allMethods

                if (ArrayUtils.isEmpty(methods)) {
                    sortModel = selectSortModel(start, end, InsertType.ADD, null)
                } else {
                    var psiMethods = methods.toList()
                    if (currentClass is PsiExtensibleClass) {
                        psiMethods = currentClass.ownMethods
                    }
                    sortModel = if (psiMethods.isNotEmpty()) {
                        selectSortModel(start, end, InsertType.ADD_BEFORE, psiMethods[0])
                    } else {
                        selectSortModel(start, end, InsertType.ADD, null)
                    }
                }
            } else {
                sortModel = selectSortModel(start, end, InsertType.ADD_BEFORE, location)
            }
        } else {
            sortModel = selectSortModel(start, end, InsertType.ADD_AFTER, location)
        }
        return sortModel
    }
}
package com.github.dcsmf.plugin.formatmethods.utils

import com.github.dcsmf.plugin.formatmethods.model.InsertType
import com.github.dcsmf.plugin.formatmethods.model.SelectSortModel
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

    /**
     * 寻找应在哪插入方法
     * Find where to insert the method
     */
    @JvmStatic
    fun getSelectSortModel(
        currentClass: PsiClass,
        start: Int,
        end: Int,
        methodsList: List<PsiMethod>
    ): SelectSortModel {
        var location: PsiMethod? = null

        // 从前往后查找
        // Look from front to back
        var min: Int = Int.MAX_VALUE
        for (psiMethod in methodsList) {
            val temp = start - psiMethod.textRange.endOffset
            if (temp in 1 until min) {
                location = psiMethod
                min = temp
            }
        }
        location?.let { return SelectSortModel(start, end, InsertType.ADD_AFTER, location) }

        // 没找到就从后往前找
        // If not found, Look from back to front
        min = Int.MAX_VALUE
        for (psiMethod in methodsList) {
            val temp = psiMethod.textRange.startOffset - end
            if (temp in 1 until min) {
                location = psiMethod
                min = temp
            }
        }
        location?.let { return SelectSortModel(start, end, InsertType.ADD_BEFORE, location) }

        // 还是没找到，范围扩充到查找该类所有方法
        // Still not found, the scope expands to find all methods of the class
        val methods = currentClass.allMethods
        return if (ArrayUtils.isEmpty(methods)) {
            SelectSortModel(start, end, InsertType.ADD, null)
        } else {
            var psiMethods = methods.toList()
            if (currentClass is PsiExtensibleClass) {
                psiMethods = currentClass.ownMethods
            }
            if (psiMethods.isNotEmpty()) {
                SelectSortModel(start, end, InsertType.ADD_BEFORE, psiMethods[0])
            } else {
                SelectSortModel(start, end, InsertType.ADD, null)
            }
        }
    }
}
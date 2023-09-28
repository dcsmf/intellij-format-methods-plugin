package com.github.dcsmf.plugin.formatmethods.model

import com.intellij.psi.PsiElement

data class SelectSortModel(
    val startLine: Int,
    val endLine: Int,
    val insertType: InsertType,
    val locationElement: PsiElement?
)

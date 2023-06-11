package com.github.dcsmf.intellijformatmethodsplugin.model

import com.intellij.psi.PsiElement

data class SelectSortModel(
    val startLine: Int,
    val endLine: Int,
    val insertType: InsertType,
    val locationElement: PsiElement?
)

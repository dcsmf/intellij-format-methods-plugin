package com.github.dcsmf.plugin.formatmethods.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@Service
@State(
    name = "com.github.dcsmf.plugin.formatmethods.settings.AppSettingsState",
    storages = [Storage("setting.xml")]
)
class AppSettingsState : PersistentStateComponent<AppSettingsState> {
    /**
     * 访问修饰符，如public
     */
    var accessModifier: Boolean = true

    /**
     * 其他修饰符，如synchronized
     */
    var otherModifier: Boolean = true

    /**
     * 泛型参数，如<T>
     */
    var typeParameter: Boolean = true

    /**
     * 返回值，如void
     */
    var returnType: Boolean = true

    /**
     * 函数名
     */
    var methodName: Boolean = true

    /**
     * 参数列表，如(int a, int b)
     */
    var parameters: Boolean = true

    /**
     * 异常抛出，如 throws Exception
     */
    var throws: Boolean = true

    override fun getState(): AppSettingsState = this

    override fun loadState(state: AppSettingsState) = XmlSerializerUtil.copyBean(state, this)

}
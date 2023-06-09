# intellij-format-methods-plugin

![Build](https://github.com/dcsmf/intellij-format-methods-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Introduction
<!-- Plugin description -->
这个插件可以将Java文件的方法排序，按方法签名的长度由短到长排序(This plug-in can sort the methods of Java files, from short to long, according to the length of the method signature)

通过 <kbd>代码</kbd> > <kbd>FormatMethodPyramid</kbd> 使用本插件(To use it, select <kbd>Code</kbd> > <kbd>FormatMethodPyramid</kbd> )

格式化例子见下(For example):

```java
public interface Demo {

    void one();

    void two(String str, int i, double d);

    void one(String str);

    void one(int i);
}
```

格式化后(After handle):

```java
public interface Demo {

    void one();

    void one(int i);

    void one(String str);

    void two(String str, int i, double d);
}
```
<!-- Plugin description end -->

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Adjust the [pluginGroup](./gradle.properties), [plugin ID](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [ ] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the `PLUGIN_ID` in the above README badges.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

## Installation

- 使用idea内置的插件市场(Using IDE built-in plugin system):

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "intellij-format-methods-plugin"</kbd> >
  <kbd>Install Plugin</kbd>

- 手动安装(Manually):

  Download the [latest release](https://github.com/dcsmf/intellij-format-methods-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
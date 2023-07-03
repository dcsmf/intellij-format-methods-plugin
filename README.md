# intellij-format-methods-plugin

![Build](https://github.com/dcsmf/intellij-format-methods-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/com.github.dcsmf.intellij-format-methods-plugin.svg)](https://plugins.jetbrains.com/plugin/com.github.dcsmf.intellij-format-methods-plugin)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/com.github.dcsmf.intellij-format-methods-plugin.svg)](https://plugins.jetbrains.com/plugin/com.github.dcsmf.intellij-format-methods-plugin)

## Introduction
This plug-in can sort the methods of Java files, from short to long, according to the length of the method signature

To use it, select <kbd>Code</kbd> > <kbd>FormatMethodPyramid</kbd>

For example:

```java
public interface Demo {

    void one();

    void two(String str, int i, double d);

    void one(String str);

    void one(int i);
}
```

After handle:

```java
public interface Demo {

    void one();

    void one(int i);

    void one(String str);

    void two(String str, int i, double d);
}
```

## Installation

- Using IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "intellij-format-methods-plugin"</kbd> >
  <kbd>Install Plugin</kbd>

- Manually:

  Download the [latest release](https://github.com/dcsmf/intellij-format-methods-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

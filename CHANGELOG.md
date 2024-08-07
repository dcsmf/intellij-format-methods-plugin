<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# intellij-format-methods-plugin Changelog

## [Unreleased]

## [2.0.2] (2024/07/16)

- The access modifiers and other modifiers have been separated, and now they can each be sorted independently
- 分离了访问修饰符和其他修饰符，现在可以各自进行单独的排序

## [2.0.1] (2024/01/02)

- Fix some bugs
- 修复了一些问题

## [2.0.0] (2023/12/07)

- Now minimum support *IDEA* version is 2023.3
- Now change JVM language level from 11 to 17
- 更改最低支持*IDEA*版本为 2023.3
- 现在编译的语言版本由11改为17

## [1.1.1] (2023/10/27)

- Fixed *IDEA* compatible error
- 修复*IDEA*的兼容性错误

## [1.1.0] (2023/10/25)

- Now minimum support *IDEA* version is 2021.3
- The settings interface replaces the previous *Kotlin UI DSL Version 1* with *Kotlin UI DSL Version 2*
- 更改最低支持*IDEA*版本为 2021.3
- 设置页面使用*Kotlin UI DSL Version 2*替换之前的*Kotlin UI DSL Version 1*

## [1.0.8] (2023/10/17)

- Fixed an issue where parameter lists were ignored when getting function signatures
- 修复获取函数签名时忽略了参数列表的问题

## [1.0.7] (2023/10/10)

- Fixed the effect of the annotations like `@Override` on sorting
- 修复了像`@Override`等注解对排序的影响

## [1.0.6] (2023/09/28)

- Add plugin settings, now you can customize the sort by
- 新增插件设置，你可以自定义排序依据了

## [1.0.5] (2023/06/25)

- Clean up some excess code
- Resolve the `com.intellij.serviceContainer.AlreadyDisposedException` caused
- 清理部分多余代码
- 解决引发的`com.intellij.serviceContainer.AlreadyDisposedException`

## [1.0.3] (2023/06/15)

- Fix method sorting errors
- 修复函数排序不对的问题

## [1.0.2] (2023/06/12)

- The version number is more prescriptive
- Now change JVM language level from 17 to 11
- Now minimum support idea version is 2020.1
- 版本号更规范
- 现在编译的语言版本由17改为11
- 现在最低支持idea 2020.1

## [1.0.1] (2023/06/08)

- Methods sort feature finished
- 函数排序功能完成

[Unreleased]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v2.0.2...HEAD
[2.0.2]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v2.0.1...v2.0.2
[2.0.1]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v2.0.0...v2.0.1
[2.0.0]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.1.1...v2.0.0
[1.1.1]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.1.0...v1.1.1
[1.1.0]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.0.8...v1.1.0
[1.0.8]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.0.7...v1.0.8
[1.0.7]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.0.6...v1.0.7
[1.0.6]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.0.5...v1.0.6
[1.0.5]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.0.3...v1.0.5
[1.0.3]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.0.2...v1.0.3
[1.0.2]: https://github.com/dcsmf/intellij-format-methods-plugin/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/dcsmf/intellij-format-methods-plugin/commits/v1.0.1

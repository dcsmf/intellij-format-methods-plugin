<p>这个插件可以将Java文件的方法排序，按方法签名的长度由短到长排序(This plug-in can sort the methods of Java files, from short to long, according to the length of the method signature)</p>
<p>通过 <b>代码 > FormatMethodPyramid</b> 使用本插件(To use it, select <b>Code > FormatMethodPyramid</b> )</p>

<p>格式化例子见下(For example):</p>
<ul>
    <li>void one();</li>
    <li>void two(String str, int i, double d);</li>
    <li>void one(String str);</li>
    <li>void one(int i);</li>
</ul>

<p>格式化后(After handle):</p>
<ul>
    <li>void one();</li>
    <li>void one(int i);</li>
    <li>void one(String str);</li>
    <li>void two(String str, int i, double d);</li>
</ul>
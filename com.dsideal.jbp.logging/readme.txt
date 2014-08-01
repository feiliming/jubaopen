slf4j + logback
1.new Plug in Project, 不使用template

2.lib添加jar,并add to build path

3.logback.xml配置

4.添加jar包到Run Time
运行时META-INF -> MANIFEST.MF -> Runtime -> Classpath和Imported Packages
编译时META-INF -> MANIFEST.MF -> Dependencies -> Imported Packages -> Add -> org.slf4j
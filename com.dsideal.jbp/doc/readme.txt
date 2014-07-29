1.创建项目
new Eclipse 4 Application Project

2.修改标题和图标
Application.e4xmi -> Windows -> Trimmed Window
修改Label和Icon URI，图片使用32*32即可

3.设置登录窗口
MANIFEST.MF -> Extensions -> org.eclipse.core.runtime.products
添加property，lifeCycleURI = bundleclass://com.dsideal.jbp.login/com.dsideal.jbp.login.Login

MANIFEST.MF -> Dependencies
添加依赖com.dsideal.jbp.login

com.dsideal.jbp.product -> Dependencies -> Add Required Plug-ins
添加依赖com.dsideal.jbp.login，否则运行时缺失依赖

登录详细参加com.dsideal.jbp.login插件

4.屏幕大小、位置
Application.e4xmi -> Windows -> Trimmed Window -> Bounds(x,y,w,h)
但是怎么才能居中显示呢? 暂时不知道, 暂时设置成全屏显示

全屏显示
Application.e4xmi -> Windows -> Trimmed Window -> Supplementary
添加tags：shellMaximized
最小化是shellMinimized

5.注意事项
运行出错时检查Run Configurations配置
修改不起作用修改Run Configurations配置
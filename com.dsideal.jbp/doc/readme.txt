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
使用css后界面渲染慢

6.添加Part(Viewer和Editor)
Application.e4xmi -> Windows -> Trimmed Window -> Controls -> 
Perspective Stack -> Perspective -> Controls -> PartSashContainer -> PartStack -> Part
在PartSashContainer设置左右或上下布局, 左右宽度使用Container Data, PartSashContainer = PartStack + PartStack + 。。。

7.将Model Element与class、resource关联, 即指定Part的class URI和Icon URI
bundleclass://com.dsideal.jbp.bt/com.dsideal.jbp.bt.parts.MultiplePart
platform:/plugin/com.dsideal.jbp.bt/icons/calc16.png
com.dsideal.jbp.bt是插件名称

8.添加菜单
Application.e4xmi -> Windows -> Trimmed Window -> Main Menu -> Menu
DirectMenuItem直接指定Handler class
HandledMenuItem指定Command





99.
@PostConstruct Is called after the class is constructed and the field and method injection has been performed.  
@PreDestroy Is called before the class is destroyed. Can be used to clean up resources.  
@Focus Indicates that this method should be called, once the Part gets the focus.  
@Persists Is called if a save request on the Part is triggered. Used by the part service to identify the method to call if a save is triggered via this service.  
@PersistState Is called before the model object is disposed, so that the part is able to save its instance state. Also called before the method annotated with @PreDestroy is called.  

100.通过Dependency Injection和Annotations访问Model object
MApplication：Describes the application object. All other model elements are below this object.  
MAddon：A self-contained component typically without user interface. It can register for events in the application life cycle and handles these events.  
MWindow：Represents a window in your application.  
MTrimmedWindow：Similar to MWindow but allows to contain toolbars for the windows (via TrimBars model elements).  
MPerspective：Object for the perspective model element. Can only be contained in a MPerspectiveStack.  
MPart：Represents the model element part, e.g. a view or an editor.  
MDirtyable：Property of MPart which can be injected. If set to true, this property informs the Eclipse platform that this Part contains unsaved data (is dirty). In a handler you can query this property to trigger a save.  
MPartDescriptor：MPartDescriptor is a template for new parts. A new part based on this PartDescriptor can be created and shown via the EPartService.showPart() method.  
Snippets：Snippets can be used to pre-configure model parts which you want to create via your program. You can use the Eclipse EModelService to clone such a snippet and use the result object to attach it to the application model at runtime.  

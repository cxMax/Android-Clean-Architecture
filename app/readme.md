# 单module - app开发层级结构
## app
与application相关的都放在这个module , 例如 : 初始化application onCreate()
## base
base基类 , 例如 : baseFragment , baseActivity
## componenet
抽出来可以复用的单独的模块, 例如 : Popup , LoadMoreModule , RefreshModule and so on
## dagger
init 参数 解偶
## module
app各个UI模块,包含业务逻辑.  
使用MVP模式的情况下可以按照UI & Present分包;  
若不使用MVP模式.也可以直接按照各module分包,在其内部在细分UI & Bussiness
## model
涉及到app相关的DB , HTTP , Rx , Bean;  
单module的情况下,model放在这里
如果项目依赖其他的android library , 需要把这个model抽成单独的module被其他module依赖.
### DB
数据库相关
### HTTP
HTTP请求相关
### RxBus
RxBus相关的都放在这里
### Bean
javaBean实例对象
## Util
相关Util类 , 与Component区别去,这个仅仅是一个util类.
component是单独抽离的模块服用
## widget
自定义控件,毋须赘述

##proguard
第三方library的引用的proguard放在app_module下

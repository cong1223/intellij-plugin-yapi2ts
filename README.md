# intellij-plugin-yapi2ts
<a href="https://www.jetbrains.com/webstorm"><img src="https://cdn.jsdelivr.net/gh/cong1223/cloudimg@master/img/20211109162916.png" width = "10%" /></a><br/>
[![](https://badgen.net/badge/yapi2ts/v1/f2a)](http://git.hljnbw.cn/WEB_GROUP/intellij-plugin-yapi2ts.git)

<!-- Plugin description -->
### description：

yapi is a tool to generate interface data ts type with one click according to the detailed address of the interface document

### 描述：

yapi是根据接口文档详情地址一键生成接口数据ts类型的工具

### Features：

- Generate ts type code according to the link address

功能：

- 根据链接地址生成ts类型代码

### Todo:

- Generate axios business request code according to the link address

### 待办：

- 根据链接地址生成axios业务请求代码
<!-- Plugin description end -->

## Template ToDo list
- [x] 根据yapi文档接口详情链接一键生成ts类型代码
- [ ] 一键生成业务请求代码

## 安装

  Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "yapi2ts"</kbd> >
  <kbd>Install Plugin</kbd>
  
## 使用前注意
**!!!务必先配置项目token: settings -> tools -> yapi2ts**
## 使用
1. 配置yapi文档项目的token
   ![](https://qnm.hunliji.com/Fo6wg9gECcIn_ZncVmwZSvljUCPz)
2. 在需要生成类型文件的js/ts/jsx/tsx文件中打开tools -> yapi2ts 或者 右键文件打开菜单,选中yapi2ts打开工具弹窗
   ![](https://picgo-cloudimg.oss-cn-hangzhou.aliyuncs.com/img/202301011533894.jpg)
   ![](https://picgo-cloudimg.oss-cn-hangzhou.aliyuncs.com/img/202301011533892.jpg)
3. 输入接口详情链接地址,选择生成请求体类型还是返回数据类型
   ![](https://cdn.jsdelivr.net/gh/cong1223/cloudimg@master/img/20211109180242.png)
[template]: https://github.com/JetBrains/intellij-platform-plugin-template

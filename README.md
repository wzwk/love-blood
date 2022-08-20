<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">爱献血1.13.0</h1>
<h4 align="center">纯公益爱心服务平台，让爱源源流动。</h4>
<h4 align="center">
<img src="https://img.shields.io/badge/springboot-v2.4.0-green" />
<img src="https://img.shields.io/badge/springcloudalibaba-v2021.1-green" />
</h4>
---
项目访问地址 ：[爱献血](http://175.178.237.123:8888/index.html#/)

## 本程序的功能是什么？
为广大市民提供寻求献血帮助的需求发布平台，同时也可以接入各大医疗站点，提供血液库存信息，鼓励市民主动献血，通过爱心等级、在线电子献血证等方式激励市民献血，从根本上解决”血荒“问题。同时也提供献血常识、献血要求、献血相关知识解答市民疑惑，排除献血误会，粉碎献血谣言，让市民学习到更多关于无偿献血的知识。还提供在线预约献血服务。

## 程序主要功能完成度

 - [x] 用户管理模块[登录、注册、找回密码、个人信息]
 - [ ] 寻求帮助模块[发布]
 - [ ] 我的关注列表模块[关注列表、取消关注]
 - [ ] 我的发布列表模块[发布列表、转发、删除、修改]
 - [ ] 我的帮助列表模块[帮助列表、转发、无法帮助]
 - [ ] 帮助内容显示模块[文章显示、转发、帮助]
 - [ ] 个人消息模块[显示帮助信息、显示官方推送信息]
 - [ ] 血液库存模块[血液库存信息]
 - [ ] 献血知识库模块[献血流程 献血误区  献血百科]
 - [ ] 预约献血模块
 - [ ] 意见反馈模块

## 主要服务
本项目由本人多次重构，经历了springboot 到 springcloud的构建，通过对系统的研究，最终将系统分为以下四个服务，方便扩展

- 1、网关服务
- 2、文章服务
- 3、第三方服务
- 3、公共服务模块

## 后端技术栈

- 基于 SpringBoot + springCoudAlibaba +  Mybatis Plus+ gateway + mysql + redis + rabbitMq 构建的爱献血平台
- 使用 feign 进行服务间的调用
- 提供 lambda 、stream api 、webflux 的生产实践

## 前端技术栈

- uni-app  是一个使用 Vue.js 开发所有前端应用的框架,者编写一套代码，可发布到 iOS、Android、Web（响应式）、以及各种小程序（微信/支付宝/百度/头条/飞书/QQ/快手/钉钉/淘宝）和快应用等多个平台。
- Uview  
- uni-request
- axios
- web-view 插件式开发

---


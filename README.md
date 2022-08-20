<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">爱献血</h1>
<h4 align="center">纯公益爱心服务平台，让爱源源流动。</h4>
<h4 align="center">
<img src="https://img.shields.io/badge/springboot-v2.4.0-green" />
<img src="https://img.shields.io/badge/springCloudAlibaba-v2021.1-green" />
</h4>
本系统采用docker进行部署
项目访问地址 ：[爱献血](http://175.178.237.123:8888/index.html#/)
---


## 简介？
为广大市民提供寻求献血帮助的需求发布平台，同时也可以接入各大医疗站点，提供血液库存信息，鼓励市民主动献血，通过爱心等级、在线电子献血证等方式激励市民献血，从根本上解决”血荒“问题。同时也提供献血常识、献血要求、献血相关知识解答市民疑惑，排除献血误会，粉碎献血谣言，让市民学习到更多关于无偿献血的知识。还提供在线预约献血，意见反馈等服务，鼓励更多人加入到献血这个队伍中来，并且根据各种奖励机制和互帮互助的形式来让人们感受到献血的价值与意义，提高用户献血的积极性。

## 主要功能

 - [ ] 用户管理模块[登录、注册、找回密码、个人信息]
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

- 基于 SpringBoot + springCloudAlibaba +  Mybatis Plus+ gateway + mysql + redis + rabbitMq + docker构建的爱献血平台
- 使用 feign 进行服务间的调用
- 提供 lambda 、stream api 、webflux 的生产实践

## 前端技术栈

- uni-app  是一个使用 Vue.js 开发所有前端应用的框架,者编写一套代码，可发布到 iOS、Android、Web（响应式）、以及各种小程序（微信/支付宝/百度/头条/飞书/QQ/快手/钉钉/淘宝）和快应用等多个平台。
- Uview  
- uni-request
- axios
- web-view 插件式开发 <br/>

![image align="center"](https://user-images.githubusercontent.com/63549640/185752914-e01cdbb1-05ae-46e7-ac37-f3accee586c4.png)

## 系统部分界面展示：

<h4 align="center">
<p>登录界面<p> 
<img src="https://user-images.githubusercontent.com/63549640/185752970-33796e89-0a1c-407d-852f-f825838076e4.png" /> <br/>
<p>首页<p>
<img src="https://user-images.githubusercontent.com/63549640/185753022-39ab991e-4f91-4f88-b030-216aa346a5b3.png" /> <br/>
<p>献血知识库<p>
<img src="https://user-images.githubusercontent.com/63549640/185753076-f125b615-c860-4ed7-af68-4f25d3c7c180.png" /> <br/>
<p>信息列表<p>
<img src="https://user-images.githubusercontent.com/63549640/185752982-4289f421-9f3f-44c0-8899-ab3261355052.png" /> <br/>
<p>发布寻求<p>
<img src="https://user-images.githubusercontent.com/63549640/185753083-0fa10a38-8666-4739-8423-7bf4df7649ce.png" /> <br/>
<p>爱心等级<p>
<img src="https://user-images.githubusercontent.com/63549640/185753092-ea40ddce-196a-4f97-81ee-fd2e271fc93c.png" /> <br/>
</h4>


---


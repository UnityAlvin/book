# 前言
这是疫情期间跟着尚硅谷视频做的一个书城项目，昨天在整理SSM文档的时候，发现还在草稿箱里面，我闲着没事就发出去了，没想到还真有人看，但是文档根本就没写完（笑），因为当时也没考虑过给所有人看，主要是想着自己看的，不过项目所实现的功能还是都在文档里面写出来了，虽然写的也不全，但是配合着视频，还是能看懂的，今天看到有小伙伴想要源码，我就先把源码分享出来，后续的再慢慢补充。
# 开发工具
IDEA2019
# 开发环境
JDK1.8+MySQL5.6+Tomcat8.0

# 主要技术
JSP+Servlet
# 源码
Github：[https://github.com/UnityAlvin/book](https://github.com/UnityAlvin/book)
感觉本项目对你有所帮助的话，请在Github右上角点个Star，谢谢！


# 项目功能
## 用户
### 页面JSP动态化
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409123630547.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
- 在 html 页面顶行添加 page 指令。
- 修改文件后缀名为：.jsp
- 使用 IDEA 搜索替换.html 为.jsp(快捷键：Ctrl+Shift+R)
- regist.jsp
### 提取页面中相同的内容
- head.jsp、footer.jsp、login_success_menu.jsp、manager_menu.jsp

### 注册、登录失败后，页面保存部分数据
- 使用EL表达式设置需要保存数据的标签value值，动态获取Servlet中设置的返回信息
- regist.jsp、login.jsp、UserServlet.java

### 合并注册、登录Servlet
- 实际项目中，一个模块，一般只使用一个 Servlet 程序，所以使用反射提取
- UserServlet.java

### 创建BaseServlet
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409124523754.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
- 使用反射优化大量 else if 
- BaseServlet.java、UserServlet.java

### 数据的封装和提取 BeanUtils 的使用
BeanUtils 工具类，它可以一次性的把所有请求的参数注入到 JavaBean 中。
BeanUtils 工具类，经常用于把 Map 中的值注入到 JavaBean 中，或者是对象属性值的拷贝操作。
BeanUtils 它不是 Jdk 的类。而是第三方的工具类。所以需要导包。

1、导入需要的 jar 包：
- commons-beanutils-1.8.0.jar
- commons-logging-1.1.1.jar

2、编写 WebUtils 工具类使用
- WebUtils.java
### 使用EL表达式修改表单回显
- login.jsp

## 图书
- book_manager.jsp、book_edit.jsp、
- bookServlet.java
- bookDao.java、bookDaoImpl.java、
- Book.java
### 图书列表
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409131111542.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)

### 添加图书
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409131142707.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
### 给WebUtils工具类添加转换 int  类型的工具方法
- WebUtils.java

### 删除图书
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409131223591.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)

### 修改图书
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409131232103.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)

## 分页
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020040913222611.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
### 分页模型Page
- Page.java

### 实现列表分页
- BookDaoImpl.java
- BookService.java、BookServiceImpl.java
- BookServlet.java
- manager_menu.jsp、book_manager.jsp
### 首页、上一页、下一页、末页
- page_nav.jsp
### 分页模块中跳转到指定页数功能实现
- page_nav.jsp
- Page.java：setPageNo()
- BookService.java：page()
### 点击页码跳转、自定义显示页码
- 显示 5 个连续的页码，而且当前页码在中间。除了当前页码之外，每个页码都可以点击跳到指定页。
- page_nav.jsp
### 修改分页后，增加，删除，修改图书信息的回显页面
以修改图书为例：

- book_manager.jsp中，在修改请求地址上追加当前页码参数
- book_edit.jsp中，使用隐藏域记录下 pageNo 参数
- book_servlet.jsp中，在服务器重定向的时候，获取当前页码追加上进行跳转

### 首页 index.jsp 的跳转
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409134932492.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
### 分页条的提取
#### 在 page  对象中添加 url 
- Page.java
#### 在 Servlet  程序的 page  分页方法中设置 url 的分页请求地址
- BookServlet.java
### 修改分页条中请求地址为 url  变量输出, 并抽取一个单独的 jsp  页面
- nav_book.java

### 首页价格搜索
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409135953914.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
- ClientBookServlet.java、BookService.java、
- BookDao.java、BookDaoImpl.java
- index.jsp


## 登录、注销、验证码
### 登录-显示用户名
- UserServlet 程序中保存用户登录的信息
- 修改 login_succuess_menu.jsp
- 修改Index.jsp
### 注销
- 销毁 Session 中用户登录的信息（或者销毁 Session）
- 重定向到首页（或登录页面）。
- UserServlet.jsp、index.jsp


###  谷歌 kaptcha 图片验证码的使用
 表单重复提交
 - 提交完表单，服务器使用请求转发来进行页面跳转，此时，用户按下F5，就会发起最后一次的请求，造成表单重复提交问题，**可使用重定向跳转解决**。
- 用户正常提交服务器，但是由于网络延迟等原因，导致用户多次点击提交按钮，也会造成表单重复提交，**可以采用验证码校验。**
- 用户正常提交服务器，服务器也没有延迟，但是提交完成后，用户回退浏览器，重新提交，也会造成表单重复提交，**可以采用验证码校验。**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200407225746847.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)

谷歌验证码 kaptcha 使用步骤如下：
- 导入谷歌验证码的 jar 包：kaptcha-2.3.2.jar
- 在 web.xml 中去配置用于生成验证码的 Servlet 程序
- 在页面表单中使用
- 在服务器比较谷歌生成的验证码和客户端发送过来的验证码
- 切换验证码
- regist.jsp、UserServlet.java、


## 购物车
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409123101348.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
### 购物车模型
- Cart.java、CartItem.java

### 加入购物车
- CartServlet .java
- index.jsp
### 展示购物车
- cart.jsp

### 删除购物车商品项
- CartServlet.java
- cart.jsp

### 清空购物车
- CartServlet.java
- cart.jsp
### 修改购物车商品数量
- CartServlet.java
- cart.jsp
## 首页购物车数据回显
- 在添加商品到购物车的时候，保存最后一个添加的商品名称 CartServlet.java 
- 在 index.jsp 页面中输出购物车信息

## 订单
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200409215835537.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
### 生成订单
- 商品添加到购物车之后，点击去结账，会创建一个对应购物车的订单
- ClientOrderServlet.java、order.jsp
### 查询所有订单（管理员）
- 分页展示出所有用户的订单信息
- OrderServlet.java、order_manager.jsp
### 发货
- 向未发货的用户发货
- OrderServlet.java、order_manager.jsp
### 查看订单详情
- 根据订单查看订单详细信息
- OrderServlet.java、order_detail.jsp
### 查看我的订单
- 根据用户id查询属于自己的订单信息
- ClientOrderServlet.java、order.jsp
### 签收订单
- 用户可在我的订单内签收已发货的订单
- ClientOrderServlet.java、order.jsp

## 未登录、数据库
### 使用Filter过滤界面、请求
- 用户未登录时无法访问订单、管理员界面
- web.xml、ManagerFilter.java

### 使用ThreadLocal确保所有操作用一个Connection实现事务处理
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415105501389.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
- getConnection()、commitAndClose()、rollbackAndClose()-------JDBCUtils.java
- 抛出CRUD中try-catch的异常------BaseDao.java

### 使用Filter为所有Service方法都加上Try-Catch来管理事务
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200415113810294.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsdmluMTk5NzY1,size_16,color_FFFFFF,t_70)
- 抛出doPost()try-catch的异常------BaseServlet.java
- 统一管理所有的事务------TransactionFilter.java
- 添加TransactionFilter的配置------web.xml

### 使用Tomcat统一管理异常界面
- 创建异常界面------error500.jsp、error404.jsp
- 配置error标签------web.xml

### 注册时使用AJAX请求验证用户名是否可用
- 在js中添加事件------user.jsp
- ajaxExistsUsername()------UserServlet.java

### 使用AJAX把商品添加到购物车
- 在js中添加事件------index.jsp
- ajaxAddItem()------CartServlet.jsp

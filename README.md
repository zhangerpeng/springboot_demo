

# Springboot-demo

## Springboot-基础应用

### 一.环境情报

DB:postgresql-9.4.0-1-windows-x64

JDK：18.151

MAVEN:apache-maven-3.3.9

编译器：ideaIU-2020.1.2

笔记：

### 二.环境构筑

#### 2.1DB环境构筑

```SQL
建表语句

--
-- PostgreSQL database dump
--
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: t_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE t_role (
    user_id character varying(4),
    user_author character(4) DEFAULT 'USER'::bpchar NOT NULL
);


ALTER TABLE t_role OWNER TO postgres;

--
-- Name: t_user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE t_user (
    user_id character varying(4) NOT NULL,
    user_name character varying(20),
    user_birth date,
    address character varying(30),
    tel_num character varying(11),
    user_pass character varying(65),
    user_status character varying(4) DEFAULT 'INIT'::character varying NOT NULL,
    last_update_time timestamp(0) without time zone DEFAULT ('now'::text)::timestamp(0) with time zone NOT NULL,
    recode_version integer DEFAULT 0 NOT NULL
);


ALTER TABLE t_user OWNER TO postgres;

--
-- Data for Name: t_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY t_role (user_id, user_author) FROM stdin;
0002	USER
0003	USER
0004	USER
0005	USER
0006	USER
0007	USER
0007	ADMN
\N	ADMN
\N	USER
\N	ADMN
\N	USER
\N	ADMN
\N	USER
\N	ADMN
\N	USER
\N	ADMN
\N	USER
\N	ADMN
\N	USER
0008	ADMN
0008	USER
0001	ADMN
0001	USER
\.


--
-- Data for Name: t_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY t_user (user_id, user_name, user_birth, address, tel_num, user_pass, user_status, last_update_time, recode_version) FROM stdin;
0003	banana	1993-03-03	東京都中央区築地一丁目1-1	09012345678	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	ACTV	2015-06-04 14:29:00	0
0004	peach	1994-04-04	東京都中央区銀座一丁目7-12	0387654321	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	ACTV	2015-06-05 14:29:00	0
0005	pineapple	1995-05-05	東京都千代田区有楽町一丁目11-1	08012345678	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	ACTV	2015-06-06 14:29:00	0
0006	pear	1996-06-06	東京都千代田区霞が関二丁目1-1	08087654321	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	RMVD	2015-06-07 14:29:00	0
0007	apple7	1990-01-01	西安	0012001300	$2a$10$/q65inY2r/F7m0GqgyO4EOlOeZI2z1S4T52PPflQqEzoI9Y1DERg6	RMVD	2019-07-15 11:41:42	0
0008	AUTO	1990-01-01	西安	0123456789	$2a$10$yXFs5oQYAT9M8.PgQeV3uOQav4JN33wYL6PKkdSWhBNmEFAVYiLkO	INIT	2019-08-20 03:13:12	0
0001	apple	1991-01-01	東京都江東区豊洲四丁目1-1	0312345678	$2a$10$W.Qwx9mEHzCRQZBZ4CZoHuPVjFfg8ZjDLuF83JnEVQAZz0yMCbwda	ACTV	2019-08-20 03:59:44	1
0002	orange	1992-02-02	東京都中央区月島一丁目3-9	0387654321	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	RMVD	2015-06-03 14:29:00	0
\.


--
-- Name: t_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (user_id);


--
-- Name: fk_t_user_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY t_role
    ADD CONSTRAINT fk_t_user_role FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


```

##### 补充说明 postgreSQL的常用命令

```DB
psql 命令
登录数据库
导入SQL脚本
登录POSTGRESQL的命令窗体
#postgres

一.创建DB
1.在postgreSQL的命令窗体中进行SQL来创建
  #postgres create database dbname
2.使用createdb 命令创建
  createdb [options] [dbname[description]]
  
二.选择对应数据库
\c + dbname 用于进入指定的DB
 postgres# \c dbname
 
三.postgreSQL中的dump文档导出，导出的dump文档进行DB恢复
2.1 dump文件的导出
  pg_dump [option][dbname]
  eg：pg_dump -f D:\user.dump -d dbname -U 用户名称
2.2 使用导出的dump恢复数据库
  pg_restore [option][file]
  
psql -U [用户名] -f [备份的dump.sql文档] -d [数据库]
psql -U postgres -f D:\git_repository\Springboot-demo\user.sql -d rubdb
pg_dump -d[数据库] -U[用户名称] -f [需要备份到的文件]
 
```



#### 2.2blank-project构筑

```pom
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.4.1</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>jp.co.ntt</groupId>
	<artifactId>usermanager</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>usermanager</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.thymeleaf.extras</groupId>
			<artifactId>thymeleaf-extras-springsecurity5</artifactId>
		</dependency>

		<!--====thymeleaf相关JAR导入 ==== -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<!--thymeleaf语法校验关闭 -->
		<dependency>
			<groupId>net.sourceforge.nekohtml</groupId>
			<artifactId>nekohtml</artifactId>
			<version>1.9.22</version>
		</dependency>
		<!--====thymeleaf相关JAR终了==== -->

		<!--====springmvc相关JAR导入==== -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!--====springmvc相关JAR终了==== -->
		<!--====webJARs导入==== -->
		<!--bootstrap导入 -->
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>3.3.7-1</version>
		</dependency>
		<!--导入jquery -->
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>3.1.1</version>
		</dependency>
		<!--====webJARs终了==== -->
		
		<!--====mybtais与spring的整合的jar导入==== -->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.1.4</version>
		</dependency>
		<!--====mybtais与spring的整合的jar导入终了==== -->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.amqp</groupId>
			<artifactId>spring-rabbit-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>·
	</build>

</project>
```



###### 2.2.1 整合mybatis 时接口无法被注入

![mybatis-flow](D:\git_repository\Springboot-demo\image\mybatis-flow.PNG)

```db
补充说明：
spring与mybatis进行整合
1.spring首先扫描方式，获取Mapper接口是否存在。但此时代理对象不生成
2.sqlsessionFactory生成时，读取mapper.xml配置文件依据配置的命名空间获取对应的字节码文件，生成对应的对象，将生成的对象设定到Configruration中，此时记录刚当的XML与接口已经被解析。
3.spring中实施注入时，生成代理对象。依据mapper接口名查找mapper.xml文件
```

```
项目中mybatis与接口的配置方式四种
1.依据mapper的路径，设定具体的mappper[mapper接口与xml的路径必须一致]
<mappers>
   <mapper class|URL|RESOURCE>
<mappers>
<mappers>
  <package>
<mappers>
2.未配置mapper节点时，
 2.1使用sqlSessionFactory的mapperlocations属性加载依赖的xml
 2.2 结合类【MapperScannerConfigure】对于依赖的接口进行扫描
```



核心代码:

```java
1、首先根据MapperScannerConfigurer进行包扫描，扫描Mapper接口，生成Spring特定的描述，并将其交由MapperProxyFactory管理，后期会由其生成动态代理对象。 
ClassOathMapperScanner 中的doScan()方法。 
1. GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
2.
3.     if (logger.isDebugEnabled()) {
4.          logger.debug("Creating MapperFactoryBean with name '" + holder.getBeanName() 
5.              + "' and '" + definition.getBeanClassName() + "' mapperInterface");
6.        }
7.
8.        // the mapper interface is the original class of the bean
9.        // but, the actual class of the bean is MapperFactoryBean
10.        definition.getPropertyValues().add("mapperInterface", definition.getBeanClassName());
11.        definition.setBeanClass(MapperFactoryBean.class);
12.
13.        definition.getPropertyValues().add("addToConfig", this.addToConfig);
```

2.初始化sqlsessionFactoryBean,首先判定mybatis.xml中是否有节点mappers,若有设定解析接口并添加到configuration对象中

```java
 XMLConfigBuilder中的mapperElement()方法 
 
 1.private void mapperElement(XNode parent) throws Exception {
2.    if (parent != null) {
3.      for (XNode child : parent.getChildren()) {
4.        if ("package".equals(child.getName())) {
5.          String mapperPackage = child.getStringAttribute("name");
6.          configuration.addMappers(mapperPackage);
7.        } else {
8.          String resource = child.getStringAttribute("resource");
9.          String url = child.getStringAttribute("url");
10.          String mapperClass = child.getStringAttribute("class");
11.          if (resource != null && url == null && mapperClass == null) {
12.            ErrorContext.instance().resource(resource);
13.            InputStream inputStream = Resources.getResourceAsStream(resource);
14.            XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
15.            mapperParser.parse();
16.          } else if (resource == null && url != null && mapperClass == null) {
17.            ErrorContext.instance().resource(url);
18.            InputStream inputStream = Resources.getUrlAsStream(url);
19.            XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, url, configuration.getSqlFragments());
20.            mapperParser.parse();
21.          } else if (resource == null && url == null && mapperClass != null) {
22.            Class<?> mapperInterface = Resources.classForName(mapperClass);
23.            configuration.addMapper(mapperInterface);
24.          } else {
25.            throw new BuilderException("A mapper element may only specify a url, resource or class, but not more than one.");
26.          }
27.        }
28.      }
29.    }
30.  }
```

3.判定sqlSessionFactoryBean中是否设定了mapperLocations属性，即就是对于mapper.xml位置，配置后，解析mapper.xml，依据其中的namespaces获取对应的接口，并添加到COnfiguration中

```java
XMLMapperBuilder中的bindMapperForNamespace()方法。 
private void bindMapperForNamespace() {
2.    String namespace = builderAssistant.getCurrentNamespace();
3.    if (namespace != null) {
4.      Class<?> boundType = null;
5.      try {
6.        boundType = Resources.classForName(namespace);
7.      } catch (ClassNotFoundException e) {
8.        //ignore, bound type is not required
9.      }
10.      if (boundType != null) {
11.        if (!configuration.hasMapper(boundType)) {
12.          // Spring may not know the real resource name so we set a flag
13.          // to prevent loading again this resource from the mapper interface
14.          // look at MapperAnnotationBuilder#loadXmlResource
15.          configuration.addLoadedResource("namespace:" + namespace);
16.          configuration.addMapper(boundType);
17.        }
18.      }
19.    }
20.  }

```

4.如果上述【mapper节点与mapperLocations】的配置都没有，使用包扫描的获取的mapper接口解析mapper对象并将其添加到Configuration中

```java
  MapperRegistry总的addMapper()方法 
  public <T> void addMapper(Class<T> type) {
2.    if (type.isInterface()) {
3.      if (hasMapper(type)) {
4.        throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
5.      }
6.      boolean loadCompleted = false;
7.      try {
8.        knownMappers.put(type, new MapperProxyFactory<T>(type));
9.        // It's important that the type is added before the parser is run
10.        // otherwise the binding may automatically be attempted by the
11.        // mapper parser. If the type is already known, it won't try.
12.        MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
13.        parser.parse();
14.        loadCompleted = true;
15.      } finally {
16.        if (!loadCompleted) {
17.          knownMappers.remove(type);
18.        }
19.      }
20.    }
21.  }

```

5.1依据接口信息获取mapper.xml文件，并且进行文件的校验

```java
MapperAnnotationBuilder中的parse()方法 
public void parse() {
2.    String resource = type.toString();
3.    if (!configuration.isResourceLoaded(resource)) {
4.      loadXmlResource();
5.      configuration.addLoadedResource(resource);
6.      assistant.setCurrentNamespace(type.getName());
7.      parseCache();
8.      parseCacheRef();
9.      Method[] methods = type.getMethods();
10.      for (Method method : methods) {
11.        try {
12.          if (!method.isBridge()) { // issue #237
13.            parseStatement(method);
14.          }
15.        } catch (IncompleteElementException e) {
16.          configuration.addIncompleteMethod(new MethodResolver(this, method));
17.        }
18.      }
19.    }
20.    parsePendingMethods();
21.  }

```

5.2MapperAnotationBuilder中的loadXmlResource（）方法

```jjava
private void loadXmlResource() {
2.    // Spring may not know the real resource name so we check a flag
3.    // to prevent loading again a resource twice
4.    // this flag is set at XMLMapperBuilder#bindMapperForNamespace
5.    if (!configuration.isResourceLoaded("namespace:" + type.getName())) {
6.      String xmlResource = type.getName().replace('.', '/') + ".xml";
7.      InputStream inputStream = null;
8.      try {
9.        inputStream = Resources.getResourceAsStream(type.getClassLoader(), xmlResource);
10.      } catch (IOException e) {
11.        // ignore, resource is not required
12.      }
13.      if (inputStream != null) {
14.        XMLMapperBuilder xmlParser = new XMLMapperBuilder(inputStream,                  assistant.getConfiguration(), xmlResource, configuration.getSqlFragments(), type.getName());
15.        xmlParser.parse();
16.      }
17.    }
18.  }

5.2 解析Mapper接口以及mapper.xml文件，将方法与sql文件进行绑定。 
5.3 使用JDK动态代理技术生成Mapper接口的代理对象，添加到Configuration中。 
```





### 三.Springboot中的Springsecurity

#### 1,核心配置 —自定义认证适配器WebSecurityConfig

```java
package jp.co.ntt.usermanager.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import jp.co.ntt.usermanager.domain.service.share.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	// 异常【There is no PasswordEncoder mapped for the id "null"】
	/**
	 * 原因： 这个错主要发生在Spring-Sercurity5.X版本上， 例如SpringBoot2.x。
	 * 导致这个错误发生主要原因就是在之前版本中的NoOpPasswordEncoder被DelegatingPasswordEncoder取代了，
	 * 而保存在数据库中的密码没有没有指定加密方式。
	 * 
	 * @return
	 */
	public NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()// 表单登录 来身份认证时，关于首页必须准许访问
				.loginPage("/login.html").permitAll().loginProcessingUrl("/authentication/login")// 自定义登录路径
				.passwordParameter("password").usernameParameter("userId").successForwardUrl("/login/success")
				.failureForwardUrl("/login/failure").and().authorizeRequests()// 对请求授权
				.antMatchers("/login.html", "/authentication/login").permitAll().anyRequest() // 任何请求
				.authenticated()// ; // 都需要身份认证
				.and().csrf().disable();// 禁用跨站攻击

	}

	/**
	 * 配置忽略静态文件
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	}

}
```

#### 2.Thymeleaf中使用SpringSecurity的tag

###### 2.1pom中的依赖

```java

<dependency>
   <groupId>org.thymeleaf.extras</groupId>
  <artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>


```

###### 2.2 html文档中

```java
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
```

###### 2.3应用

```JAVAV
判断用户是否已经登陆认证，引号内的参数必须是isAuthenticated()。
sec:authorize="isAuthenticated()"

获得当前用户的用户名，引号内的参数必须是name。
sec:authentication=“name”

判断当前用户是否拥有指定的权限。引号内的参数为权限的名称。
sec:authorize=“hasRole(‘role’)”

获得当前用户的全部角色，引号内的参数必须是principal.authorities。
sec:authentication="principal.authorities"
```







### 四,thymeleaf中的模板布局

### 五,问题一览

#### 5.1 CSS中的相对与绝对的定位

position 中有四个值

1.static【默认】2.relative 3.absolute 绝对 4.fixed

绝对定位：【距离文档流中最近的绝对或相对元素的定位的定位】

相对定位：【 相对于原位置定位的相对定位，此属性依然在文档的流中，不影响其他的元素】

| 值        | 描述                                       |
| :------- | ---------------------------------------- |
| absolute | 生成绝对定位的元素，相对于static定位以外的第一个父元素进行定位。元素的位置：left,top,right,bottom的属性进行设定 |
| fixed    | 生成绝对的定位元素，相对于浏览器窗口进行定位。元素的位置：left,top,right,bottom的属性进行设定 |
| relative | 生成相对定位的元素。相对于其正常位置进行的定位。                 |
| static   | 默认值，没有定位。元素出现在正常的流中                      |
| inherit  | 规定元素从父元素中继承position属性中的值                 |






# <center>Gradle培训</center>
## Groovy 基础
   groovy是一种脚本语言，gradle脚本是通过groovy编写，所以很有必要学习一下groovy，需要注意的是否groovy完全兼容Java
，所以可以通过Java编写gradle脚本，google的Android拓展插件就是通过Java编写的。
</br>需要学习的基础:
    1. 变量、方法声明，同java，在Java的基础上增加弱语言方式，关键词为def
	2. 数据类型：兼容Java、闭包（很多时候可以理解为Java的lambda表达式）、增强集合、文件和file（本质的在Java类型的基础上增加了很多工具方法）
	3. 简单语法：属性自动附带set/get方式、with多次操作、$操作

### 1、变量、方法声明
```
 def a="A";//弱类型声明，编译器自动推断类型，等价与String a="A"
 String b="B";
 
 println "var a=$a b=$b";
  
```
### 2、数据类型
```
 //这里提一下集合，集合可以通过[]直接赋值，类似与js。
 //例子
 def list=[1,"2",3]
 def map=["name":"sen","age":18]
```

### 3、闭包
    闭包非常重要，是我们看懂脚本的必备武器,这里举个用Android build.gradle例子，分析一下. 
	说例子前看看语法：</br>	
```
  //语法结构：{args->body}
  def clos={a,b->
     println a
     println b
  }
  	

```
实际应用例子，其他的脚本都可以根据这个去例子去分析</br>
	
```
//在看例子前看看这个结构：

    methodName params //这里解析一下，刚用gradle的时候一直以为这个是一个配置，原来这里是调用一个方法的意思，

//例子

android {
    compileSdkVersion 28 //这里解析一下，刚用gradle的时候一直以为这个是一个配置，原来这里是调用一个方法的意思，
   
  
  //方式1:这里将一个闭包是直接赋值，其实我们采用方式2
	defaultConfig {
		 
        applicationId "ac692x_case.jieli.com.myapplication"//调用方法赋值
        minSdkVersion 18//调用方法赋值
        targetSdkVersion 28//调用方法赋值
        versionCode 1//调用方法赋值
        versionName "1.0"//调用方法赋值
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"//调用方法赋值
    }
	
	//方式2：
	def config={
	  applicationId "ac692x_case.jieli.com.myapplication"//调用方法赋值
	  minSdkVersion 18//调用方法赋值
	  targetSdkVersion 28//调用方法赋值
	  versionCode 1//调用方法赋值
	  versionName "1.0"//调用方法赋值
	  testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"//调用方法赋值
	}
	
	defaultConfig config;
	
	
}
```

## Gradle基础
	在Gradle有两个最重要的概念：Project和Task，Gradle里的所有东西都是由这两个东西组成的。
在project代表的是一个工程，或者一个应用，或者一个library，在Android中我们可以认为是工程和
modlue。一个project中可以包含多个Task，奇怪的我们在build.gradle脚本中并没有看见过脚本，
原因后面讲插件的时候再说。



### 1、Task定义
	Task由配置、doFirst、doLast 3部分组成。注意一点：配置部分会在配置项目的时候执行，
	可以理解为只要执行项目中任意的task就会执行task配置
```
    //配置
	task hello{
		println "config task"
	}
	
	//任务执行时最开始的操作
	hello.doFirst{
		println 'task doFirst''
	}
	
	//任务执行时最后的操作
	hello.doLast{
		println 'task doLast'
	}
	

   //便捷方式
   task hello << {
	   println 'task doLast'
   }
    
```

### 2、任务参数

|参数|	含义|	默认值 |
|-------|--------|----------|
|name |task的名字	|不能为空，必须指定|
|type |task的“父类”	|DefaultTask|
|overwrite|	是否替换已经存在的task	|false|
|dependsOn|	task依赖的task的集合	|[]|
|group	|task属于哪个组|	null|
|description|task的描述|	null|

 ```
  task('config'){
	  name 'test'
  }
 ```
 
### 3、任务依赖
    依赖关系是保证task执行顺序的关键部分，
```
task task1 << { 
	println ' task1 '
}

task task2 << {
	println ' task2 '
}

task1.dependsOn task2;//这样task1就会在task2执行完毕后再执行


```
### 4、Project
    Project在我们Android可以理解为build.gradle文件，project中提供了很多遍历的方法给我们操作，如task、
	file等操作.
```
 //获取属性，属性包含了很多配置信息
  project.properties
  //获取工程路径,在知道工程路径的基础上，我们可以通过project的file接口访问文件
  project.projectDir
  
```

### 5、应用实例，打包Jar包
```
task buildJar(type: Jar, dependsOn: 'assembleRelease') {
    def rootPath = project.projectDir.getPath();
    def srcDir = rootPath + "/build/intermediates/classes/release"
    destinationDir = file("out")//通过project的文件接口新建一个文件
    delete destinationDir.path;

    from srcDir
	// from fileTree('build/intermediates/classes/release')

    def lib = fileTree("libs")//读取libs中的文件
    def libTree;
    lib.each {
        file ->
            if (libTree == null) {
                libTree = zipTree(file)
            } else {
                libTree.plus(zipTree(file))
            }
    }

    if(libTree!=null){
        from libTree
    }

    appendix 'http'//后缀
    baseName "jl"//base 名称
    extension "jar"//拓展名

    exclude('android')
    exclude('**/R.class')
    exclude('**/BuildConfig.class')
}

buildJar.doLast{
	  def tree = fileTree("out")
	tree.each {
	      zipTree(it).each {
	          println it.name
	      }
	}
}

//通过上面的例子，理解其中的原理，我们可以把这个原理应用多模块的Jar打包。

task makeJar(type: Jar) {
    destinationDir = file("out")//通过project的文件接口新建一个文件
    appendix 'commom'//后缀
    baseName "jl"//base 名称
    extension "jar"//拓展名

    def modules = []
    project.subprojects {
        p ->
            if (p.name == 'jl_audio' || p.name == 'jl_bluetooth_ac692x'
                    || p.name == 'jl_http') {
                String taskName = p.name + ":assembleDebug";
                modules.add(taskName);

                from p.name + File.separator + "build/intermediates/classes/debug"
                file("libs").eachFile {
                    from zipTree(it)
                }

            }
    }

    setDependsOn(modules);

    exclude('android')
    exclude('**/R.class')
    exclude('**/R$**.class')
    exclude('**/BuildConfig.class')
}

makeJar.doLast {
    println projectDir
    println "---makeJar---"
}


```

 
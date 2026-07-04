https://baomidou.com/

在快速入门里面:

1:引入依赖

2:继承BaseMapper接口

如何知道有哪些字段?

通过扫描实体类,并且基于反射获取实体类信息作为数据表信息

![image-20260304095952647](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304095952647.png)

###### **约定大于配置**

自定义配置

![image-20260304101258467](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304101258467.png)

@TableName 类名与表名不一致

@TableId 主键名和类中定义的主键名不一致

![image-20260411113141015](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260411113141015.png)

@TableField 成员变量名字和数据库字段名不一致

使用is开头,且是布尔类型(会把is去掉)

assign_id 插入数据的

关键字冲突

不是数据库字段的,标注exist=false即可

![image-20260411151018124](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260411151018124.png)

成员变量如果是数据库的关键字,则需要使用转义字符来表示

转义字符:   ``

![image-20260304101821284](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304101821284.png)

```
@TableId(value = "id", type = IdType.AUTO)
private Integer id;
```

这里你设置了自增长你的数据库表本身也就要自增长

如果这里了没指定type默认就是雪花算法

```
IdType.ASSIGN_ID
```

-AUTO SAAIGN_ID INPUT

![image-20260304103528806](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304103528806.png)

 

自定义配置

在application.yaml文件中



条件构造器:

Wrapper(条件构造器)



Lambda QueryWrapper

尽量使用Lambda的query Wrapper和 update Wrapper,避免硬编码(写死)



![image-20260304142344451](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304142344451.png)

![image-20260304152402243](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304152402243.png)



自定义SQL

MyBatisPlus更加适合去编写where条件的语句的构建

![image-20260305094006331](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305094006331.png)

![image-20260411200400833](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260411200400833.png)

业务层,DAO层

这是固定字段值,只需在使用动态SQL的时候在末尾加上此代码即可

```
${ew.customSqlSegment}
```

batch 在性能上会好一点(数据量非常大的情况下)

batch 批量提交SQL语句

普通的则是使用where id in

查询的话

查询单个使用get

查询多个使用list

![image-20260305095733783](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305095733783.png)



page 分页查询

复杂条件的查询使用LambdaQuery()

或者LambdaUpdate()



![image-20260305101650793](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305101650793.png)

继承关系如上图

![image-20260305104609095](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305104609095.png)

1.自定义Service接口继承IService接口

2.自定义Service实现类,实现自定义接口并且继承ServiceImpl类



![image-20260305105409829](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305105409829.png)

筛选,以及指定,这个要注意区分,使用什么方式来接受参数处理请求

一般实际的场景中往往只会使用post 和 get (安全性)



复杂条件查询更新使用:

IService 的 Lambda查询

```
lambdaQuery()
```

lambdaQuery()

​	.like(name!=null,User::getName,name)

​	.list(); 

lambdaUpdate()

​	***.update()***;

悲观锁

乐观锁



IService 的批量新增的功能



批处理

```
// 每次插入1000条数据，分批插入10万条数据
List<User> list = new ArrayList<>(1000);
long b = System.currentTimeMillis();
for (int i = 0; i < 100000; i++) {
    list.add(buildUsers(i));
    if (list.size() == 1000) {
        userService.saveBatch(list);
        list.clear();
    }
}
long e = System.currentTimeMillis();
log.info("批量插入10万条数据耗时: {}ms", e - b);
```

saveBatch()

在配置文件的数据库配置后面添加

```
rewriteBatchedStatements=true
```

就可以实现新能最佳

![image-20260412160017310](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260412160017310.png)



MybatisPlus 扩展功能

使用MybatisPlus扩展

代码生成



静态工具

传递.class,使用反射得到实体类的相关信息

DB 

如果出现service相互调用可以使用DB静态工具



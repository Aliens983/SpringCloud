https://baomidou.com/

在快速入门里面:

1:引入依赖

2:继承BaseMapper接口

![image-20260304095952647](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304095952647.png)

自定义配置

![image-20260304101258467](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304101258467.png)

assign_id 插入数据的

关键字冲突

转义字符: ``

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

AUTO SAAIGN_ID INPUT

![image-20260304103528806](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304103528806.png)

条件构造器:

![image-20260304142344451](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304142344451.png)

![image-20260304152402243](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260304152402243.png)

自定义SQL

![image-20260305094006331](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305094006331.png)

```
${ew.customSqlSegment}
```

batch 在性能上会好一点

batch 批量提交SQL语句

普通的则是使用where id in

![image-20260305095733783](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305095733783.png)

![image-20260305101650793](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305101650793.png)

![image-20260305104609095](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305104609095.png)

![image-20260305105409829](C:\Users\25516\AppData\Roaming\Typora\typora-user-images\image-20260305105409829.png)

IService 的 Lambda查询

```
lambdaQuery
```

悲观锁

乐观锁

IService 的批量新增的功能

批处理

在配置文件的数据库配置后面添加

```
rewriteBatchedStatements=true
```


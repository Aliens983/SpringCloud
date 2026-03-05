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

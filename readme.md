## 计算服务使用方式

#### 传入参数示例

```
{
    "url": "http://www.a.b/{Offset(1-3,1)}",
    "body": {
      "a":"{Offset(1-2,1)}"
    }
}
```

#### 返回结果示例

```
{
  "code": 0,
  "success": true,
  "message": "成功",
  "data": [
    {
      "url": "http://www.a.b/1",
      "body": {
        "a": "1"
      }
    },
    {
      "url": "http://www.a.b/1",
      "body": {
        "a": "2"
      }
    },
    {
      "url": "http://www.a.b/2",
      "body": {
        "a": "1"
      }
    },
    {
      "url": "http://www.a.b/2",
      "body": {
        "a": "2"
      }
    },
    {
      "url": "http://www.a.b/3",
      "body": {
        "a": "1"
      }
    },
    {
      "url": "http://www.a.b/3",
      "body": {
        "a": "2"
      }
    }
  ]
}
```

#### 支持以下几种公式

|公式名称|表达式|示例及含义|备注|
|---|---|---|---|
|Offset|{Offset(start-end,step,prefix,firstLinkPrefix)}<br>{Offset(start-end,step,prefix)}<br>{Offset(start-end,step)}<br>{Offset(start-end)}&nbsp;&nbsp;step默认等于1|{Offset(1-5,1,\_,N)}&nbsp;&nbsp;表示范围为1到5，步长为1，前缀为\_,第一个不加前缀|---|
|Or|{Or(a&#124;b&#124;c)}|{Or(html&#124;pdf)}&nbsp;&nbsp;表示值为html或者pdf|---|
|Date|{Date(operation,value,format)}<br>{Date(format)}<br>{Date()}&nbsp;&nbsp;默认的format为yyyyMMdd|{Date(AddDays,-1,yyy-MM-dd)}&nbsp;&nbsp;表示前一天的日期，格式为yyyy-MM-dd|---|
|Random|{Random(min,max)}<br>{Random(max)}&nbsp;&nbsp;表示0到max的随机数<br>{Random()}&nbsp;&nbsp;表示0到Integer.MAX_VALUE的随机数|{Random(1,10)}&nbsp;&nbsp;表示1到10的随机数|---|

## 打包

```
mvn clean package -Dmaven.test.skip=true
```

## 部署：linux

```
nohup java -jar ****.jar --spring.profiles.active=dev &
```

## 部署 ：docker-compose

```
version: '3'
services:
        blog:
                container_name: blog
                restart: always
                image: java:8
                ports:
                        - 8080:8080
                volumes:
                        - /usr/local/docker/java/blog/blog-0.0.1-SNAPSHOT.jar:/blog-0.0.1-SNAPSHOT.jar
                        - ./logFiles:/logFiles
                environment:
                        - TZ='Asia/Shanghai'
                entrypoint: java -jar blog-0.0.1-SNAPSHOT.jar
```
# logger
## 基于aspectjweaver+slf4j实现的日志格式化输出和记录插件
## 配合自定义注解用于记录用户的操作日志和项目异常日志
## 可单独用于输出或将日志持久化至redis
## 具体使用方法可参考demo
## 日志输出格式请自行修改
	com.tstkj.logger.entry.ExceptionEntry.toString()
    com.tstkj.logger.entry.OperaEntry.toString()
由于对日志框架不够熟悉,插件可能存在未知问题
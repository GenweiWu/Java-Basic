## 简单学习下freemarker的用法

#### 各种数据类型的测试
- 对boolean类型处理，无法自动转换，[参见资料](http://freemarker.org/docs/ref_builtins_boolean.html)
- 对于日期类型，[需要转换](http://freemarker.org/docs/ref_builtins_date.html)。不过我倾向于传递的时候就已经转换成字符串类型了

#### 如何处理list或者map类型


#### 针对jackson传值的问题和解决
- 对boolean类型处理，不用转换了
- 对于日期类型，先转换为字符串类型
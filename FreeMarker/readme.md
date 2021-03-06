## 简单学习下freemarker的用法

#### 各种数据类型的测试
- 对boolean类型处理，无法自动转换，[参见资料](http://freemarker.org/docs/ref_builtins_boolean.html)
- 对于日期类型，[需要转换](http://freemarker.org/docs/ref_builtins_date.html)。不过我倾向于传递的时候就已经转换成字符串类型了

#### 如何处理list或者map类型
- [list用法](http://freemarker.org/docs/ref_directive_list.html)
- map[低版本支持不太好](http://freemarker.org/docs/ref_directive_list.html#ref.directive.list),推荐写法：
 ```ftl
 <#list hash as key, value>
     Part repeated for each key-value pair
 </#list>
```

#### 针对jackson传值的问题和解决
- 对boolean类型处理，不用转换了
- 对于日期类型，先转换为字符串类型
- 对于字符串类型，为了避免111被展示为"111"的问题，需要```.asText()```方法
- 对于list类型，对应ArrayNode，解决方法主要有：
  - 对于迭代通过 ```arrayNode.iterator()```解决，此时对于展示后111展示成"111"的问题，通过`asText()`方法解决
  - 或者转换为List<ObjectNode>来解决，不会出现"111"的问题
- 对于map中属性，也要解决类似"111"的问题  
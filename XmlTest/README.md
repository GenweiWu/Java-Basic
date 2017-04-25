### 问题：
下面的xml,要读取最后b下面的x节点，并且找到内容为555的节点，然后修改它的下一个兄弟节点的内容为bingo。
```xml
<root>
    <b>
        <row id="row1">
            <x id="id1">444</x>
            <x id="id2">555</x>
            <x id="id3">666</x>
        </row>
    </b>
    <b>
        <row id="row2">
            <x id="id4">444</x>
            <x id="id5">555</x>
            <x id="id6">666</x>
        </row>
    </b>
</root>
```

### 技术点
1. 忽略dtd校验
2. 选择当前节点下的符合要求节点  
    `.`用来标识当前节点,例如xpath如下：`.//row[x='555']`
3. 获取下一个兄弟节点   
    `"./following-sibling::*[1]"`

---

<div>
<#list arrayNode1 as item>
    <p>${item.asText()}</p>
</#list>
</div>

<div>
<#list arrayNode2 as item>
    <p>${item}</p>
</#list>
</div>

<div>
    <p>${myHash.name.asText()}</p>
    <p>${myHash.age.asText()}</p>
</div>
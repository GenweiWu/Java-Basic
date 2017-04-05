<div>
<#list sequence as item>
    <p>${item}</p>
</#list>
</div>

<div>
<#list myHash?keys as k>
    <p>${k}</p>
</#list>
<#list myHash?values as v>
    <p>${v}</p>
</#list>
</div>
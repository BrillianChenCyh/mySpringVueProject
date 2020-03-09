abc
===
    select * from user where 1 = 1
    @if(!isEmpty(name)&&name != ''){
        and name = #name#
    @}

selectArr
===
    select * from user where 1 = 1
    @if(!isEmpty(a)){
        and id in (#join(a)#)
    @}

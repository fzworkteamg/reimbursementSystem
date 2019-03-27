//获取公司填充下拉列表
function selectCompany(){
    var companys;
        $.ajax({
            url: '/company/selectCompany',
            type: 'post',
            dataType: 'json',
            async : false,
            contentType: "application/json",
            success: function (result) {
                companys = result.data
            }
        });
         return companys;
}

//根据公司名查询部门
function selectDepByCompany(company){
    var deps;
    $.ajax({
        url:'/dep/selectDepByCompany',
        type:'post',
        dataType:'json',
        data:{
            "company":company
        },
        async:false,
        success:function (result) {
            deps = result.data;
        }
    });
    return deps;
}
//查询发票状态
function selectInvoiceStatus() {
    var invoiceStatus;
    $.ajax({
        url:'invoice/selectInvoice',
        type:'post',
        dataType:'json',
        async : false,
        contentType: "application/json",
        success: function (result) {
            invoiceStatus = result.data
        }

    });
    return invoiceStatus;
}
//查询合同状态
function selectContractStatus() {
    var contractStatus;
    $.ajax({
        url:'invoice/selectInvoice',
        type:'post',
        dataType:'json',
        async : false,
        contentType: "application/json",
        success: function (result) {
            contractStatus = result.data
        }
    });
    return contractStatus;
}
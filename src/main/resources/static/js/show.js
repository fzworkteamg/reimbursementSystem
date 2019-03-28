$(document).ready(function () {
    //获取公司填充下拉列表
    var companys = selectCompany();
    romance(companys,"selectCompany")

    //填充部门
    $("select[name='company']").change(function () {
        var company = $("#selectCompany").val();
        if(company!="--选择公司--"){
            var deps = selectDepByCompany(company);
            cleanSelect("selectDep");
            romance(deps,"selectDep");
        }
    })
    //填充发票
    var invoice = selectInvoiceStatus();
    romance(invoice,"invoice");

    //填充合同
    var contract = selectInvoiceStatus();
    romance(contract,"contract");

    //填充类别

    var formData = new FormData($("#selectForm")[0]);//此处id为form表单的id
    $.ajax({
        url: '/bill/selectBill',
        type: 'post',
        // dataType: 'json',
        contentType: false,
        data: formData,
        processData: false,
        async: false,
        cache: false,
        success: function (result) {
        },
        fail: function () {
            window.alert("系统错误，请联系管理员");
        }
    })

})
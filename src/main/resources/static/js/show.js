var formData;

$(document).ready(function () {
    layui.use('layer', function () {
        var layer = layui.layer;
    });
    layui.use('jquery'), function () {
        $ = layui.jquery;
    }
    layui.use('form', function () {
        form = layui.form;
        form.render();
    })
    layui.use('table', function () {
        table = layui.table;
        remoceBillTable();
    });
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

    formData = new FormData($("#selectForm")[0]);//此处id为form表单的id
    // $.ajax({
    //     url: '/bill/selectBill',
    //     type: 'post',
    //     // dataType: 'json',
    //     contentType: false,
    //     data: formData,
    //     processData: false,
    //     async: false,
    //     cache: false,
    //     success: function (result) {
    //         console.log(result.data);
    //     },
    //     fail: function () {
    //         window.alert("系统错误，请联系管理员");
    //     }
    // })


})

function remoceBillTable() {
    table.render({
        // elem: '#billTable',
        // page: true,
        // url: "/reiSubject/selectDepContentCommentByCompanyDep?company='河北分公司'&dep='营销部'",
        elem: '#billTable',
        where: {
            company:$('#selectCompany').val(),
            dep:$('#selectDep').val(),
            attribute:$('#attribute').val(),
            type:$('#type').val(),
            invoice:$('#invoice').val(),
            contract:$('#contract').val(),
            billId:$('#billId').val(),
            chargePerson:$('#chargePerson').val(),
            registrantPerson:$('#registrantPerson').val(),
            summary:$('#summary').val(),
            subject:$('#subject').val(),
            produceData:$('#produceData').val(),
            registrantDate:$('#registrantDate').val(),
            endDate:$('#endDate').val(),
            amountLow:$('#amountLow').val(),
            amoutHigh:$('#amoutHigh').val(),
        },
        url: '/bill/selectBill',
        method: 'post',
        cols: [[
            {field:'xuhao',width:50,title:'序号',templet:'#xuhao'},
            {field: 'rei_subject_dep_name', width: 100, title: '部门'},
            {field: 'rei_subject_content', width: 150, title: '科目'},
            {field: 'rei_subject_comment', width: 280, title: '备注'},
            // {fixed: 'right', width:80, align:'center', toolbar: '#barDemo',title:'确定'}
        ]],
        done: function (res) {
            console.log(res);
        }
    })
}
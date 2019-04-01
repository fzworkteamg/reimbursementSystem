var formData;
var billTable;
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
    romance(companys, "selectCompany")

    //填充部门
    $("select[name='company']").change(function () {
        var company = $("#selectCompany").val();
        if (company != "--选择公司--") {
            var deps = selectDepByCompany(company);
            cleanSelect("selectDep");
            romance(deps, "selectDep");
        }
    })
    //填充发票
    var invoice = selectInvoiceStatus();
    romance(invoice, "invoice");

    //填充合同
    var contract = selectInvoiceStatus();
    romance(contract, "contract");

    //填充类别

    // formData = new FormData($("#selectForm")[0]);//此处id为form表单的id


})

function remoceBillTable() {
    billTable = table.render({
        id: 'billTable',
        elem: '#billTable',
        height: '470', //高度最大化减去差值
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'print', 'exports'],
        where: {
            company: $('#selectCompany').val(),
            dep: $('#selectDep').val(),
            attribute: $('#attribute').val(),
            type: $('#type').val(),
            invoice: $('#invoice').val(),
            contract: $('#contract').val(),
            billId: $('#billId').val(),
            chargePerson: $('#chargePerson').val(),
            registrantPerson: $('#registrantPerson').val(),
            summary: $('#summary').val(),
            subject: $('#subject').val(),
            produceData: $('#produceData').val(),
            registrantDate: $('#registrantDate').val(),
            endDate: $('#endDate').val(),
            amountLow: $('#amountLow').val(),
            amoutHigh: $('#amoutHigh').val(),
        },
        url: '/bill/selectBill',
        method: 'post',
        page: true,
        limit: 10,
        cols: [[
            {field: 'xuhao', width: 80, title: '序号', templet: '#xuhao'},
            {field: 'billId', width: 180, title: '单号'},
            {field: 'billAttribute', width: 80, title: '属性'},
            {field: 'billType', width: 80, title: '类型'},
            {field: 'billReimbursementDep', width: 100, title: '报销部门'},
            {field: 'billSubject', width: 100, title: '科目'},
            {field: 'billBelongCompany', width: 130, title: '业务所属公司'},
            {field: 'billReimbursementPerson', width: 80, title: '报销人'},
            {field: 'billSummary', width: 150, title: '摘要'},
            {field: 'billAmount', width: 100, title: '金额'},
            {field: 'billContractStatusName', width: 120, title: '合同'},
            {field: 'billInvoiceStatusName', width: 120, title: '发票'},
            {field: 'billInvoiceAmount', width: 100, title: '发票金额'},
            {field: 'billReimbursementPersonConfirm', width: 100, title: '报销人确认'},
            {field: 'billChargePerson', width: 80, title: '经办人'},
            {field: 'billRegistrantPerson', width: 80, title: '登记人'},
            {field: 'billRegistrantDate', width: 120, title: '登记日期'},
            {field: 'billProduceDate', width: 120, title: '产生日期'},
            {field: 'billEndDate', width: 120, title: '结束日期'},
            // {field: 'billEndDate', width: 280, title: '流程执行情况'},
            // {fixed: 'right', width:80, align:'center', toolbar: '#barDemo',title:'确定'}
        ]],
        done: function (res) {
            console.log(res);
        }
    })
}


function reloadBillTable() {
    billTable.reload({
        where: { //设定异步数据接口的额外参数，任意设
            company: $('#selectCompany').val(),
            dep: $('#selectDep').val(),
            attribute: $('#attribute').val(),
            type: $('#type').val(),
            invoice: $('#invoice').val(),
            contract: $('#contract').val(),
            billId: $('#billId').val(),
            chargePerson: $('#chargePerson').val(),
            registrantPerson: $('#registrantPerson').val(),
            summary: $('#summary').val(),
            subject: $('#subject').val(),
            produceData: $('#produceData').val(),
            registrantDate: $('#registrantDate').val(),
            endDate: $('#endDate').val(),
            amountLow: $('#amountLow').val(),
            amoutHigh: $('#amoutHigh').val(),
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}
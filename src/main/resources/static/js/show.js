var formData;
var billTable;
var processTip;
$(document).ready(function () {
    $("#selectCompany").attr("readOnly", true);
    var deps = selectDepByCompany($("#selectCompany").val());
    romance(deps, 'selectDep');
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
        table.on('row(billTable)', function (obj) {

        });

        table.on('rowDouble(billTable)', function (obj) {
            openBillDetail(obj.data);
        });
        //
        table.on('tool(billTable)', function (obj) {
            if (obj.event === 'confirmReceipt') {
                var index = layer.confirm('您确定已到账吗', {
                    btn: ['确定', '取消']
                }, function () {
                    layer.close(index);
                    $.ajax({
                        url: '/bill/updateBillReimbursementComfirmByBillId',
                        type: 'post',
                        async: false,
                        data: {
                            billId: obj.data.billId
                        },
                        success: function (data) {
                            if (data.status == 0) {
                                parent.layer.msg('确认到账成功', {
                                    icon: 1,
                                    offset: '100px',
                                    time: 1000
                                });
                                reimbursed();
                            } else {
                                parent.layer.msg('确认到账失败，请联系系统管理员', {
                                    icon: 3,
                                    offset: '100px',
                                    time: 1000
                                });
                            }
                        }
                    })
                })
            }
        })
        //顶部工具栏
        table.on('toolbar(billTable)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            var date = new Date();
            switch (obj.event) {
                case 'thisMonth'://查询本月的账单
                   thisMonth();
                    break;
                case 'done'://本人已审核账单的查询
                   done();
                    break;
                case 'toAudit'://本人待审核账单的查询
                    toAudit();
                    break;
                case 'writeAndPast'://本人登记被驳回
                    writeAndPast();
                    break;
                case 'reimbursed'://本人报销
                    reimbursed();
                    break;
            }
            ;
        });
    });
    //获取公司填充下拉列表
    // var companys = selectCompany();
    // romance(companys, "selectCompany")

    //填充部门
    // $("select[name='billCompany']").change(function () {
    //     var company = $("#selectCompany").val();
    //     if (company != "--选择公司--") {
    //         var deps = selectDepByCompany(company);
    //         cleanSelect("selectDep");
    //         romance(deps, "selectDep");
    //     }
    // })
    //填充发票
    var invoice = selectInvoiceStatus();
    romance(invoice, "invoice");

    //填充合同
    var contract = selectInvoiceStatus();
    romance(contract, "contract");

    //填充类别

    // formData = new FormData($("#selectForm")[0]);//此处id为form表单的id


})

//表格初始渲染
function remoceBillTable() {
    billTable = table.render({
        id: 'billTable',
        elem: '#billTable',
        height: '570', //高度最大化减去差值
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'print', 'exports'],
        where: {
            billCompany: $('#selectCompany').val(),
            billDep: $('#selectDep').val(),
            billAttribute: $('#attribute').val(),
            billType: $('#type').val(),
            billInvoiceStatusName: $('#invoice').val(),
            billContractStatusName: $('#contract').val(),
            billId: $('#billId').val(),
            billChargePerson: $('#chargePerson').val(),
            billRegistrantPerson: $('#registrantPerson').val(),
            billSummary: $('#summary').val(),
            billSubject: $('#subject').val(),
            billProduceDate: $('#produceData').val(),
            billRegistrantDate: $('#registrantDate').val(),
            billEndDate: $('#endDate').val(),
            amountLow: $('#amountLow').val(),
            amoutHigh: $('#amoutHigh').val(),
        },
        url: '/bill/selectBill',
        method: 'post',
        page: true,
        limit: 10,
        cols: [[
            {field: 'xuhao', width: 60, title: '序号', templet: '#xuhao', fixed: 'left'},
            {field: 'billId', width: 170, title: '单号', fixed: 'left'},
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
            // {fixed: 'right', width:80, align:'center', toolbar: '#barDemo'}
        ]],
        done: function (res) {
            // console.log(res);
        }
    })
}

//查询按钮刷新表格
function reloadBillTable() {
    billTable.reload({
        url: '/bill/selectBill',
        where: { //设定异步数据接口的额外参数，任意设
            billCompany: $('#selectCompany').val(),
            billDep: $('#selectDep').val(),
            billAttribute: $('#attribute').val(),
            billType: $('#type').val(),
            billInvoiceStatusName: $('#invoice').val(),
            billContractStatusName: $('#contract').val(),
            billId: $('#billId').val(),
            billChargePerson: $('#chargePerson').val(),
            billRegistrantPerson: $('#registrantPerson').val(),
            billSummary: $('#summary').val(),
            billSubject: $('#subject').val(),
            billProduceDate: $('#produceData').val(),
            billRegistrantDate: $('#registrantDate').val(),
            billEndDate: $('#endDate').val(),
            amountLow: $('#amountLow').val(),
            amoutHigh: $('#amoutHigh').val(),
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

//弹出账单详情页面
function openBillDetail(data) {
    layer.close(processTip);
    data = JSON.stringify(data);
    layer.open({
        type: 2,
        title: '账单详情',
        maxmin: true,
        offset: '1px',
        shadeClose: true, //点击遮罩关闭层
        area: ['950px', '600px'],
        content: '/view/toBillDetail?data=' + encodeURIComponent(data, 'utf-8')//转换编码格式
    });
}

//弹出当前账单流程页面
function openBillProcess(id) {
    $.ajax({
        url: '/process/selectProcessContentByBillId',
        data: {
            billId: id
        },
        method: 'post',
        success: function (result) {
            processTip = layer.msg(result.data, {
                time: 6000,
                offset: '126px',
            })
        }
    })
}


function reimbursed() {
    billTable.reload({
        url: '/bill/reimbursedBill',
        cols: [[
            {field: 'xuhao', width: 60, title: '序号', templet: '#xuhao', fixed: 'left'},
            {field: 'billId', width: 170, title: '单号', fixed: 'left'},
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
            {field: 'reimbursementStatus', width: 120, fixed: 'right', templet: '#judgment'}
        ]],
        page: {
            curr: 1
        }
    });
}

function writeAndPast() {
    billTable.reload({
        url: '/bill/selectBillWriteAndPast',
        page: {
            curr: 1
        }
    });
}

function done() {
    billTable.reload({
        url: '/bill/selectBillAudited',
        page: {
            curr: 1
        }
    });
}

function toAudit() {
    billTable.reload({
        url: '/bill/selectBillWaitAudit',
        page: {
            curr: 1
        }
    });
}

function thisMonth() {
    var date = new Date();
    billTable.reload({
        url: '/bill/selectBillByMonth',
        where: { //设定异步数据接口的额外参数，任意设
            date: date.getFullYear() + "-" + (date.getMonth() + 1)
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

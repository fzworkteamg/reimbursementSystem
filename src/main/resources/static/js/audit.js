// layui.use('element', function () {
//     var element = layui.element;
// });
var layer;
var object = {};
layui.use('layer', function () {
     layer = layui.layer;
});
layui.use('form', function () {
    form = layui.form;
    form.render();
    form.on('submit(auditPass)', function (data) {
        var index = layer.confirm('您确定要通过审核吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(index);
            $.ajax({
                url: '/bill/auditBill',
                data: {
                    billId: $('#bill_id').val(),
                    audit_summary: $('#audit_summary').val(),
                    selectContractStatus: $('#selectContractStatus').val(),
                    selectInvoiceStatus: $('#selectInvoiceStatus').val()
                },
                async: false,
                method: 'post',
                success: function (result) {
                    if (result.status == 0) {
                        parent.layer.msg('审核已通过', {
                            icon: 1,
                            offset: '300px',
                            time: 1000
                        });
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);//关闭当前页
                    } else {
                        parent.layer.msg('审核失败，请联系系统管理员', {
                            icon: 3,
                            offset: '300px',
                            time: 1000
                        });
                    }
                }
            })
        })
        return false;
    })
    form.on('submit(auditPast)', function (data) {
        //询问框
        var index = layer.confirm('您确定要驳回吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(index);
            $.ajax({
                url: '/bill/rejectBill',
                data: {
                    billId: $('#bill_id').val(),
                    audit_summary: $('#audit_summary').val()
                },
                async: false,
                method: 'post',
                success: function (result) {
                    if (result.status == 0) {
                        parent.layer.msg('已驳回', {
                            icon: 2,
                            offset: '300px',
                            time: 1000
                        });
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);//关闭当前页
                    } else {
                        parent.layer.msg('驳回失败，请联系系统管理员', {
                            icon: 3,
                            offset: '300px',
                            time: 1000
                        });
                    }
                }
            })
        })
        return false;
    })
})

$(document).ready(function () {
    buildProcess();
    isAudit();

    //填充合同状态
    var contractStatus = selectContractStatus();
    romance(contractStatus, "selectContractStatus");

    //填充发票状态
    var invoiceStatus = selectInvoiceStatus();
    romance(invoiceStatus, "selectInvoiceStatus")

})

// function isAudit() {
//     var msg = [[${session.status}]];
//     if (msg == 1) {
//         $("#auditPass").hide();
//         $("#auditPast").hide();
//     } else {
//         $("#tip").hide();
//     }
// }

// //动态生成流程图
function buildProcess() {
    $.ajax({
        url: '/processStatus/selectNameStateOptionById',
        data: {
            billId: $('#bill_id').val()
        },
        type: 'post',
        dataType:'json',
        success: function (result) {
            var list = result;
            $("#process").before("<font style='margin-left: -100px'>（单击流程查看审核意见）流程:</font>");
            for (var i = 0; i < list.length; i++) {
                var processStatus = list[i];
                if (processStatus.process_status_state == "待审核") {
                    var content = "<font style='color: orange;border-style: solid;cursor: pointer' id='"+i+"'>"
                        + processStatus.process_status_process_name + " " +" "+
                        processStatus.process_status_state + " </font>&nbsp;&nbsp;"
                } else if ((processStatus.process_status_state == "已审核")) {
                    object[i] = processStatus.process_status_opinion;
                    var content = "<font style='color: green;border-style: solid;cursor: pointer' id='"+i+"' onclick='summaryTip("+i+")'>" + processStatus.process_status_process_name +
                        " " +" "+processStatus.process_status_auditor+" "+ processStatus.process_status_state + "   </font>&nbsp;&nbsp;"
                } else if (processStatus.process_status_state == "驳回") {
                    object[i] = processStatus.process_status_opinion;
                    var content = "<font style='color: red;border-style: solid;cursor: pointer' id='"+i+"' onclick='summaryTip("+i+")'>" + processStatus.process_status_process_name +
                        " " +" "+processStatus.process_status_auditor+" "+ processStatus.process_status_state + "   </font>&nbsp;&nbsp;"
                } else if (processStatus.process_status_state == "") {
                    var content = "<font style='border-style: solid;cursor: pointer' id='"+i+"'>" + processStatus.process_status_process_name +
                        " " + processStatus.process_status_state + "   </font>&nbsp;&nbsp;"
                }
                $("#process").before(content);
            }
        }
    })
}
window.summaryTip = function(i) {
    //小tips
    let temp = object[i];
    layer.tips(temp, "#"+i, {
        tips: [3, '#3595CC'],
        time: 5000
    });
}

//弹出明细页面
function showDetail() {
    var detailIndex = layer.open({
        type: 2,
        title: '账单明细详情',
        maxmin: true,
        offset: '1px',
        shadeClose: true, //点击遮罩关闭层
        area: ['400px', '450px'],
        content: '/view/toShowBillDetail'
    })
}
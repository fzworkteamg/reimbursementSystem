var layer;
layui.use('layer', function () {
    layer = layui.layer;
});
layer.use('form', function () {
    form = layui.form;
    form.render();
    form.on('submit(auditPass)', function (data) {
        var formData = new FormData($("#selectForm")[0]);
        var index = layer.confirm('您确定要修改吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(index);
            $.ajax({
                url: '/bill/updateBill',
                data: formData,
                async: false,
                method: 'post',
                success: function (result) {
                    if (result.status == 0) {
                        parent.layer.msg('已修改', {
                            icon: 1,
                            offset: '300px',
                            time: 1000
                        });
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);//关闭当前页
                    } else {
                        parent.layer.msg('修改失败，请联系系统管理员', {
                            icon: 3,
                            offset: '300px',
                            time: 1000
                        });
                    }
                }
            })
        })
        return false;
    });
    $("#delBill").click(function () {
        var index = layer.confirm('你确定要删除此账单吗', {
            btn: ['确定', '取消']
        }, function () {
            layer.close(index);
            $.ajax({
                url: '/bill/delBill',
                data: {
                    billId: $('#bill_id').val()
                },
                async: false,
                method: 'post',
                success: function (result) {
                    if (result.status == 0) {
                        parent.layer.msg('账单已删除', {
                            icon: 1,
                            offset: '300px',
                            time: 1000
                        });
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);//关闭当前页
                    } else {
                        parent.layer.msg('删除失败，请联系系统管理员', {
                            icon: 3,
                            offset: '300px',
                            time: 1000
                        });
                    }
                }
            })
        })
    })
})

$(document).ready(function () {
    buildProcess();

    //填充合同状态
    var contractStatus = selectContractStatus();
    romance(contractStatus, "selectContractStatus");

    //填充发票状态
    var invoiceStatus = selectInvoiceStatus();
    romance(invoiceStatus, "selectInvoiceStatus")

})

//动态生成流程图
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
                        + processStatus.process_status_process_name + " " +" "+processStatus.process_status_auditor+" "+
                        processStatus.process_status_state + " </font>&nbsp;&nbsp;"
                } else if ((processStatus.process_status_state == "已审核")) {
                    var content = "<font style='color: green;border-style: solid;cursor: pointer' id='"+i+"' onclick='summaryTip('"+processStatus.process_status_opinion+"',"+i+")'>" + processStatus.process_status_process_name +
                        " " +" "+processStatus.process_status_auditor+" "+ processStatus.process_status_state + "   </font>&nbsp;&nbsp;"
                } else if (processStatus.process_status_state == "驳回") {
                    var content = "<font style='color: red;border-style: solid;cursor: pointer' id='"+i+"' onclick='summaryTip('"+processStatus.process_status_opinion+"',"+i+")'>" + processStatus.process_status_process_name +
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

function summaryTip(content,i) {
    //小tips
    layer.tips(content, "#"+i, {
        tips: [3, '#3595CC'],
        time: 5000
    });
}
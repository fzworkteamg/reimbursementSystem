$(document).ready(function () {
    //填充公司下拉框
    // var companys = selectCompany();
    // romance(companys,"selectCompany")

    //根据公司填充部门
    // var company = $("#company").val()
    // var deps = selectDepByCompany(company);
    // romance(deps,"selectDep")


    //填充发票状态
    var invoiceStatus = selectInvoiceStatus();
    romance(invoiceStatus, "selectInvoiceStatus")


    //填充合同状态
    var contractStatus = selectContractStatus();
    romance(contractStatus, "selectContractStatus");

    $('#bill_id_suff').val(billIdByTime());
    $("#bill_id_suff").attr("readOnly", true);

    $("#reiPerson").attr("readOnly", true);
    $("#chargePerson").attr("readOnly", true);
    // $("#subject").attr("readOnly",true);
})

var subjectIndex;
var reiPersonIndex;
var chargePersonIndex;
var form;
//载入table模块
layui.use('table', function () {
    table = layui.table;
    table.on('rowDouble(subjectTable)', function (obj) {
        // console.log(obj.tr) //得到当前行元素对象
        // console.log(obj.data) //得到当前行数据
        //obj.del(); //删除当前行
        //obj.update(fields) //修改当前行数据
        $("#subject").attr("readOnly", false);
        $("#subject").val(obj.data.rei_subject_content);
        layer.close(subjectIndex);
    });
});
// 载入form模块
layui.use('form', function () {
    form = layui.form;
    //选择公司时填充部门与员工下拉框
    form.on('select(childSelectCompany)', function (data) {
        var deps = selectDepByCompany(data.value);
        cleanSelect("childSelectDep");
        romance(deps, "childSelectDep");
        form.render();
        var persons = selectPersonsByCompanyAndDep($("#childSelectCompany").val(), $("#childSelectDep").val());
        cleanSelect("childSelectPerson")
        romance(persons, "childSelectPerson");
        form.render();
    })
    //选择部门时改变填充员工下拉框
    form.on('select(childSelectDep)', function (data) {
        var persons = selectPersonsByCompanyAndDep($("#childSelectCompany").val(), $("#childSelectDep").val());
        cleanSelect("childSelectPerson")
        romance(persons, "childSelectPerson");
        form.render();
    })
    //选择科目页面，选择公司时，填充部门
    form.on('select(selectSubjectCompany)', function (data) {
        var deps = selectDepByCompany(data.value);
        cleanSelect("selectSubjectDep");
        romance(deps, "selectSubjectDep");
        form.render();
    })
    //选择科目页面，选择部门时填充科目表格
    form.on('select(selectSubjectDep)', function (data) {
        $("#subjectTable").empty();
        romanceTable();
    })
    //新增报销提交按钮
    form.on('submit(insertBill)', function (data) {
        var formData = new FormData($("#form1")[0]);
        var index = layer.confirm('您确定新增吗', {
            btn: ['确定', '取消']
        }, function () {
            layer.close(index);
            $.ajax({
                url: '/bill/insertBill',
                type: 'post',
                async: false,
                data: formData,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data.status == 0) {
                        parent.layer.msg('新增成功', {
                            icon: 1,
                            offset: '300px',
                            time: 1000
                        });
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);//关闭当前页
                    } else {
                        parent.layer.msg('新增失败，请联系系统管理员', {
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
});
//载入jquery模块
layui.use('jquery'), function () {
    $ = layui.jquery;
}
//载入layer模块
layui.use('layer', function () {
    var layer = layui.layer;
});

function openAddSubjet() {
    subjectIndex = layer.open({
        type: 1,
        area: ['600px', '600px'],
        shadeClose: true, //点击遮罩关闭
        content:
            "<div class=\"layui-row\">\n" +
            "                <form class=\"layui-form\" id=\"form3\">\n" +
            "                    <div class=\"layui-form-item\">\n" +
            "                        <label class=\"layui-form-label\">公司:</label>\n" +
            "                        <div class=\"layui-input-block\">\n" +
            "                            <select id=\"selectSubjectCompany\" lay-filter=\"selectSubjectCompany\">\n" +
            "                                <option></option>\n" +
            "                            </select>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"layui-form-item\">\n" +
            "                        <label class=\"layui-form-label\">部门:</label>\n" +
            "                        <div class=\"layui-input-block\">\n" +
            "                            <select id=\"selectSubjectDep\" lay-filter=\"selectSubjectDep\">\n" +
            "                                <option></option>\n" +
            "                            </select>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </form>\n" +
            "            </div>\n" +
            "<h4 style='color: red;'>*双击选择</h4>" +
            "<table id='subjectTable' lay-filter='subjectTable'>" +
            "            </table>"
    });

    var companys = selectCompany();
    romance(companys, "selectSubjectCompany");
    form.render();

}

function openAddReiPerson() {
    reiPersonIndex = layer.open({
        type: 1,
        area: ['600px', '600px'],
        shadeClose: true, //点击遮罩关闭
        success: function (layero, index) {
            console.log(layero, index);
            var company = $("#company").val();
            $("#companyInCompanyPane").append("<a style='' id='companyInPane' href='javascript:;'>" + company + "</a><br>");
            selectPersonsByCompanyAndDepShowInPane(company, "", 2, 0);
            $.ajax({
                url: '/dep/selectDepByCompany',
                type: 'post',
                dataType: 'json',
                data: {
                    "company": company
                },
                async: false,
                success: function (result) {
                    deps = result.data;
                    for (var i = 0; i < deps.length; i++) {
                        $("#depPane").append("<h5 name='dep' style='cursor: pointer' onclick='selectPersonsByCompanyAndDepShowInPane(\"" + company + "\",\"" + deps[i] + "\",2,1)'>" + deps[i] + "</h5><br>");
                    }
                }
            });
        },
        content:
            "<div class='layui-row'>" +
            "<div style='border-right-style: solid;float: left; width: 180px; text-align: center' id='companyPane'>" +
            "<h4 id='companyInCompanyPane'></h4>" +
            "</div> " +
            "<div style='border-right-style: solid;float: left; width: 180px; text-align: center'id='depPane'>" +
            "" +
            "</div> " +
            "<div id='staffPane' style='border-right-style: solid;float: left; width: 180px; text-align: center'>" +
            "" +
            "</div> " +
            "</div>"
        // "<div class='layui-row' style='text-align: center'>\n" +
        // "<form class=\"layui-form\" action=\"\">\n" +
        // "<div class=\"layui-form-item\">\n" +
        // "    <label class=\"layui-form-label\">选择公司：</label>\n" +
        // "    <div class=\"layui-input-block\">\n" +
        // "        <select id='childSelectCompany' name='childSelectCompany1' lay-filter='childSelectCompany' lay-verify=\"required\">\n" +
        // "<option></option>" +
        // "        </select>\n" +
        // "    </div>\n" +
        // "</div>\n" +
        // "<div class=\"layui-form-item\">\n" +
        // "    <label class=\"layui-form-label\">选择部门：</label>\n" +
        // "    <div class=\"layui-input-block\">\n" +
        // "        <select id='childSelectDep' lay-verify=\"required\" lay-filter='childSelectDep'>\n" +
        // "<option></option>" +
        // "        </select>\n" +
        // "    </div>\n" +
        // "</div>\n" +
        // "<div class=\"layui-form-item\">\n" +
        // "    <label class=\"layui-form-label\">选择员工：</label>\n" +
        // "    <div class=\"layui-input-block\">\n" +
        // "        <select name=\"childSelectPerson\" lay-verify=\"required\" lay-filter='childSelectPerson' id='childSelectPerson'>\n" +
        // "<option></option>" +
        // "        </select>\n" +
        // "    </div>\n" +
        // "</div>\n" +
        // "<button type='button' class='layui-btn' lay-filter='chooseReiPerson' onclick='chooseReiPersonAndCloseTip()' id='chooseReiPersonButton'>确定</button>" +
        // "</form>\n" +
        // "</div>"
    });
}

function openAddChargePerson() {
    chargePersonIndex = layer.open({
        type: 1,
        area: ['600px', '600px'],
        shadeClose: true, //点击遮罩关闭
        success: function (layero, index) {
            console.log(layero, index);
            var company = $("#company").val();
            $("#companyInCompanyPane").append("<a style='' id='companyInPane' href='javascript:;'>" + company + "</a><br>");
            selectPersonsByCompanyAndDepShowInPane(company, "", 3, 0);
            $.ajax({
                url: '/dep/selectDepByCompany',
                type: 'post',
                dataType: 'json',
                data: {
                    "company": company
                },
                async: false,
                success: function (result) {
                    deps = result.data;
                    for (var i = 0; i < deps.length; i++) {
                        $("#depPane").append("<h5 name='dep' style='cursor: pointer' onclick='selectPersonsByCompanyAndDepShowInPane(\"" + company + "\",\"" + deps[i] + "\",3,1)'>" + deps[i] + "</h5><br>");
                    }
                }
            });
        },
        content:
            "<div class='layui-row'>" +
            "<div style='border-right-style: solid;float: left; width: 180px; text-align: center' id='companyPane'>" +
            "<h4 id='companyInCompanyPane'></h4>" +
            "</div> " +
            "<div style='border-right-style: solid;float: left; width: 180px; text-align: center'id='depPane'>" +
            "" +
            "</div> " +
            "<div id='staffPane' style='border-right-style: solid;float: left; width: 180px; text-align: center'>" +
            "" +
            "</div> " +
            "</div>"
    });
}

function romanceTable() {
    table.render({
        elem: '#subjectTable',
        page: true,
        limit: 10,
        url: '/reiSubject/selectDepContentCommentByCompanyDep?company=' + $('#selectSubjectCompany').val() + "&dep=" + $('#selectSubjectDep').val(),
        cols: [[
            {field: 'rei_subject_dep_name', width: 100, title: '部门'},
            {field: 'rei_subject_content', width: 150, title: '科目'},
            {field: 'rei_subject_comment', width: 280, title: '备注'},
            // {fixed: 'right', width:80, align:'center', toolbar: '#barDemo',title:'确定'}
        ]],
        done: function (res) {
            // console.log(res);
        }
    })
}

//弹出层选择报销人返回
function chooseReiPersonAndCloseTip() {
    $("#reiPerson").attr("readOnly", false);
    var data = event.target.innerHTML;
    var arr = data.split("|");
    console.log(arr);
    $("#reiPerson").val(arr[0]);
    layer.close(reiPersonIndex);
    $("#reiPerson").attr("readOnly", true);
}

//弹出层选择经办人返回
function chooseChargeAndCloseTip() {
    $("#chargePerson").attr("readOnly", false);
    var staff = event.target.innerHTML;
    var temp = staff.split("|");
    console.log(temp[0]);
    $("#chargePerson").val(temp[0]);
    layer.close(chargePersonIndex);
    $("#chargePerson").attr("readOnly", true);
}

//根据时间生成单号
function billIdByTime() {
    var myDate = new Date();
    // myDate.getYear();        //获取当前年份(2位)
    // myDate.getFullYear();    //获取完整的年份(4位,1970-????)
    // myDate.getMonth();       //获取当前月份(0-11,0代表1月)
    // myDate.getDate();        //获取当前日(1-31)
    // myDate.getDay();         //获取当前星期X(0-6,0代表星期天)
    // myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)
    // myDate.getHours();       //获取当前小时数(0-23)
    // myDate.getMinutes();     //获取当前分钟数(0-59)
    // myDate.getSeconds();     //获取当前秒数(0-59)
    // myDate.getMilliseconds();    //获取当前毫秒数(0-999)
    // myDate.toLocaleDateString();     //获取当前日期
    // var mytime=myDate.toLocaleTimeString();     //获取当前时间
    // myDate.toLocaleString( );        //获取日期与时间
    var billId = myDate.getFullYear() + "" + (myDate.getMonth() + 1) + "" + myDate.getDate() + "" + myDate.getDay() + "" + myDate.getHours() + "" + myDate.getMinutes() + "" + myDate.getSeconds();
    return billId;
}

//弹出层根据公司部门填充职工栏
var count = 0;
var oldClick;

function selectPersonsByCompanyAndDepShowInPane(company, dep, type, flag) {
    //type:3代表选择经办人，2代表选择报销人
    //flag:0代表刚弹出页面直接显示公司的全部职员，1代表根据公司部门筛选员工
    if (type == 2) {
        if (flag != 0) {
            if (count == 0) {
                oldClick = event.currentTarget;
                oldClick.style.color = 'red';
                count++;
            } else {
                oldClick.style.color = 'black';
                var newClick = event.currentTarget;
                newClick.style.color = 'red';
                oldClick = newClick;
            }
        }
        var persons = selectPersonsByCompanyAndDep(company, dep);
        $("#staffPane").empty();
        for (var i = 0; i < persons.length; i++) {
            $("#staffPane").append("<h5 onclick='chooseReiPersonAndCloseTip()' style='cursor: pointer'>" + persons[i] + "</h5><br>");
        }
    }

    if (type == 3) {
        if (flag != 0) {
            if (count == 0) {
                oldClick = event.currentTarget;
                oldClick.style.color = 'red';
                count++;
            } else {
                oldClick.style.color = 'black';
                var newClick = event.currentTarget;
                newClick.style.color = 'red';
                oldClick = newClick;
            }
        }
        var persons = selectPersonsByCompanyAndDep(company, dep);
        $("#staffPane").empty();
        for (var i = 0; i < persons.length; i++) {
            $("#staffPane").append("<h5 onclick='chooseChargeAndCloseTip()' style='cursor: pointer'>" + persons[i] + "</h5><br>");
        }
    }

}





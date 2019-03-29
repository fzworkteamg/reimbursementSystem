$(document).ready(function () {
    //填充公司下拉框
    var companys = selectCompany();
    romance(companys,"selectCompany")

    //根据公司填充部门
    var company = $("#company").val()
    var deps = selectDepByCompany(company);
    romance(deps,"selectDep")


    //填充发票状态
    var invoiceStatus = selectInvoiceStatus();
    romance(invoiceStatus,"selectInvoiceStatus")


    //填充合同状态
    var contractStatus = selectContractStatus();
    romance(contractStatus,"selectContractStatus")

    //新增保存按钮

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
        var formData = new FormData($( "#form1" )[0]);
        $.ajax({
            url:'/bill/insertBill',
            type : 'post',
            async: false,
            data : formData,
            cache:false,
            contentType: false,
            processData: false,
            success : function(data) {
                if(data.code==1){
                    parent.layer.msg("新增报销保存成功");
                }
            }
        })
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
        area: ['500px', '550px'],
        shadeClose: true, //点击遮罩关闭
        content: "<div class=\"layui-row\">\n" +
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
        area: ['400px', '300px'],
        shadeClose: true, //点击遮罩关闭
        content: "<div class='layui-row' style='text-align: center'>\n" +
            "<form class=\"layui-form\" action=\"\">\n" +
            "<div class=\"layui-form-item\">\n" +
            "    <label class=\"layui-form-label\">选择公司：</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "        <select id='childSelectCompany' name='childSelectCompany1' lay-filter='childSelectCompany' lay-verify=\"required\">\n" +
            "<option></option>" +
            "        </select>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"layui-form-item\">\n" +
            "    <label class=\"layui-form-label\">选择部门：</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "        <select id='childSelectDep' lay-verify=\"required\" lay-filter='childSelectDep'>\n" +
            "<option></option>" +
            "        </select>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"layui-form-item\">\n" +
            "    <label class=\"layui-form-label\">选择员工：</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "        <select name=\"childSelectPerson\" lay-verify=\"required\" lay-filter='childSelectPerson' id='childSelectPerson'>\n" +
            "<option></option>" +
            "        </select>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<button type='button' class='layui-btn' lay-filter='chooseReiPerson' onclick='chooseReiPersonAndCloseTip()' id='chooseReiPersonButton'>确定</button>" +
            "</form>\n" +
            "</div>"
    });
    var companys = selectCompany();
    romance(companys, "childSelectCompany");
    form.render();
}

function openAddChargePerson() {
    chargePersonIndex = layer.open({
        type: 1,
        area: ['400px', '300px'],
        shadeClose: true, //点击遮罩关闭
        content: "<div class='layui-row' style='text-align: center'>\n" +
            "<form class=\"layui-form\" action=\"\">\n" +
            "<div class=\"layui-form-item\">\n" +
            "    <label class=\"layui-form-label\">选择公司：</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "        <select id='childSelectCompany' name='childSelectCompany1' lay-filter='childSelectCompany' lay-verify=\"required\">\n" +
            "<option></option>" +
            "        </select>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"layui-form-item\">\n" +
            "    <label class=\"layui-form-label\">选择部门：</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "        <select id='childSelectDep' lay-verify=\"required\" lay-filter='childSelectDep'>\n" +
            "<option></option>" +
            "        </select>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"layui-form-item\">\n" +
            "    <label class=\"layui-form-label\">选择员工：</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "        <select name=\"childSelectPerson\" lay-verify=\"required\" lay-filter='childSelectPerson' id='childSelectPerson'>\n" +
            "<option></option>" +
            "        </select>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<button type='button' class='layui-btn' lay-filter='chooseChargePerson' onclick='chooseChargePersonAndCloseTip()' id='chooseChargePersonButton'>确定</button>" +
            "</form>\n" +
            "</div>"
    });
    var companys = selectCompany();
    romance(companys, "childSelectCompany");
    form.render();
}

function romanceTable() {
    table.render({
        elem: '#subjectTable',
        page: true,
        url: '/reiSubject/selectDepContentCommentByCompanyDep?company=' + $('#selectSubjectCompany').val() + "&dep=" + $('#selectSubjectDep').val(),
        cols: [[
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

//弹出层选择报销人返回
function chooseReiPersonAndCloseTip() {
    var data = $("#childSelectPerson").val();
    var arr = data.split("|");
    $("#reiPerson").val(arr[0]);
    layer.close(reiPersonIndex);
}

//弹出层选择经办人返回
function chooseChargePersonAndCloseTip() {
    var data = $("#childSelectPerson").val();
    var arr = data.split("|");
    $("#chargePerson").val(arr[0]);
    layer.close(chargePersonIndex);
}


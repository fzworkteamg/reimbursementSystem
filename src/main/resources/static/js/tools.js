
var oldClick;


function selectCompany() {
    var companys;
    $.ajax({
        url: '/company/selectCompany',
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: "application/json",
        success: function (result) {
            companys = result.data
        }
    });
    return companys;
}

//根据公司名查询部门
function selectDepByCompany(company) {
    var deps;
    $.ajax({
        url: '/dep/selectDepByCompany',
        type: 'post',
        dataType: 'json',
        data: {
            "company": company
        },
        async: false,
        async: false,
        success: function (result) {
            deps = result.data;
        }
    });
    return deps;
}

//查询发票状态
function selectInvoiceStatus() {
    var invoiceStatus;
    $.ajax({
        url: '/invoiceStatus/selectInvoiceStatus',
        type: 'post',
        dataType: 'json',
        async: false,
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
        url: '/contractStatus/selectContractStatus',
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: "application/json",
        success: function (result) {
            contractStatus = result.data
        }
    });
    return contractStatus;
}

//根据传入的集合与元素id渲染select
function romance(data, id) {
    for (var i = 0; i < data.length; i++) {
        $("#" + id).append($("<option>" + data[i] + "</option>"))
    }
}

function cleanSelect(id) {
    $("#" + id).empty();
}

//根据公司与部门查询职工
function selectPersonsByCompanyAndDep(company, dep) {
    var persons;
    $.ajax({
        url: '/staff/selectStaffByCompanyAndDep',
        type: 'post',
        dataType: 'json',
        data: {
            company: company,
            dep: dep
        },
        async: false,
        success: function (result) {
            persons = result.data;
        }
    })
    return persons;
}
var count=0;
function selectPersonsByCompanyAndDepShowInPane(company, dep) {
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
    var persons = selectPersonsByCompanyAndDep(company, dep);
    $("#staffPane").empty();
    for (var i = 0; i < persons.length; i++) {
        $("#staffPane").append("<h5 onclick='chooseReiStaff()' style='cursor: pointer'>" + persons[i] + "</h5><br>");
    }
}

function chooseReiStaff() {
    var that = event.currentTarget;
    $("#reiPerson").attr("readOnly", false);
    console.log(that+"====="+that.text);
    $("#reiPerson").text(that.text);
    layer.close(reiPersonIndex);
}

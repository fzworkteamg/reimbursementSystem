

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




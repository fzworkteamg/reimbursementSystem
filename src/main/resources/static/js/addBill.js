$(document).ready(function () {

    //填充公司下拉框
    var companys = selectCompany();
    for (var i = 0; i <companys.length; i++){
        $("#selectCompany").append($("<option>"+companys[i]+"</option>"))
    }
    var company = $("#company").val()
    var deps = selectDepByCompany(company);
    for (var i = 0; i <deps.length; i++){
        $("#selectDep").append($("<option>"+deps[i]+"</option>"))
    }

    //填充发票状态
    var invoiceStatus = selectInvoiceStatus();
    for (var i = 0; i <invoiceStatus.length; i++){
        $("#selectInvoiceStatus").append($("<option>"+invoiceStatus[i]+"</option>"))
    }

    //填充发票状态
    var contractStatus = selectContractStatus();
    for (var i = 0; i <contractStatus.length; i++){
        $("#selectContractStatus").append($("<option>"+contractStatus[i]+"</option>"))
    }




})
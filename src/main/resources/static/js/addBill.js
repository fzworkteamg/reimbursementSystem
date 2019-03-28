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
})
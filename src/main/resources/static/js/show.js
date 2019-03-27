$(document).ready(function () {
    //获取公司填充下拉列表
    var companys = selectCompany();
    for (var i = 0; i <companys.length; i++){
        $("#selectCompany").append($("<option>"+companys[i]+"</option>"))
    }
    $("select[name='company']").change(function () {
        var company = $("#selectCompany").val();
        console.log($("#selectCompany").val());
        if(company!="--选择公司--"){
            $.ajax({
                url: '/dep/selectDepByCompany',
                type: 'post',
                dataType: 'json',
                data:{
                    "company":company
                },
                success: function (result) {
                    var deps = result.data;
                    $("#selectDep").empty();
                    for (var i = 0; i <deps.length; i++){
                        $("#selectDep").append($("<option>"+deps[i]+"</option>"))
                    }
                }
            })
        }
    })

    var formData = new FormData($("#selectForm")[0]);//此处id为form表单的id
    $.ajax({
        url: '/bill/selectBill',
        type: 'post',
        // dataType: 'json',
        contentType: false,
        data: formData,
        processData: false,
        async: false,
        cache: false,
        success: function (result) {
        },
        fail: function () {
            window.alert("系统错误，请联系管理员");
        }
    })

})
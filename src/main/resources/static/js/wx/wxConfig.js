wx.config({
    beta: true,// 必须这么写，否则wx.invoke调用形式的jsapi会有问题
    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wxdc2a7022949cf052', // 必填，企业微信的corpID
    timestamp: 1555034516, // 必填，生成签名的时间戳
    nonceStr: '6b3d35f4fb8e4875', // 必填，生成签名的随机串
    signature: '1f25cdb7879b2f72f1d4d025f150014e63256755',// 必填，签名，见 附录-JS-SDK使用权限签名算法
    jsApiList: ["selectEnterpriseContact"] // 必填，需要使用的JS接口列表，凡是要调用的接口都需要传进来
});
wx.invoke("selectEnterpriseContact", {
        "fromDepartmentId": -1,// 必填，表示打开的通讯录从指定的部门开始展示，-1表示自己所在部门开始, 0表示从最上层开始
        "mode": "multi",// 必填，选择模式，single表示单选，multi表示多选
        "type": ["department", "user"],// 必填，选择限制类型，指定department、user中的一个或者多个
        "selectedDepartmentIds": ["2","3"],// 非必填，已选部门ID列表。用于多次选人时可重入，single模式下请勿填入多个id
        "selectedUserIds": ["lisi","lisi2"]// 非必填，已选用户ID列表。用于多次选人时可重入，single模式下请勿填入多个id
    },function(res){
        if (res.err_msg == "selectEnterpriseContact:ok")
        {
            if(typeof res.result == 'string')
            {
                res.result = JSON.parse(res.result) //由于目前各个终端尚未完全兼容，需要开发者额外判断result类型以保证在各个终端的兼容性
            }
            var selectedDepartmentList = res.result.departmentList;// 已选的部门列表
            for (var i = 0; i < selectedDepartmentList.length; i++)
            {
                var department = selectedDepartmentList[i];
                var departmentId = department.id;// 已选的单个部门ID
                var departemntName = department.name;// 已选的单个部门名称
            }
            var selectedUserList = res.result.userList; // 已选的成员列表
            for (var i = 0; i < selectedUserList.length; i++)
            {
                var user = selectedUserList[i];
                var userId = user.id; // 已选的单个成员ID
                var userName = user.name;// 已选的单个成员名称
                var userAvatar= user.avatar;// 已选的单个成员头像
            }
        }
    }
);

$(document).ready(function () {
    BindPhoneDg("/easyui/handler/userInfoHandler.ashx?method=GetUserInfo");
    fenye();

});


//绑定巡线员表格
function BindPhoneDg(urlAddress) {
    //   var maindiv = parent.document.getElementById("main");
    var theight = $('#userInfoTB').height();
    //   theight = $('#right', parent.document).height();
    //   var lastIndex;
    //   var phoneId=[];

    $('#userInfoTB').datagrid({
        //       title: '手持端管理',
        width: "100%",
        height: theight,
        pagination: true,
        rownumbers: true,
        singleSelect:true,
        striped: true,
        collapsible: false,
        //pageSize: 4,
    	//pageList: [2, 4, 6, 8],
        pageSize: 4,
        pageList: [2, 4, 6, 8],
        url: urlAddress,
       // method: 'get',
        fitColumns: true,
     

        columns: [[
            { field: 'xuehao', title: '学号',

                width: 80, align: 'center'
            },
            { field: 'name', title: '姓名', editor: 'text', width: 80, align: 'center' },

            { field: 'modify', title: '修改用户信息', width: 110, align: 'center',
                formatter: function (value, rec, index) {
                    return '<a  href=javascript:EDITIphone();>编辑</a> <a  href=javascript:getSelected();>保存</a>';
                }
            },
            { field: 'del', title: '删除', width: 100, align: 'center',
                formatter: function (value, rec, index) {
                    return '<a  href=javascript:DelIphone(' + rec.PHONEID + ');>删除</a>';
                }
            }
		]],
        //       rownumbers: true,
        loadMsg: "正在加载，请等待",
        toolbar: [
       {
           id: 'btnadd',
           text: '添加',
           iconCls: 'icon-add',
           handler: function () {
               append();
           }
       }
       ]
    });
}

function fenye() {

    var pager = $('#userInfoTB').datagrid().datagrid('getPager'); // get the pager of datagrid
    pager.pagination({
       
       //// showPageList: false,
       // //        url:url,
        beforePageText: '第',
        afterPageText: '页 共{pages}页',
        displayMsg: '当前显示{from}-{to}条记录，共{total}条记录'

    });
}

var editIndex = undefined;
function endEditing() {
    if (editIndex == undefined) { return true }
    //        if ($('#userInfoTB').datagrid('validateRow', editIndex)) {
    //            var ed = $('#userInfoTB').datagrid('getEditor', { index: editIndex, field: 'xuehao' });
    //            var productname = $(ed.target).combobox('getText');
    //            $('#userInfoTB').datagrid('getRows')[editIndex]['productname'] = productname;
    //            $('#userInfoTB').datagrid('endEdit', editIndex);
    //            editIndex = undefined;
    //            return true;
    //        } else {
    //            return false;
    //        }
}
function append() {
    if (endEditing()) {
        editIndex = $('#userInfoTB').datagrid('getRows').length - 1;
        $('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
    }
}
function InsertPhone() {

    $.ajax({
        type: "GET",
        url: "DataPatrolPhone.ashx?method=InsertPhone&patrolphone=" + escape($("#patrolphone").val()) + "&phonetype="
            + escape($("#phonetype").val()) + "&phonevender=" + escape($("#phonevender").val()) + "&phonebz=" + escape($("#phonebz").val()) + "&date=" + new Date(),
        success: function (response, option) {
            if (response != "") {
                $('#phonetable').datagrid("reload");
            }
            else {
                //该用户无创建计划的权限
            }
            $('#addphone').window('close');

        },
        falure: function (response, option) {
            $('#addphone').window('close');
        }
    });


}

//修改手持设备信息
//function UpdatePhone(id, patrolphone, phonetype, phonevender, phonebz) {
function UpdatePhone(id) {
    $.ajax({
        type: "GET",
        url: "DataPatrolPhone.ashx?method=UpdatePhone&id=" + id + "patrolphone=" + escape($("#phonenumber").val()) + "&phonetype="
            + escape($("#phonetype2").val()) + "&phonevender=" + escape($("#phonevender2").val()) + "&phonebz=" + escape($("#phonebz2").val()) + "&date=" + new Date(),
        success: function (response, option) {
            if (response != "") {
                $('#phonetable').datagrid("reload");
            }
            else {
                //该用户无创建计划的权限
            }

        },
        falure: function (response, option) {
        }
    });
}
//打开更新窗口
function OpenUpdatePhone(rec) {
    //alert("1");
    $('#updatephone').window('open');
}

//修改手持信息
function EDITIphone(lastIndex, rowIndex) {
    $('#phonetable').datagrid('rejectChanges');
    if (lastIndex != rowIndex) {
        $('#phonetable').datagrid('endEdit', lastIndex);
        $('#phonetable').datagrid('beginEdit', rowIndex);
    }
    lastIndex = rowIndex;
}

function getSelected() {
    $('#phonetable').datagrid('acceptChanges');
    var selected = $('#phonetable').datagrid('getSelected');
    $.ajax({
        type: "GET",
        url: "DataPatrolPhone.ashx?method=UpdatePhone&id=" + escape(selected.PHONEID) + "&patrolphone=" + escape(selected.PHONENUM) + "&phonetype="
            + escape(selected.PHONETYPE) + "&phonevender=" + escape(selected.PHONEVENDER) + "&phonebz=" + escape(selected.PHONEBZ) + "&date=" + new Date(),
        success: function (response, option) {
            if (response != "") {
                $('#phonetable').datagrid("reload");

            }
            else {
                //该用户无创建计划的权限
                //$.messager.alert('Warning', '添加失败'); 
            }

        },
        falure: function (response, option) {
        }
    });
}
//删除手持设备
function DelIphone(id) {
    //调用删除计划服务
    $.ajax({
        type: "GET",
        url: "DataPatrolPhone.ashx?method=DelPhone&id=" + id,
        success: function (response, option) {
            if (response != "") {
                if (parseInt(response) > 0) {
                    $('#phonetable').datagrid("reload");
                }
            }
            $('#btnsave').linkbutton('enable');
        },
        falure: function (response, option) {
            //服务调用失败提示
            $('#btnsave').linkbutton('enable');
        }

    });
}

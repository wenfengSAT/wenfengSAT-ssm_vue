$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/notice/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 10, key: true },
			{ label: '用户名', name: 'body', width: 120 }, 			
			{ label: '类型', name: 'type', width: 20 ,formatter: function(value, options, row){
				return value === 0 ? 
						'<span class="label label-success">正常</span>' : 
						'<span class="label label-danger">重要</span>';
				}}, 
			{ label: '是否删除', name: 'isDelete', width: 25,formatter: function(value, options, row){
				return value === 0 ? 
						'<span class="label label-success">正常</span>' : 
						'<span class="label label-danger">禁用</span>';
				} },		
			{ label: '创建时间', name: 'gmtCreate', width: 50 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			key: null
		},
		showList: true,
        title:null,
        notice:{
        	body:null, 
        	type:0
        }
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'key': vm.q.key},
                page:page
            }).trigger("reloadGrid");
		},
		add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.notice = {body:null, type:0};
        },
        update: function () {
            var noticeId = getSelectedRow();
            if(noticeId == null){
                return ;
            }
            vm.getNotice(noticeId);
            vm.showList = false;
            vm.title = "修改";
        },
        del: function () {
            var noticeIds = getSelectedRows();
            if(noticeIds == null){
                return ;
            }
            console.log("noticeIds:"+noticeIds);
            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/notice/delete",
                    contentType: "application/json",
                    data: JSON.stringify(noticeIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            var url = vm.notice.id == null ? "sys/notice/save" : "sys/notice/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.notice),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        getNotice: function(noticeId){
            $.get(baseURL + "sys/notice/info/"+noticeId, function(r){
                vm.notice = r.notice;
            });
        }
	}
});
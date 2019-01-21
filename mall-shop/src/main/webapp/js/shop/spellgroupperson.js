$(function () {
    $("#jqGrid").Grid({
        url: '../spellgroupperson/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '关联的商品', name: 'spellId', index: 'spell_id', width: 80},
			{label: '', name: 'userName', index: 'user_name', width: 80},
			{label: '', name: 'userId', index: 'user_id', width: 80},
			{label: '扩展信息', name: 'ext', index: 'ext', width: 80},
			{label: '', name: 'createTime', index: 'create_time', width: 80},
			{label: '', name: 'updateTime', index: 'update_time', width: 80}]
    });
});

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		spellGroupPerson: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.spellGroupPerson = {};
		},
		update: function (event) {
            var id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            var url = vm.spellGroupPerson.id == null ? "../spellgroupperson/save" : "../spellgroupperson/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.spellGroupPerson),
                type: "POST",
			    contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
			});
		},
		del: function (event) {
            var ids = getSelectedRows("#jqGrid");
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
				    url: "../spellgroupperson/delete",
                    params: JSON.stringify(ids),
                    type: "POST",
				    contentType: "application/json",
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
					}
				});
			});
		},
		getInfo: function(id){
            Ajax.request({
                url: "../spellgroupperson/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.spellGroupPerson = r.spellGroupPerson;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                name: ''
            }
            vm.reload();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
	}
});
$(function () {
    $("#jqGrid").Grid({
        url: '../spellgroupconfig/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '关联的商品', name: 'goodsId', index: 'goods_id', width: 80},
			{label: '', name: 'goodsImage', index: 'goods_image', width: 80},
			{label: '', name: 'goodsName', index: 'goods_name', width: 80},
			{label: '', name: 'goodsType', index: 'goods_type', width: 80},
			{label: '类型', name: 'spellType', index: 'spell_type', width: 80},
			{label: '参与人数', name: 'personNum', index: 'person_num', width: 80},
			{label: '中奖人数', name: 'winningNum', index: 'winning_num', width: 80},
			{label: '支付方式', name: 'payType', index: 'pay_type', width: 80},
			{label: '支付金额', name: 'payAmount', index: 'pay_amount', width: 80},
			{label: '排序', name: 'orderNo', index: 'order_no', width: 80},
			{label: '', name: 'firstSpellId', index: 'first_spell_id', width: 80},
			{label: '', name: 'firstSpellName', index: 'first_spell_name', width: 80},
			{label: '', name: 'firstSpellImage', index: 'first_spell_image', width: 80},
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
		spellGroupConfig: {},
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
			vm.spellGroupConfig = {};
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
            var url = vm.spellGroupConfig.id == null ? "../spellgroupconfig/save" : "../spellgroupconfig/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.spellGroupConfig),
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
				    url: "../spellgroupconfig/delete",
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
                url: "../spellgroupconfig/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.spellGroupConfig = r.spellGroupConfig;
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
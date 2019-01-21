$(function () {
    $("#jqGrid").Grid({
        url: '../depositorder/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商家', name: 'sellerName', index: 'seller_name', width: 80},
            {label: '门店', name: 'storeName', index: 'storeName', width: 80},
            {label: '订单号', name: 'orderCode', index: 'order_code', width: 80},
            {label: '保证金金额', name: 'payMoney', index: 'pay_money', width: 80},
            {
                label: '订单状态', name: 'status', index: 'status', width: 80, formatter: function (value) {
                if (value == 0) {
                    return "未支付";
                }
                if (value == 1) {
                    return "已支付";
                }
            }
            },
            {
                label: '支付时间', name: 'payTime', index: 'pay_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
            {
                label: '创建时间', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        depositOrder: {},
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
            vm.depositOrder = {};
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
            var url = vm.depositOrder.id == null ? "../depositorder/save" : "../depositorder/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.depositOrder),
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
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../depositorder/delete",
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
        getInfo: function (id) {
            Ajax.request({
                url: "../depositorder/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.depositOrder = r.depositOrder;
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
        reloadSearch: function () {
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
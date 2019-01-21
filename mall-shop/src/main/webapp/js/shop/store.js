$(function () {
    $("#jqGrid").Grid({
        url: '../store/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商家id', name: 'sellerId', index: 'seller_id', width: 80},
            {label: '店铺名称', name: 'storeName', index: 'store_name', width: 80},
            {label: '店铺地址', name: 'storeAddress', index: 'store_address', width: 80},
            {label: '经营类目', name: 'typeName', index: 'typeName', width: 80},
            {label: '联系电话', name: 'mobile', index: 'mobile', width: 80},
            {
                label: '状态', name: 'status', index: 'status', width: 80, formatter: function (value) {
                if (value == 0) {
                    return "未审核";
                }
                if (value == 1) {
                    return "审核通过";
                }
                if (value == 2) {
                    return "冻结";
                }
                return "-";
            }
            },
            {
                label: '头图', name: 'banner', index: 'banner', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {
                label: '法人身份证', name: 'bossIdCard', index: 'boss_id_card', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {
                label: '营业执照', name: 'businessLicense', index: 'business_license', width: 80, formatter: function (value) {
                return transImg(value);
            }
            },
            {
                label: '加入质量保证计划', name: 'isVip', index: 'is_vip', width: 80, formatter: function (value) {
                if (value == 0) {
                    return "否";
                }
                if (value == 1) {
                    return "是";
                }
                return "-";
            }
            },
            {label: '保证金', name: 'cashDeposit', index: 'cash_deposit', width: 80},
            {label: '评分', name: 'score', index: 'score', width: 80},
            {label: '积分', name: 'point', index: 'point', width: 80},
            {
                label: '创建时间', name: 'createTime', index: 'create_time', width: 80, formatter: function (value) {
                return transDate(value, 'yyyy-MM-dd');
            }
            }
        ]
    });
})
;

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        businessCategoryList:[],
        store: {
            businessLicense: "",
            bossIdCard: "",
            banner: ""
        },
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
            vm.store = {
                businessLicense: "",
                bossIdCard: "",
                banner: ""
            };
            vm.getBusinessCategoryList();
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getBusinessCategoryList();
            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.store.id == null ? "../store/save" : "../store/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.store),
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
                    url: "../store/delete",
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
        /**
         * 获取品牌
         */
        getBusinessCategoryList: function () {
            Ajax.request({
                url: "../store/getBusinessCategoryList",
                async: true,
                successCallback: function (r) {
                    vm.businessCategoryList = r.list;
                }
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../store/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.store = r.store;
                    vm.getBusinessCategoryList();
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
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleProgress: function () {
            this.$Message.loading({
                content: '上传中....',
                duration: 0
            });
        },
        handleSuccessLicensePicUrl: function (res, file) {
            this.$Message.destroy();
            this.$Message.success('上传成功');
            vm.store.businessLicense = file.response.url;
        },
        handleSuccessIdCardPicUrl: function (res, file) {
            this.$Message.destroy();
            this.$Message.success('上传成功');
            vm.store.bossIdCard = file.response.url;
        },
        handleSuccessBannerPicUrl: function (res, file) {
            this.$Message.destroy();
            this.$Message.success('上传成功');
            vm.store.banner = file.response.url;
        },
        eyeImageLicensePicUrl: function () {
            var url = vm.store.businessLicense;
            eyeImage(url);
        },
        eyeImageBannerPicUrl: function () {
            var url = vm.store.banner;
            eyeImage(url);
        },
        eyeImageIdCardPicUrl: function () {
            var url = vm.store.bossIdCard;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
    }
});

'use strict';

app.controller('ActivitiListController', function($scope, $resource,$stateParams,$modal,$state,$localStorage) {
    
    //设置user值
    $scope.session_user = $localStorage.user;

    //查询
    $scope.query = function(page,filter,roleId){

        var $com = $resource($scope.app.host + "/activiti/list/?page=:page&search=:filter&roleId=:roleId",{page:'@page',filter:'@filter',roleId:'@roleId'});
        roleId = $localStorage.user.roleId
        if(!page){
            page=1;
        }else{
            page=parseInt(page);
        }
        $com.get({page:page,filter:filter,roleId:roleId},function(data){
            console.log(data);

            //计算页面数，默认每页10条记录
            data.page_count = data.total / 10 + 1;

            //扩展分页数据，显示页签，最终效果为  < 1 2 3 4 5 >
            data.page_index = page;
            data.pages = [];    //页签表
            var N = 5;          //每次显示5个页签
            var s = Math.floor(page/N)*N;
            if(s==page)s-=N;
            s += 1;
            var e = Math.min(data.page_count,s+N-1)
            for(var i=s;i<=e;i++)
                data.pages.push(i);
            $scope.data = data;
            $scope.search_context = filter;
        }
      );
    }
    //搜索跳转
    $scope.search = function(){
        $state.go('app.activiti.list',{search:$scope.search_context});
    }
    //全选
    var selected = false;
    $scope.selectAll = function(){
        selected = !selected;
        angular.forEach($scope.data.object,function(item){
            item.selected = selected;
        });
    }
    //自定义操作处理，其中1为删除所选记录
    $scope.exec = function(){
        if($scope.operate=="1"){
            var ids = [];
            angular.forEach($scope.data.object,function(item){
                if(item.selected){
                    ids.push(item.id);
                }
            });
            if(ids.length>0){
                //弹出删除确认
                var modalInstance = $modal.open({
                    templateUrl: 'admin/confirm.html',
                    controller: 'ConfirmController',
                    size:'sm',
                });
                modalInstance.result.then(function () {
                      var $com = $resource($scope.app.host + "/activiti/deletes/?");
                      $com.delete({'ids':ids.join(',')},function(){
                          $state.go('app.activiti.list');
                      });
                });
            }
        }
    }
    //根据url参数（分页、搜索关键字）查询数据
    $scope.query($stateParams.page,$stateParams.search);

    //工作流任务重定向到不同界面
    $scope.redirect = function(data){
      switch(data.taskName){
        case "创建产品投标":
          $state.go('app.activiti.detail', {"id":data.id});
          break;
        case "技术部门-产品拆分":
          console.log(11);
          $state.go('app.activiti.productSplit', {"id":data.id});
          break;
        case "仓库部门-确认数量":
          $state.go('app.activiti.confirmQuantity', {"id":data.id});
          break;
        case "生产部门-确定费用":
          $state.go('app.activiti.determineCost', {"id":data.id});
          break;
        case "采购部门-确定收购价":
          $state.go('app.activiti.determinePurchasePrice', {"id":data.id});
          break;
        case "查看报价结果":
          $state.go('app.activiti.quoteResults', {"id":data.id});
          break;
        default:
          alert("任务名出错，请联系管理员");
      }
    }
});

app.controller('ConfirmController', ['$scope', '$modalInstance', function($scope, $modalInstance){
     $scope.ok = function () {
        $modalInstance.close();
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
  };
}]);

app.controller('ActivitiDetailController', function($rootScope,$scope, $resource, $stateParams,$state,$localStorage) {

  $scope.edit_mode = !!$stateParams.id;
  if($scope.edit_mode){
      var $com = $resource($scope.app.host + "/activiti/:id/?",{id:'@id'});
      var resp = $com.get({id:$stateParams.id},function(data){
          $scope.data = data.object;
      });
  }
  else{
      $scope.data = {};
  }
  $scope.submit = function(){
      if($scope.edit_mode){
          var $com = $resource($scope.app.host + "/activiti/:id/?",{id:'@id'},{
              'update': { method:'PUT' },
          });
          $com.update({id:$stateParams.id},$scope.data,function(data){
              $state.go('app.activiti.list');
          });
      }
      else{
          $scope.data.userId = $localStorage.user.id;
          $scope.data.username = $localStorage.user.name;
          $scope.data.assigneeRoleId = $localStorage.user.roleId;
          $scope.data.assigneeDepartment = "boss";
          var $com = $resource($scope.app.host + "/activiti/?");
          $com.save($scope.data,function(data){
              $state.go('app.activiti.list');
          });
      }
  };
  $scope.delete = function(){
      var $com = $resource($scope.app.host + "/activiti/:id/?",{id:'@id'});
      $com.delete({id:$stateParams.id},function(){
          $state.go('app.activiti.list');
      })
  }
});

//技术部门-产品分割
app.controller('ProductSplitController', function($scope, $resource,$stateParams,$modal,$state,$localStorage){
    console.log($stateParams.id);

    //获取报价投标数据
    $scope.edit_mode = !!$stateParams.id;
    if($scope.edit_mode){
        var $com = $resource($scope.app.host + "/activiti/:id/?",{id:'@id'});
        var resp = $com.get({id:$stateParams.id},function(data){
            $scope.bidding = data.object["bidding"];
            $scope.materialTypes = data.object["materialTypes"];
        });
    }
    else{
        $scope.bidding = {};
        $scope.materialTypes = [];
    }

    //动态options，用于存储提交的数据
    $scope.biddingMaterialList = [];

    //为新建选项框创建唯一标示
    var randomCode = function() {
        var chars = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKMNOPQRSTUVWXYZ1234567890";
        var randomChars = "";
        for (var i = 0; i < 10; i++) {
            var index = Math.floor(Math.random() * chars.length);
            randomChars = randomChars + chars.charAt(index);
        }
        return randomChars;
    }
    var getIndex = function(array, id) {
        var tmpItem = {};
        angular.forEach(array, function(item) {
            if (item.optionIndex == id) {
                tmpItem = item;
            }
        });
        return array.indexOf(tmpItem);
    }
    $scope.add = function() {
        var optionIndex = randomCode();
        $scope.biddingMaterialList.push({
            biddingId: $stateParams.id,
            optionIndex : optionIndex,
            materialType : {materials:[]},
            material : {},
            neededNumber: null
        });
        console.log($scope.biddingMaterialList)
    }
    
    $scope.deleteFunc = function(id) {
        var tmpIdIndex = getIndex($scope.biddingMaterialList, id);
        
        $scope.biddingMaterialList.splice(tmpIdIndex, 1);
    }

    //返回列表页
    $scope.cancel = function(){
        $state.go('app.activiti.list');
    };

    //提交
    $scope.submit = function(){
        if($scope.biddingMaterialList.length == 0){
            alert("材料种类不能为0");
            return;
        }
        //处理数据
        angular.forEach($scope.biddingMaterialList, function(item) {
            if (item.material.materialCode == undefined) {
                alert("请选择材料！");
                return;
            }
            if (item.materialType.materialTypeName == undefined) {
                alert("请选择材料类型！");
                return;
            }
            if (item.neededNumber == null) {
                alert("请输入数量！");
                return;
            }
            item.materialCode = item.material.materialCode;
            item.materialTypeCode = item.materialType.materialTypeCode;
            item.materialName = item.material.materialName;
            item.materialTypeName = item.materialType.materialTypeName;
            item.costPrice = item.material.materialPrice;
        });

        //创建参数
        var data = {
            biddingMaterials: $scope.biddingMaterialList,
            assigneeRoleId: $localStorage.user.roleId
        }
        var $com = $resource($scope.app.host + "/activiti/productSplit/?");
        $com.save(data ,function(data){
            $state.go('app.activiti.list');
        });        
    };
});

//仓库部门-确认数量
app.controller('ConfirmQuantityController', function($scope, $resource,$stateParams,$modal,$state,$localStorage){
    console.log($stateParams.id);

    var $com = $resource($scope.app.host + "/activiti/confirmQuantity/:id/?",{id:'@id'});
    var resp = $com.get({id:$stateParams.id},function(data){
        $scope.bidding = data.object;
        $scope.biddingMaterials = data.object.biddingMaterials;
    });

    //不够
    $scope.cancel = function(){
        $scope.bidding.condition = "不够";

        //计算仓库成本价格
        $scope.bidding.costSumPrice = 0;
        angular.forEach($scope.bidding.biddingMaterials, function(item) {
            if(item.sumNumber < item.neededNumber){
                item.isEnough = 1;
                item.buyNumber = item.neededNumber - item.sumNumber;
                $scope.bidding.costSumPrice = $scope.bidding.costSumPrice + item.sumNumber*item.costPrice;
            }
            else{
                $scope.bidding.costSumPrice = $scope.bidding.costSumPrice + item.costPrice * item.neededNumber;
            }
        });

        //更新数据
        updateBiddingConfirmQuantity();  
    };

    //足够
    $scope.submit = function(){
        $scope.bidding.condition = "足够";

        //计算仓库成本价格
        $scope.bidding.costSumPrice = 0;
        angular.forEach($scope.bidding.biddingMaterials, function(item) {
            $scope.bidding.costSumPrice = $scope.bidding.costSumPrice + item.costPrice * item.neededNumber;
        });
        //更新数据
        updateBiddingConfirmQuantity();  
    };

    var updateBiddingConfirmQuantity = function(){
        var $com = $resource($scope.app.host + "/activiti/confirmQuantity/:id/?",{id:'@id'}, {
            'update': { method:'PUT' }
        });
        $com.update({id:$stateParams.id},$scope.bidding,function(data){
            $state.go('app.activiti.list');
        });
    }
});

//采购部门-确定收购价
app.controller('DeterminePurchasePriceController', function($scope, $resource,$stateParams,$modal,$state,$localStorage){
    console.log($stateParams.id);

    var $com = $resource($scope.app.host + "/activiti/determinePurchasePrice/:id/?",{id:'@id'});
    var resp = $com.get({id:$stateParams.id},function(data){
        $scope.bidding = data.object;
        $scope.biddingMaterials = data.object.biddingMaterials;
    });

    //返回列表页
    $scope.cancel = function(){
        $state.go('app.activiti.list');
    };

    //提交
    $scope.submit = function(){

        //计算收购成本价格
        $scope.bidding.purchaseSumPrice = 0;
        angular.forEach($scope.bidding.biddingMaterials, function(item) {
            $scope.bidding.purchaseSumPrice = $scope.bidding.purchaseSumPrice + item.buyNumber*item.buyPrice;
        });

        var $com = $resource($scope.app.host + "/activiti/determinePurchasePrice/:id/?",{id:'@id'}, {
            'update': { method:'PUT' }
        });
        $com.update({id:$stateParams.id},$scope.bidding,function(data){
            $state.go('app.activiti.list');
        });
    };

});

//生产部门-确定费用
app.controller('DetermineCostController', function($scope, $resource,$stateParams,$modal,$state,$localStorage){
    console.log($stateParams.id);

    var $com = $resource($scope.app.host + "/activiti/determineCost/:id/?",{id:'@id'});
    var resp = $com.get({id:$stateParams.id},function(data){
        $scope.bidding = data.object;
        $scope.biddingMaterials = data.object.biddingMaterials;
    });

    //返回列表页
    $scope.cancel = function(){
        $state.go('app.activiti.list');
    };

    //提交
    $scope.submit = function(){
        $scope.bidding.biddingProduceCost.biddingId = $stateParams.id;

        //计算生产成本价格
        var biddingProduceCost = $scope.bidding.biddingProduceCost;
        biddingProduceCost.sumProducePrices = biddingProduceCost.personWorkingHours + biddingProduceCost.staffOvertimeCosts +
            biddingProduceCost.manageServiceCharges + biddingProduceCost.toolWearCosts +
            biddingProduceCost.materialAccessoriesPrices;
        $scope.bidding.biddingProduceCost.sumProducePrices = biddingProduceCost.sumProducePrices;

        //计算最终价格
        $scope.bidding.sumPrice = $scope.bidding.costSumPrice + $scope.bidding.purchaseSumPrice + 
            biddingProduceCost.sumProducePrices;

        var $com = $resource($scope.app.host + "/activiti/determineCost/?");
        $com.save($scope.bidding ,function(data){
            $state.go('app.activiti.list');
        });
    };

});

//查看报价结果
app.controller('QuoteResultsController', function($scope, $resource,$stateParams,$modal,$state,$localStorage){
    console.log($stateParams.id);

    var $com = $resource($scope.app.host + "/activiti/quoteResults/:id/?",{id:'@id'});
    var resp = $com.get({id:$stateParams.id},function(data){
        $scope.bidding = data.object;
        $scope.biddingMaterials = data.object.biddingMaterials;
    });

    //返回列表页
    $scope.cancel = function(){
        $state.go('app.activiti.list');
    };

    //可能会多次查看，确定按钮暂不处理
    $scope.submit = function(){

        //待添加
    };

});

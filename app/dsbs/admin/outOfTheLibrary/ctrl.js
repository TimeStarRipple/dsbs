
'use strict';

app.controller('OutOfTheLibraryController', function($scope, $resource,$stateParams,$modal,$state,$localStorage) {
	$scope.query = function(page,filter){

        var $com = $resource($scope.app.host + "/material/?page=:page&search=:filter",{page:'@page',filter:'@filter'});
        if(!page){
            page=1;
        }else{
            page=parseInt(page);
        }
        $com.get({page:page,filter:filter},function(data){
            console.log(data);
            data.total = 10;
            //计算页面数，默认每页10条记录
            if(data.total / 10 * 10 == data.total){
              data.page_count = data.total / 10;
            }
            else{
              data.page_count = data.total / 10 + 1;
            }

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

    //根据url参数（分页、搜索关键字）查询数据
    $scope.query($stateParams.page,$stateParams.search);

    $scope.update = function(data){
        console.log(data);
        //添加入库
        data.materialNumber = data.materialNumber - data.addNumber;

        //更新
        var $com = $resource($scope.app.host + "/material/:id/?",{id:'@id'},{
            'update': { method:'PUT' },
        });
        $com.update({id:data.id},data,function(data){
            $state.go('app.outOfTheLibrary.list');
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
                      var $com = $resource($scope.app.host + "/material/deletes/?");
                      $com.delete({'ids':ids.join(',')},function(){
                          $state.go('app.outOfTheLibrary.list');
                      });
                });
            }
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